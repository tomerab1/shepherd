package com.tomerab.shepherd.navigation.algorithm.graph;

public class WeightedEdge {
    private GraphNode node1;
    private GraphNode node2;
    private double weight;

    public WeightedEdge(GraphNode node1, GraphNode node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public GraphNode first() {
        return node1;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public GraphNode second() {
        return node2;
    }
}
