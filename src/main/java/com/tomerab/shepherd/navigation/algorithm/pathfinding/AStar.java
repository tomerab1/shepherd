package com.tomerab.shepherd.navigation.algorithm.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.tomerab.shepherd.navigation.algorithm.EdgeCostCalculator;
import com.tomerab.shepherd.navigation.algorithm.cost.DistanceCalcAlgo;
import com.tomerab.shepherd.navigation.algorithm.cost.HaversineCalcAlgo;
import com.tomerab.shepherd.navigation.algorithm.graph.DiGraph;
import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;
import com.tomerab.shepherd.navigation.algorithm.graph.WeightedEdge;

public class AStar implements PathFindingAlgo {
    private DiGraph graph;

    public AStar(DiGraph graph) {
        this.graph = graph;
        calculateWeights();
    }

    private void calculateWeights() {
        var adjListIter = graph.getAdjList().iterator();

        while (adjListIter.hasNext()) {
            var next = adjListIter.next();
            var neighbors = next.getValue();
            neighbors.forEach((edge) -> {
                EdgeCostCalculator calculator = new EdgeCostCalculator(edge, new HaversineCalcAlgo());
                edge.setWeight(calculator.calculate());
            });
        }
    }

    public List<GraphNode> plan(long sourceId, long destId) {
        GraphNode source = graph.getNodeById(sourceId), dest = graph.getNodeById(destId);
        Map<GraphNode, GraphNode> cameFrom = new HashMap<>();
        Map<Long, Double> gScore = new HashMap<>();
        gScore.put(source.getId(), 0.0);

        Map<Long, Double> fScore = new HashMap<>();
        EdgeCostCalculator sourceToDestCalculator = new EdgeCostCalculator(new WeightedEdge(source, dest),
                new DistanceCalcAlgo());
        fScore.put(source.getId(), gScore.get(source.getId()) + sourceToDestCalculator.calculate());

        Set<GraphNode> closedSet = new HashSet<>();
        PriorityQueue<GraphNode> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(node -> fScore.get(node.getId())));
        openSet.add(source);

        while (!openSet.isEmpty()) {
            GraphNode current = openSet.poll();
            if (current.equals(dest)) {
                return reconstructPath(cameFrom, current);
            }

            closedSet.add(current);

            for (WeightedEdge neighborEdge : graph.getNeighbors(current.getId())) {
                GraphNode neighbor = neighborEdge.second();
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGScore = gScore.get(current.getId()) + neighborEdge.getWeight();

                if (!gScore.containsKey(neighbor.getId()) || tentativeGScore < gScore.get(neighbor.getId())) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor.getId(), tentativeGScore);

                    EdgeCostCalculator neighborToDestCalculator = new EdgeCostCalculator(neighborEdge,
                            new DistanceCalcAlgo());
                    fScore.put(neighbor.getId(), tentativeGScore + neighborToDestCalculator.calculate());

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private List<GraphNode> reconstructPath(Map<GraphNode, GraphNode> cameFrom, GraphNode current) {
        List<GraphNode> totalPath = new ArrayList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(current);
        }
        Collections.reverse(totalPath);
        return totalPath;
    }
}
