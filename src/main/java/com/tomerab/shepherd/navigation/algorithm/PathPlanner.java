package com.tomerab.shepherd.navigation.algorithm;

import java.util.*;

import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;
import com.tomerab.shepherd.navigation.algorithm.pathfinding.PathFindingAlgo;

public class PathPlanner {
    private PathFindingAlgo pathFindingAlgo;

    public PathPlanner(PathFindingAlgo pathFindingAlgo) {
        this.pathFindingAlgo = pathFindingAlgo;
    }

    public List<GraphNode> plan(long sourceId, long destId) {
        return pathFindingAlgo.plan(sourceId, destId);
    }
}
