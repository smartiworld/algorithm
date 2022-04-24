package com.iworld.algorithm.graph;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/4/14 22:39
 */
public class GraphGenerator {
    
    public static Graph<Integer> createGraph(int[][] matrix) {
        Graph<Integer> graph = new Graph<>();
        for (int i = 0; i < matrix.length; i++) {
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = matrix[i][2];
            // 构造进出点
            if (!graph.nodeMap.containsKey(from)) {
                graph.nodeMap.put(from, new Graph.Node<>(from));
            }
            if (!graph.nodeMap.containsKey(to)) {
                graph.nodeMap.put(to, new Graph.Node<>(to));
            }
            Graph.Node<Integer> fromNode = graph.nodeMap.get(from);
            Graph.Node<Integer> toNode = graph.nodeMap.get(to);
            Graph.Edge<Integer> edge = new Graph.Edge<>(fromNode, toNode, weight);
            fromNode.edges.add(edge);
            fromNode.nexts.add(toNode);
            fromNode.out ++;
            toNode.in ++;
            graph.edges.add(edge);
        }
        return graph;
    }
}
