package chapter22;

import java.util.List;

public class Vertex {

    protected int index;

    protected String name;

    protected List<Vertex> adjacencyList;

    protected Color color;

    protected int discoveryDistance;

    protected Vertex predecessor;

    protected int finishDistance;

    protected Vertex(int index, String name, List<Vertex> adjacencyList, Color color, int discoveryDistance, Vertex predecessor, int finishDistance) {
        this.index = index;
        this.name = name;
        this.adjacencyList = adjacencyList;
        this.color = color;
        this.discoveryDistance = discoveryDistance;
        this.predecessor = predecessor;
        this.finishDistance = finishDistance;
    }
}
