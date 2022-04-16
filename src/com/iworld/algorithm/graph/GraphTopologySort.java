package com.iworld.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 图的拓扑排序
 * 无环图
 * 1.将图点的节点和入度放map中 将入度为0的节点放入队列中
 * 2.弹出队列中入度为0的节点，将弹出节点出度节点的入度分别减1，此时如果有入度为0的节点 则放入队列
 * @date 2022/4/15 22:41
 */
public class GraphTopologySort {
    
    public static List<Graph.Node<String>> topologySort(Graph<String> graph) {
        if (graph == null || graph.nodeMap == null) {
            return null;
        }
        List<Graph.Node<String>> result = new ArrayList<>();
        // 存放入度为0的点 宽度方式---- 也可以栈 深度方式
        Queue<Graph.Node<String>> queue = new LinkedList<>();
        // 记录图中点的入度
        Map<Graph.Node<String>, Integer> inCounter = new HashMap<>();
        // 维护所有点的入度  将入度为0的点放队列
        for (Graph.Node<String> node : graph.nodeMap.values()) {
            inCounter.put(node, node.in);
            if (node.in == 0) {
                queue.offer(node);
            }
        }
        while (!queue.isEmpty()) {
            Graph.Node<String> poll = queue.poll();
            result.add(poll);
            for (Graph.Node<String> next : poll.nexts) {
                Integer in = inCounter.get(next);
                inCounter.put(next, --in);
                if (in == 0) {
                    queue.add(next);
                }
            }
        }
        return result;
    }
}
