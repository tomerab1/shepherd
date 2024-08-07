package com.tomerab.shepherd.navigation.algorithm.graph;

public class Node {
    private long id;
    private double lat;
    private double lon;

    public Node(long id, double lat, double lon) {
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
