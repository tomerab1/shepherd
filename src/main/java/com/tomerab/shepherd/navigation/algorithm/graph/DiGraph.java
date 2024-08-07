package com.tomerab.shepherd.navigation.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiGraph {
    private List<Pair<Node, List<Node>>> adjList;
    private Map<Long, Integer> idToIndex;

    public DiGraph() {
        this.adjList = new ArrayList<>();
        this.idToIndex = new HashMap<>();
    }

    public void addNode(Node node) {
        if (!idToIndex.containsKey(node.getId())) {
            idToIndex.put(node.getId(), adjList.size());
            adjList.add(new Pair<>(node, new ArrayList<>()));
        }
    }

    public void addEdge(Node source, Node neighbor) {
        if (!idToIndex.containsKey(source.getId())) {
            throw new IllegalArgumentException("Error: Node with id=" + source.getId() + " is not in the graph");
        }
        if (source == neighbor) {
            return;
        }

        int sourceIndex = idToIndex.get(source.getId());
        adjList.get(sourceIndex).getValue().add(neighbor);
    }

    public Node getNodeById(long id) {
        return adjList.get(idToIndex.get(id)).getKey();
    }

    public List<Pair<Node, List<Node>>> getAdjList() {
        return adjList;
    }

    public List<Node> getNeighbors(long nodeId) {
        if (!idToIndex.containsKey(nodeId)) {
            return null;
        }

        return adjList.get(idToIndex.get(nodeId)).getValue();
    }
}
