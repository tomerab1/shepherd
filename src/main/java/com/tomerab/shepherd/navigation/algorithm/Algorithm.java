package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public interface Algorithm {
    public double calculate(WeightedEdge e1);
}
