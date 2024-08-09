package com.tomerab.shepherd.navigation.algorithm.pathfinding;

import java.util.List;

import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;

public interface PathFindingAlgo {
    public List<GraphNode> plan(long sourceId, long destId);
}
