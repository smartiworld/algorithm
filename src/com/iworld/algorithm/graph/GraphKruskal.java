package com.iworld.algorithm.graph;

import com.iworld.algorithm.unionset.UnionSetOpt;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 图的最小生成树 k算法  借助并查集
 * 1.将图的所有节点放入并查集中
 * 2.将图的所有边放入小根堆 按照边权重
 * 3.如果当前边的两个点不在同一个集合中 则选中当前边 将边上两个点放入同一个集合
 *
 * 如果是无向图 此时会少一半的边  如果需要在添加边的时候可以再复制反方向边放入结果集中
 * @date 2022/4/16 14:02
 */
public class GraphKruskal {
    
    
    public Set<Graph.Edge> kruskal(Graph<String> graph) {
        if (graph == null || graph.edges == null) {
            return null;
        }
        // 将图的所有节点放入并查集中
        UnionSetOpt<Graph.Node<String>> unionSetOpt = new UnionSetOpt<>(graph.nodeMap.values());
        Set<Graph.Edge> result = new HashSet<>();
        PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 将图的所有边放入小根堆 按照边权重
        priorityQueue.addAll(graph.edges);
        while (!priorityQueue.isEmpty()) {
            Graph.Edge poll = priorityQueue.poll();
            // 如果当前边的两个点不在同一个集合中 则选中当前边 将边上两个点放入同一个集合
            if (!unionSetOpt.isSameSet(poll.from, poll.to)) {
                result.add(poll);
                unionSetOpt.union(poll.from, poll.to);
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