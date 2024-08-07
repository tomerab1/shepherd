package com.tomerab.shepherd.navigation.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a directed graph (DiGraph) data structure.
 * 
 * This class provides methods to add nodes and edges to the graph, retrieve
 * nodes by ID,
 * retrieve the adjacency list, and get the neighbors of a specific node.
 */
public class DiGraph {
    private List<Pair<AStarNode, List<AStarNode>>> adjList;
    private Map<Long, Integer> idToIndex;

    public DiGraph() {
        this.adjList = new ArrayList<>();
        this.idToIndex = new HashMap<>();
    }

    public void addNode(AStarNode node) {
        if (!idToIndex.containsKey(node.getId())) {
            idToIndex.put(node.getId(), adjList.size());
            adjList.add(new Pair<>(node, new ArrayList<>()));
        }
    }

    public void addEdge(AStarNode source, AStarNode neighbor) {
        if (!idToIndex.containsKey(source.getId())) {
            throw new IllegalArgumentException("Error: Node with id=" + source.getId() + " is not in the graph");
        }
        if (source == neighbor) {
            return;
        }

        int sourceIndex = idToIndex.get(source.getId());
        adjList.get(sourceIndex).getValue().add(neighbor);
    }

    public AStarNode getNodeById(long id) {
        return adjList.get(idToIndex.get(id)).getKey();
    }

    public List<Pair<AStarNode, List<AStarNode>>> getAdjList() {
        return adjList;
    }

    public List<AStarNode> getNeighbors(long nodeId) {
        if (!idToIndex.containsKey(nodeId)) {
            return null;
        }

        return adjList.get(idToIndex.get(nodeId)).getValue();
    }
}
