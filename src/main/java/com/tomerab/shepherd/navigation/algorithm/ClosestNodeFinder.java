package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.cost.HaversineCalcAlgo;
import com.tomerab.shepherd.navigation.algorithm.graph.DiGraph;
import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;
import com.tomerab.shepherd.navigation.algorithm.graph.Pair;
import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public class ClosestNodeFinder {
    private double lon;
    private double lat;

    public ClosestNodeFinder(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public GraphNode findClosestNode(DiGraph graph) {
        var adjList = graph.getAdjList();
        HaversineCalcAlgo calculator = new HaversineCalcAlgo();

        return adjList.parallelStream().filter((p1) -> {
            String tag = p1.getKey().getTag();
            return "highway".equals(tag) ||
                    "footway".equals(tag) ||
                    "cycleway".equals(tag) ||
                    "track".equals(tag);
        })
                .map(Pair::getKey)
                .min((p1, p2) -> {
                    double dist1 = calculator.calculate(
                            p1.getLon(),
                            p1.getLat(),
                            lat,
                            lon);
                    double dist2 = calculator.calculate(
                            p2.getLon(),
                            p2.getLat(),
                            lat,
                            lon);

                    return Double.compare(dist1, dist2);
                }).orElse(null);
    }
}