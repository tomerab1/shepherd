package com.tomerab.shepherd.navigation.algorithm.cost;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

/**
 * Represents an algorithm for calculating a value based on a weighted edge.
 */
public interface CostAlgorithm {
    public double calculate(WeightedEdge e1);

    public double calculate(double lon1, double lat1, double lon2, double lat2);
}
