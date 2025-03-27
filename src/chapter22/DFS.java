package chapter22;

import java.util.Iterator;
import java.util.LinkedList;

public class DFS {

    public static Vertex[] buildGraph() {
        Vertex a = new Vertex(0, "a", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex b = new Vertex(1, "b", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex c = new Vertex(2, "c", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex d = new Vertex(3, "d", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex e = new Vertex(4, "e", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex f = new Vertex(5, "f", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex g = new Vertex(6, "g", new LinkedList<>(), Color.WHITE, -1, null, -1);
        Vertex h = new Vertex(7, "h", new LinkedList<>(), Color.WHITE, -1, null, -1);

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

    public static LinkedList<Vertex> DFS(Vertex[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i].color = Color.WHITE;
            graph[i].predecessor = null;
        }

        int time = 0;
        LinkedList<Vertex> dfsSort = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].color == Color.WHITE) {
                DFSVisit(graph, graph[i], time, dfsSort);
            }
        }

        return dfsSort;
    }

    private static int DFSVisit(Vertex[] graph, Vertex u, int time, LinkedList<Vertex> dfsSort) {
        time = time + 1;
        u.discoveryDistance = time;
        u.color = Color.GRAY;

        printGraph(graph);
        for (Vertex v : u.adjacencyList) {
            if (v.color == Color.WHITE) {
                v.predecessor = u;
                time = DFSVisit(graph, v, time, dfsSort);
                printGraph(graph);
            }
        }

        u.color = Color.BLACK;
        time = time + 1;
        u.finishDistance = time;
        dfsSort.addFirst(u);

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

    private static void printDFSSort(LinkedList<Vertex> dfsSort) {
        System.out.println("-----------------------------------------------------");
        Iterator<Vertex> iterator = dfsSort.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().name);
        }
        System.out.println();
        System.out.println("-----------------------------------------------------");
    }

    private static Vertex[] buildGraphT(Vertex[] graph) {
        Vertex[] graphT = new Vertex[graph.length];
        for (int i = 0; i < graph.length; i++) {
            graphT[i] = new Vertex(i, graph[i].name, new LinkedList<>(), Color.WHITE, -1, null, -1);
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].adjacencyList.size(); j++) {
                Vertex v = graph[i].adjacencyList.get(j);
                graphT[v.index].adjacencyList.add(graphT[i]);
            }
        }

        return graphT;
    }

    private static LinkedList<LinkedList<Vertex>> DFST(Vertex[] graphT, LinkedList<Vertex> dfsSort) {
        for (int i = 0; i < graphT.length; i++) {
            graphT[i].color = Color.WHITE;
            graphT[i].predecessor = null;
        }

        int time = 0;
        LinkedList<LinkedList<Vertex>> strongConnectVertexList = new LinkedList<>();
        for (int i = 0; i < dfsSort.size(); i++) {
            if (graphT[dfsSort.get(i).index].color == Color.WHITE) {
                LinkedList<Vertex> strongConnectVertex = new LinkedList<>();
                DFSTVisit(graphT, graphT[dfsSort.get(i).index], time, strongConnectVertex);
                strongConnectVertexList.add(strongConnectVertex);
            }
        }

        return strongConnectVertexList;
    }


    private static int DFSTVisit(Vertex[] graph, Vertex u, int time, LinkedList<Vertex> strongConnectVertex) {
        time = time + 1;
        u.discoveryDistance = time;
        u.color = Color.GRAY;

        for (Vertex v : u.adjacencyList) {
            if (v.color == Color.WHITE) {
                v.predecessor = u;
                time = DFSTVisit(graph, v, time, strongConnectVertex);
            }
        }

        u.color = Color.BLACK;
        time = time + 1;
        u.finishDistance = time;
        strongConnectVertex.add(u);

        return time;
    }

    private static void printStrongConnectVertex(LinkedList<LinkedList<Vertex>> strongConnectVertexList) {
        System.out.println("-----------------------------------------------------");
        for (LinkedList<Vertex> strongConnectVertex : strongConnectVertexList) {
            for (Vertex vertex : strongConnectVertex) {
                System.out.print(vertex.name);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Vertex[] graph = buildGraph();

        LinkedList<Vertex> dfsSort = DFS(graph);

        printAllPath(graph, graph[0]);

        printDFSSort(dfsSort);

        Vertex[] graphT = buildGraphT(graph);

        LinkedList<LinkedList<Vertex>> strongConnectVertexList = DFST(graphT, dfsSort);

        printStrongConnectVertex(strongConnectVertexList);
    }


}
