package com.tomerab.shepherd.navigation.algorithm.graph;

/**
 * Represents a node in the A* algorithm graph.
 */
public class GraphNode {
    private long id;
    private double lat;
    private double lon;

    public GraphNode(long id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        GraphNode otherNode = (GraphNode) other;
        return Double.compare(this.lat, otherNode.lat) == 0 && Double.compare(this.lon, otherNode.lon) == 0;
    }

    @Override
    public String toString() {
        return "Node(" + id + ", " + lat + ", " + lon + ")";
    }
}
