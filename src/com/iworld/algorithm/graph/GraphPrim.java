package com.iworld.algorithm.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 最小生成树 prim（普利姆）算法 p算法
 * 随便一个点 找出该点所有出度边 找出最小出度边所连接的点 ，如果该点已经被找出 不在添加
 * 点-最小权重边-点......
 * 1.随机找出一个点
 * 2.然后将点的所有出度边添加到小根堆
 * 3.从小根堆弹出最小权重边，如果到达点未使用则找出当前点的所有未使用出度边 放入小根堆
 * @date 2022/4/16 16:52
 */
public class GraphPrim {
    
    public static Set<Graph.Edge> prim(Graph<String> graph) {
        if (graph == null || graph.nodeMap != null) {
            return null;
        }
        // 结果边集
        Set<Graph.Edge> result = new HashSet<>();
        // 已经使用过的点
        Set<Graph.Node<String>> nodes = new HashSet<>();
        // 边的小根堆
        PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 使用for循环 解决森林问题 如果非森林 直接选择一个点直接可用
        for (Graph.Node<String> node : graph.nodeMap.values()) {
            // 找出一个点
            if (!nodes.contains(node)) {
                nodes.add(node);
                // 将点的出度边放入小根堆
                priorityQueue.addAll(node.edges);
                while (!priorityQueue.isEmpty()) {
                    // 弹出一个最小权重的边
                    Graph.Edge poll = priorityQueue.poll();
                    // 如果当前边的到达点没有使用
                    if (!nodes.contains(poll.to)) {
                        // 将当前边放入返回结果中
                        result.add(poll);
                        // 当前边到的点放入已使用点集
                        nodes.add(poll.to);
                        // 将当前筛选的点的所有出度边放入小根堆
                        priorityQueue.addAll(poll.to.nexts);
                    }
                }
            }
        }
        return result;
    }
    
    static class EdgeComparator implements Comparator<Graph.Edge> {
    
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
