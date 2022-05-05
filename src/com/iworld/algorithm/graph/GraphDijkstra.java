package com.iworld.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 迪克斯特拉算法  最短路径  必须指定一个点
 * 1.生成一个源点到各个点的最小距离表，开始只有自己到自己点的距离为0
 * 2.从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离
 * 3.源点到所有点记录全部拿一遍 则结束
 * @date 2022/4/17 21:48
 */
public class GraphDijkstra {
    
    public static Map<Graph.Node<Integer>, Integer> dijkstra(Graph.Node<Integer> node) {
        if (node == null) {
            return null;
        }
        // 当前节点到各个节点距离表
        Map<Graph.Node<Integer>, Integer> distanceMap = new HashMap<>();
        distanceMap.put(node, 0);
        Graph.Node<Integer> first = node;
        // 已经使用的节点
        Set<Graph.Node> selectNodes = new HashSet<>();
        while (first != null) {
            Integer distance = distanceMap.get(first);
            for (Graph.Edge edge : first.edges) {
                Graph.Node<Integer> to = edge.to;
                Integer toDistance = distanceMap.get(to);
                // 还未有到当前点距离 或者新的距离小于原来距离
                if (toDistance == null || (distance + edge.weight) < toDistance) {
                    distanceMap.put(to, distance + edge.weight);
                }
            }
            selectNodes.add(first);
            // 找出未使用最小距离的点
            first = selectMinWeightNode(distanceMap, selectNodes);
        }
        return distanceMap;
    }
    
    /**
     * 找出最小距离的点
     * @param distanceMap
     * @param selectNodes
     * @return
     */
    private static Graph.Node<Integer> selectMinWeightNode(Map<Graph.Node<Integer>, Integer> distanceMap, Set<Graph.Node> selectNodes) {
        int min = Integer.MAX_VALUE;
        Graph.Node<Integer> result = null;
        for (Map.Entry<Graph.Node<Integer>, Integer> entry : distanceMap.entrySet()) {
            if (!selectNodes.contains(entry.getKey()) && entry.getValue() < min) {
                result = entry.getKey();
                min = entry.getValue();
            }
        }
        return result;
    }
    
    /**
     * @see com.iworld.algorithm.graph.GraphDijkstraHeap
     * 使用堆优化
     * @param node
     * @return
     */
    public static Map<Graph.Node<Integer>, Integer> dijkstraUseHeap(Graph.Node<Integer> node) {
        if (node == null) {
            return null;
        }
        GraphDijkstraHeap<Integer> heap = new GraphDijkstraHeap<>(node.nexts.size());
        // 当前节点到各个节点距离表
        heap.push(node, 0);
        while (!heap.isEmpty()) {
            GraphDijkstraHeap.NodeRecord<Integer> pop = heap.pop();
            // 维护开始节点到当前节点的距离
            Integer distance = pop.distance;
            // 当前节点下所有的出度边
            for (Graph.Edge edge : pop.node.edges) {
                Graph.Node<Integer> to = edge.to;
                heap.push(to, distance + edge.weight);
            }
        }
        return heap.getDistanceMap();
    }
    
    public static void main(String[] args) {
        int[][] ints = {{1, 2, 3}, {1, 3, 10}, {1, 4, 2}, {2, 3, 6}, {3, 5, 3}, {4, 3, 1}, {4, 5, 5}};
        Graph<Integer> graph = GraphGenerator.createGraph(ints);
        Graph.Node<Integer> integerNode = graph.nodeMap.get(1);
        Map<Graph.Node<Integer>, Integer> dijkstra = dijkstra(integerNode);
        System.out.println(dijkstra);
        Map<Graph.Node<Integer>, Integer> dijkstraUseHeap = dijkstraUseHeap(integerNode);
        System.out.println(dijkstraUseHeap);
    }
}
