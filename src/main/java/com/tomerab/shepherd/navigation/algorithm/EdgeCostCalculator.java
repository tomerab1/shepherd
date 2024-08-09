package com.tomerab.shepherd.navigation.algorithm;

import com.tomerab.shepherd.navigation.algorithm.cost.CostAlgorithm;
import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public class EdgeCostCalculator {
    private WeightedEdge edge;
    private CostAlgorithm algo;

    public EdgeCostCalculator(WeightedEdge edge, CostAlgorithm algo) {
        this.edge = edge;
        this.algo = algo;
    }

    public EdgeCostCalculator(CostAlgorithm algo) {
        this.algo = algo;
    }

    public void setEdge(WeightedEdge edge) {
        this.edge = edge;
    }

    public double calculate() {
        return algo.calculate(edge);
    }
}
