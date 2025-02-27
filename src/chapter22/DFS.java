package chapter22;

import java.util.LinkedList;
import java.util.Queue;

public class DFS {

    public static Vertex[] buildGraph() {
        Vertex a = new Vertex(0, "a", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex b = new Vertex(0, "b", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex c = new Vertex(0, "c", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex d = new Vertex(0, "d", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex e = new Vertex(0, "e", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex f = new Vertex(0, "f", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex g = new Vertex(0, "g", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex h = new Vertex(0, "h", new LinkedList<>(), Color.WHITE, -1, null, -1);

        a.adjacencyList.add(b);

        b.adjacencyList.add(c);
        b.adjacencyList.add(e);
        b.adjacencyList.add(f);

        c.adjacencyList.add(d);
        c.adjacencyList.add(g);

        d.adjacencyList.add(c);
        d.adjacencyList.add(h);

        e.adjacencyList.add(a);
        e.adjacencyList.add(f);

        f.adjacencyList.add(g);

        g.adjacencyList.add(f);
        g.adjacencyList.add(h);

        h.adjacencyList.add(h);

        Vertex[] graph = new Vertex[8];
        graph[0] = a;
        graph[1] = b;
        graph[2] = c;
        graph[3] = d;
        graph[4] = e;
        graph[5] = f;
        graph[6] = g;
        graph[7] = h;

        return graph;
    }

    public static void DFS(Vertex[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i].color = Color.WHITE;
            graph[i].predecessor = null;
        }

        int time = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].color == Color.WHITE) {
                DFSVisit(graph, graph[i], time);
            }
        }

    }

    private static int DFSVisit(Vertex[] graph, Vertex u, int time) {
        time = time + 1;
        u.discoveryDistance = time;
        u.color = Color.GRAY;

        printGraph(graph);
        for (Vertex v : u.adjacencyList) {
            if (v.color == Color.WHITE) {
                v.predecessor = u;
                time = DFSVisit(graph, v, time);
                printGraph(graph);
            }
        }

        u.color = Color.BLACK;
        time = time + 1;
        u.finishDistance = time;

        printGraph(graph);

        return time;
    }

    private static void printGraph(Vertex[] graph) {
        for (int i = 0; i < graph.length / 2; i++) {
            System.out.print(graph[i].name + "\t\t");
        }
        System.out.println();

        for (int i = 0; i < graph.length / 2; i++) {
            System.out.print(graph[i].color + "\t");
        }
        System.out.println();

        for (int i = 0; i < graph.length / 2; i++) {
            System.out.print(graph[i].discoveryDistance + "/" + graph[i].finishDistance + "\t");
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
            System.out.print(graph[i].discoveryDistance + "/" + graph[i].finishDistance + "\t");
        }
        System.out.println();

        System.out.println("-----------------------------------------------------");
    }

    private static void printAllPath(Vertex[] graph, Vertex s) {
        for (int i = 0; i < graph.length; i++) {
            printPath(graph, s, graph[i]);
            System.out.println();
        }
    }

    private static void printPath(Vertex[] graph, Vertex s, Vertex v) {
        if (v == s) {
            System.out.print(s.name);
        }
        else if (v.predecessor == null) {
            System.out.println("no path from " + s.name + " to " + v.name);
        }
        else {
            printPath(graph, s, v.predecessor);
            System.out.print(v.name);;
        }
    }

    public static void main(String[] args) {
        Vertex[] graph = buildGraph();

        DFS(graph);

        printAllPath(graph, graph[1]);
    }
}
