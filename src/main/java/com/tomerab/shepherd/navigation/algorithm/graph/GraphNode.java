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
    public String toString() {
        return "Node(" + id + ", " + lat + ", " + lon + ")";
    }
}
