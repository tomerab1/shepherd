package com.tomerab.shepherd.navigation.graphconstructor;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.tomerab.shepherd.navigation.algorithm.graph.DiGraph;
import com.tomerab.shepherd.navigation.algorithm.graph.Node;

public class DiGraphConstructor {
    private final String dataPath;
    private static final String WAY_TAG = "way";
    private static final String ND_TAG = "nd";
    private static final String REF_ATTR = "ref";
    private static final String NODE_TAG = "node";
    private static final String ID_ATTR = "id";
    private static final String LON_ATTR = "lon";
    private static final String LAT_ATTR = "lat";

    public DiGraphConstructor(String path) {
        this.dataPath = path;
    }

    public DiGraph construct() {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        DiGraph graph = new DiGraph();

        try (BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(dataPath))) {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fileInputStream);
            processXml(reader, graph);
        } catch (XMLStreamException | IOException ex) {
            System.err.println("Exception while constructing graph: " + ex.getMessage());
        }

        return graph;
    }

    private void processXml(XMLEventReader reader, DiGraph graph) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();

                switch (startElement.getName().getLocalPart()) {
                    case NODE_TAG:
                        processNode(startElement, graph);
                        break;
                    case WAY_TAG:
                        processWay(reader, graph);
                        break;
                }
            }
        }
    }

    private void processNode(StartElement startElement, DiGraph graph) {
        try {
            long id = Long.parseLong(startElement.getAttributeByName(new QName(ID_ATTR)).getValue());
            double lat = Double.parseDouble(startElement.getAttributeByName(new QName(LAT_ATTR)).getValue());
            double lon = Double.parseDouble(startElement.getAttributeByName(new QName(LON_ATTR)).getValue());
            graph.addNode(new Node(id, lat, lon));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing node attributes: " + e.getMessage());
        }
    }

    private void processWay(XMLEventReader reader, DiGraph graph) throws XMLStreamException {
        List<Long> nodeIds = new LinkedList<>();

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isEndElement() && WAY_TAG.equals(event.asEndElement().getName().getLocalPart())) {
                break;
            }

            if (event.isStartElement() && ND_TAG.equals(event.asStartElement().getName().getLocalPart())) {
                StartElement startElement = event.asStartElement();
                long ref = Long.parseLong(startElement.getAttributeByName(new QName(REF_ATTR)).getValue());
                nodeIds.add(ref);
            }
        }

        if (nodeIds.size() > 1) {
            createEdgesFromNodeIds(graph, nodeIds);
        }
    }

    private void createEdgesFromNodeIds(DiGraph graph, List<Long> nodeIds) {
        Node source = graph.getNodeById(nodeIds.get(0));
        for (int i = 1; i < nodeIds.size(); i++) {
            Node target = graph.getNodeById(nodeIds.get(i));
            if (source != null && target != null) {
                graph.addEdge(source, target);
            } else {
                System.err.println(
                        "Invalid node ID found during edge creation: " + nodeIds.get(i) + " or " + nodeIds.get(i + 1));
            }
        }
    }
}
