package com.tomerab.shepherd;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tomerab.shepherd.navigation.algorithm.ClosestNodeFinder;
import com.tomerab.shepherd.navigation.algorithm.PathPlanner;
import com.tomerab.shepherd.navigation.algorithm.graph.DiGraph;
import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;
import com.tomerab.shepherd.navigation.algorithm.pathfinding.AStar;
import com.tomerab.shepherd.navigation.graphconstructor.DiGraphConstructor;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    private final DiGraphConstructor graphConstructor = new DiGraphConstructor("./data/nes-zionna.osm");
    private final DiGraph graph = graphConstructor.construct();
    private final PathPlanner pathPlanner = new PathPlanner(new AStar(graph));

    @GetMapping("/closest-node")
    public GraphNode closestNode() {
        ClosestNodeFinder finder = new ClosestNodeFinder(31.92620, 34.78415);
        return finder.findClosestNode(graph);

        // var res = pathPlanner.plan(2032864113l, 2339662116l);
        // return res;
    }

    @GetMapping("/graph")
    public List<GraphNode> graph() {
        var res = pathPlanner.plan(2232375653l, 3885782146l);
        return res;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
