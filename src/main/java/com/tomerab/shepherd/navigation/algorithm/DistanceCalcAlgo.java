package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public class DistanceCalcAlgo implements Algorithm {
    @Override
    public double calculate(WeightedEdge e1) {
        double dx = e1.first().getLon() - e1.second().getLon();
        double dy = e1.first().getLat() - e1.second().getLat();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
