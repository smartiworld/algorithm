package com.iworld.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 图的基本数据结构
 * @date 2022/4/14 21:53
 */
public class Graph<V> {
    /**
     * 点值对应的节点
     */
    public Map<V, Node<V>> nodeMap;
    /**
     * 图的边集
     */
    public Set<Edge> edges;
    
    public Graph() {
        this.nodeMap = new HashMap<>();
        this.edges = new HashSet<>();
    }
    
    /**
     * 图中节点数据结构
     */
    public static class Node<V> {
        /**
         * 点的值
         */
        public V value;
        /**
         *入度
         */
        public int in;
        /**
         * 出度
         */
        public int out;
        /**
         * 出度连着的点
         */
        public List<Node> nexts;
        /**
         * 出度连着的边
         */
        public List<Edge> edges;
        
        public Node(V v) {
            this.value = v;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }
    
    /**
     * 图中边的数据结构
     */
    public static class Edge<V> {
        /**
         * 开始连接的节点
         */
        public Node<V> from;
        /**
         * 结束连接的节点
         */
        public Node<V> to;
        /**
         * 边的权重
         */
        public int weight;
        
        public Edge(Node<V> from, Node<V> to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
    }
}
