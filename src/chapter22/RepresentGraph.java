package chapter22;

import java.util.LinkedList;

/**
 * 
 * 如何在计算机中装载一个图
 * 根据画法几何点构成线，线构成面，面构成空间
 * 所以要在计算机中装载一个图，需要装载图中的点和线
 * 
 * 算法导论提供了两种通用的实现
 * 1.通过LinkedList和Set来实现，其中LinkedList的开头为一个点，后面的点是与其相连的点，所有的LinkedList加在一起构成了Set
 * 2.通过Matrix来实现，注意这里的行跟列都是多有的点
 * 
 * 算法导论提供了两种通用的实现的加权扩展
 * 1.对于list实现的通过增加权值来实现
 * 2.通过新建一个权值的Matrix来实现
 * 
 * 算法导论提供了两种通用的实现的比较
 * 1.适用于比较稀疏的情况
 * 2.适用于比较稠密的情况
 * 
 * 对于面向对象的语言而言
 * 可以把点设计成对象，把线设计成对象
 * 
 * @author 滑德友
 * @since 2018年6月26日07:55:15
 *
 */
public class RepresentGraph {

    public static void main(String[] args) {
        Vertex[] graphList = buildAdjacencyListRepresentGraph();
        int[][] graphMatrix = buildMatrixRepresentGraph();

        System.out.println(graphList[0]);
    }

    private static Vertex[] buildAdjacencyListRepresentGraph() {
        Vertex vertex0 = new Vertex(0, "1", new LinkedList<>(), null, -1, null, -1);
        Vertex vertex1 = new Vertex(0, "1", new LinkedList<>(), null, -1, null, -1);
        Vertex vertex2 = new Vertex(0, "1", new LinkedList<>(), null, -1, null, -1);
        Vertex vertex3 = new Vertex(0, "1", new LinkedList<>(), null, -1, null, -1);
        Vertex vertex4 = new Vertex(0, "1", new LinkedList<>(), null, -1, null, -1);

        vertex0.adjacencyList.add(vertex1);
        vertex0.adjacencyList.add(vertex4);

        vertex1.adjacencyList.add(vertex0);
        vertex1.adjacencyList.add(vertex2);
        vertex1.adjacencyList.add(vertex3);
        vertex1.adjacencyList.add(vertex4);

        vertex2.adjacencyList.add(vertex1);
        vertex2.adjacencyList.add(vertex3);

        vertex3.adjacencyList.add(vertex1);
        vertex3.adjacencyList.add(vertex2);
        vertex3.adjacencyList.add(vertex4);

        vertex4.adjacencyList.add(vertex0);
        vertex4.adjacencyList.add(vertex1);
        vertex4.adjacencyList.add(vertex3);

        Vertex[] graphList = new Vertex[5];
        graphList[0] = vertex0;
        graphList[1] = vertex1;
        graphList[2] = vertex2;
        graphList[3] = vertex3;
        graphList[4] = vertex4;
        return graphList;
    }

    private static int[][] buildMatrixRepresentGraph() {
        int[][] graphMatrix = new int[5][5];
        graphMatrix[0][1] = 1;
        graphMatrix[0][4] = 1;

        graphMatrix[1][0] = 1;
        graphMatrix[1][2] = 1;
        graphMatrix[1][3] = 1;
        graphMatrix[1][4] = 1;

        graphMatrix[2][1] = 1;
        graphMatrix[2][3] = 1;

        graphMatrix[3][1] = 1;
        graphMatrix[3][2] = 1;
        graphMatrix[3][4] = 1;

        graphMatrix[4][0] = 1;
        graphMatrix[4][1] = 1;
        graphMatrix[4][3] = 1;

        return graphMatrix;
    }

}
