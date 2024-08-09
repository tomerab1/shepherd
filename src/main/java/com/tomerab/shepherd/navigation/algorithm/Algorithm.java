package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

/**
 * Represents an algorithm for calculating a value based on a weighted edge.
 */
public interface Algorithm {
    public double calculate(WeightedEdge e1);
}
