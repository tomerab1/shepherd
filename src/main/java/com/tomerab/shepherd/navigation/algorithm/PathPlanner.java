package com.tomerab.shepherd.navigation.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;
import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;
import com.tomerab.shepherd.navigation.algorithm.graph.DiGraph;
import com.tomerab.shepherd.navigation.graphconstructor.DiGraphConstructor;

public class PathPlanner {
    private DiGraphConstructor graphConstructor;
    private DiGraph graph;

    public PathPlanner(String dataPath) {
        graphConstructor = new DiGraphConstructor(dataPath);
        graph = graphConstructor.construct();
        calculateWeights();
    }

    public void calculateWeights() {
        var adjListIter = graph.getAdjList().iterator();

        while (adjListIter.hasNext()) {
            var next = adjListIter.next();
            var neighbors = next.getValue();

            if (neighbors.size() > 1) {
                neighbors.forEach((edge) -> {
                    EdgeCostCalculator calculator = new EdgeCostCalculator(edge, new HaversineCalcAlgo());
                    edge.setWeight(calculator.calculate());
                });
            }
        }
    }

    // PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(
    // (e1, e2) -> Double.compare(e1.getWeight(), e2.getWeight()));
    public List<GraphNode> plan(GraphNode source, GraphNode dest) {
        Set<GraphNode> closedSet = new HashSet<>();
        Set<GraphNode> openSet = new HashSet<>();
        openSet.add(source);

        List<GraphNode> cameFrom = new ArrayList<>();
        HashMap<Long, Double> gScore = new HashMap<>();
        gScore.put(source.getId(), 0.0);

        HashMap<Long, Double> fScore = new HashMap<>();
        WeightedEdge sourceToDest = new WeightedEdge(source, dest);
        EdgeCostCalculator calculator = new EdgeCostCalculator(sourceToDest, new DistanceCalcAlgo());
        fScore.put(source.getId(), gScore.get(source.getId()) + calculator.calculate());

        while (!openSet.isEmpty()) {

        }

        return null;
    }
}
