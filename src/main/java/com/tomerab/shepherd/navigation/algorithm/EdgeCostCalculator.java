package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public class EdgeCostCalculator {
    private WeightedEdge edge;
    private Algorithm algo;

    public EdgeCostCalculator(WeightedEdge edge, Algorithm algo) {
        this.edge = edge;
        this.algo = algo;
    }

    public double calculate() {
        return algo.calculate(edge);
    }
}
