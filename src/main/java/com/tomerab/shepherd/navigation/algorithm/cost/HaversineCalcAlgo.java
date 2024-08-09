package com.tomerab.shepherd.navigation.algorithm.cost;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

/**
 * The HaversineCalcAlgo class implements the Algorithm interface and provides a
 * method to calculate the distance between two points on the Earth's surface
 * using the Haversine formula.
 */
public class HaversineCalcAlgo implements CostAlgorithm {
    private static final double EARTH_RADIUS = 6371.0;

    @Override
    public double calculate(WeightedEdge edge) {
        return calculateHaversine(
                edge.first().getLon(),
                edge.first().getLat(),
                edge.second().getLon(),
                edge.second().getLat());
    }

    @Override
    public double calculate(double lon1, double lat1, double lon2, double lat2) {
        return calculateHaversine(lon1, lat1, lon2, lat2);
    }

    private double calculateHaversine(double lon1, double lat1, double lon2, double lat2) {
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);

        double lambda1 = Math.toRadians(lon1);
        double lambda2 = Math.toRadians(lon2);
        double deltaLambda = lambda2 - lambda1;

        double a = haversine(deltaPhi) + Math.cos(phi1) * Math.cos(phi2) * haversine(deltaLambda);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private double haversine(double radian) {
        return Math.pow(Math.sin(radian / 2), 2);
    }
}
