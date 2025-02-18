package chapter22;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static Vertex[] buildGraph() {
        Vertex r = new Vertex(0, "r", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex s = new Vertex(0, "s", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex t = new Vertex(0, "t", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex u = new Vertex(0, "u", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex v = new Vertex(0, "v", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex w = new Vertex(0, "w", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex x = new Vertex(0, "x", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex y = new Vertex(0, "y", new LinkedList<>(), Color.WHITE, -1, null, -1);

        r.adjacencyList.add(s);
        r.adjacencyList.add(v);

        s.adjacencyList.add(r);
        s.adjacencyList.add(w);

        t.adjacencyList.add(u);
        t.adjacencyList.add(w);
        t.adjacencyList.add(x);

        u.adjacencyList.add(t);
        u.adjacencyList.add(x);
        u.adjacencyList.add(y);

        v.adjacencyList.add(r);

        w.adjacencyList.add(s);
        w.adjacencyList.add(t);
        w.adjacencyList.add(x);

        x.adjacencyList.add(t);
        x.adjacencyList.add(u);
        x.adjacencyList.add(w);
        x.adjacencyList.add(y);

        y.adjacencyList.add(u);
        u.adjacencyList.add(x);

        Vertex[] graph = new Vertex[8];
        graph[0] = r;
        graph[1] = s;
        graph[2] = t;
        graph[3] = u;
        graph[4] = v;
        graph[5] = w;
        graph[6] = x;
        graph[7] = y;

        return graph;
    }

    public static void BFS(Vertex[] graph, Vertex s) {
        for (int i = 0; i < graph.length; i++) {
            graph[i].color = Color.WHITE;
            graph[i].discoveryDistance = -1;
            graph[i].predecessor = null;
        }

        s.color = Color.GRAY;
        s.discoveryDistance = 0;
        s.predecessor = null;

        Queue<Vertex> nextIterationVertexQueue = new ArrayDeque<>(8);
        nextIterationVertexQueue.offer(s);

        printGraphAndNextIterationVertexQueue(graph, nextIterationVertexQueue);

        while (nextIterationVertexQueue.size() > 0) {
            Vertex u = nextIterationVertexQueue.poll();
            printGraphAndNextIterationVertexQueue(graph, nextIterationVertexQueue);
            for (Vertex v : u.adjacencyList) {
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.discoveryDistance = u.discoveryDistance + 1;
                    v.predecessor = u;
                    nextIterationVertexQueue.offer(v);
                    printGraphAndNextIterationVertexQueue(graph, nextIterationVertexQueue);
                }
            }
            u.color = Color.BLACK;
            printGraphAndNextIterationVertexQueue(graph, nextIterationVertexQueue);
        }
    }

    private static void printGraphAndNextIterationVertexQueue(Vertex[] graph, Queue<Vertex> nextIterationVertexQueue) {
        for (int i = 0; i < graph.length / 2; i++) {
            System.out.print(graph[i].name + "\t\t");
        }
        System.out.println();

        for (int i = 0; i < graph.length / 2; i++) {
            System.out.print(graph[i].color + "\t");
        }
        System.out.println();

        for (int i = 0; i < graph.length / 2; i++) {
            System.out.print(graph[i].discoveryDistance + "\t\t");
        }
        System.out.println();

        for (int i = graph.length / 2; i < graph.length; i++) {
            System.out.print(graph[i].name + "\t\t");
        }
        System.out.println();

        for (int i = graph.length / 2; i < graph.length; i++) {
            System.out.print(graph[i].color + "\t");
        }
        System.out.println();

        for (int i = graph.length / 2; i < graph.length; i++) {
            System.out.print(graph[i].discoveryDistance + "\t\t");
        }
        System.out.println();

        for (Vertex vertex : nextIterationVertexQueue) {
            System.out.print(vertex.name);
        }
        System.out.println();
        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Vertex[] graph = buildGraph();

        BFS(graph, graph[1]);
    }
}
