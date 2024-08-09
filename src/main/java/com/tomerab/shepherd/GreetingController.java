package com.tomerab.shepherd;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tomerab.shepherd.navigation.algorithm.PathPlanner;
import com.tomerab.shepherd.navigation.algorithm.graph.GraphNode;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/graph")
    public List<GraphNode> graph() {
        PathPlanner pathPlanner = new PathPlanner("./data/nes-zionna.osm");

        var res = pathPlanner.plan(2032864113l, 2339662116l);

        return res;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
