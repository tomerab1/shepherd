package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public class HaversineCalcAlgo implements Algorithm {
    private static final double EARTH_RADIUS = 6371.0;

    public double calculate(WeightedEdge edge) {
        return calculateHaversine(edge);
    }

    private double calculateHaversine(WeightedEdge edge) {
        double phi1 = Math.toRadians(edge.first().getLat());
        double phi2 = Math.toRadians(edge.second().getLat());
        double deltaPhi = Math.toRadians(edge.second().getLat() - edge.first().getLat());

        double lambda1 = Math.toRadians(edge.first().getLon());
        double lambda2 = Math.toRadians(edge.second().getLon());
        double deltaLambda = Math.toRadians(lambda2 - lambda1);

        double a = haversine(deltaPhi) + Math.cos(phi1) * Math.cos(phi2) * haversine(deltaLambda);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private double haversine(double radian) {
        return Math.pow(Math.sin(radian / 2), 2);
    }
}
