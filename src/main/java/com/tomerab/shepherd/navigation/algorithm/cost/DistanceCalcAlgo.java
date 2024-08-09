package com.tomerab.shepherd.navigation.algorithm.cost;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

/**
 * Calculates the distance between two points represented by a weighted edge.
 * Implements the Algorithm interface.
 */
public class DistanceCalcAlgo implements CostAlgorithm {
    @Override
    public double calculate(WeightedEdge e1) {
        double dx = e1.first().getLon() - e1.second().getLon();
        double dy = e1.first().getLat() - e1.second().getLat();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public double calculate(double lon1, double lat1, double lon2, double lat2) {
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
    }
}
