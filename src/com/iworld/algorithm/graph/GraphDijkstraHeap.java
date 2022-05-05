package com.iworld.algorithm.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 迪克斯特拉算法 优化  使用扩展栈减少时间复杂度
 * 小根堆
 * @date 2022/4/21 19:17
 */
public class GraphDijkstraHeap<V> {
    
    private Graph.Node<V>[] nodes;
    /**
     * 起始点到当前点的距离
     */
    Map<Graph.Node<V>, Integer> nodeDistance;
    /**
     * 当前节点在堆上索引
      */
    Map<Graph.Node<V>, Integer> nodeIndex;
    
    private int size = 0;
    
    public GraphDijkstraHeap(int length) {
        nodes = new Graph.Node[length];
        nodeDistance = new HashMap<>();
        nodeIndex = new HashMap<>();
    }
    
    public void push(Graph.Node<V> node, int distance) {
        // 堆已经满了
        if (size == nodes.length) {
            return;
        }
        // 还在堆上 重新repush
        if (isInHeap(node)) {
            rePush(node, distance);
        }
        if (!isEnter(node)) {
            nodes[size] = node;
            nodeDistance.put(node, distance);
            nodeIndex.put(node, size);
            upward(size ++);
        }
    }
    
    /**
     * 重新放入堆上节点
     *
     * @param node
     * @param distance
     */
    private void rePush(Graph.Node<V> node, int distance) {
        int index = nodeIndex.get(node);
        Graph.Node<V> curNode = nodes[index];
        int curDistance = nodeDistance.get(curNode);
        if (distance < curDistance) {
            nodeDistance.put(curNode, distance);
            upward(index);
        }
    }
    
    /**
     * 当前位置的元素 上浮
     * push元素时
     * @param index
     */
    private void upward(int index) {
        if (index <= 0) {
            return ;
        }
        int parentIndex = (index - 1) >> 1;
        while (parentIndex >= 0) {
            // 父节点的distance
            int parentDistance = nodeDistance.get(nodes[parentIndex]);
            int curDistance = nodeDistance.get(nodes[index]);
            if (curDistance < parentDistance) {
                swap(index, parentIndex);
            }
            parentIndex = (parentIndex - 1) >> 2;
        }
    }
    
    /**
     * 当前位置元素下沉
     */
    private void under(int index) {
        if (index >= nodes.length) {
            return ;
        }
        int leftIndex = (index << 1) + 1;
        while (leftIndex < size) {
            // 小节点
            int lessIndex = leftIndex;
            if (leftIndex + 1 < size && nodeDistance.get(nodes[leftIndex + 1]) < nodeDistance.get(nodes[leftIndex])) {
                lessIndex = leftIndex + 1;
            }
            if (nodeDistance.get(nodes[lessIndex]) < nodeDistance.get(nodes[index])) {
                swap(index, lessIndex);
            }
            leftIndex = (leftIndex << 1) + 1;;
        }
    }
    
    /**
     * 交换位置
     * 交换index
     * @param b
     * @param a
     */
    private void swap(int a, int b) {
        if (a == b) {
            return ;
        }
        Graph.Node<V> aNode = nodes[a];
        int aIndex = nodeIndex.get(aNode);
        Graph.Node<V> bNode = nodes[b];
        int bIndex = nodeIndex.get(bNode);
        nodes[a] = nodes[b];
        nodeIndex.put(aNode, bIndex);
        nodes[b] = aNode;
        nodeIndex.put(bNode, aIndex);
    }
    
    public boolean isEnter(Graph.Node<V> node) {
        return nodeIndex.containsKey(node);
    }
    
    public boolean isInHeap(Graph.Node<V> node) {
        return nodeIndex.containsKey(node) && nodeDistance.get(node) >= 0;
    }
    
    public NodeRecord<V> pop() {
        if (size == 0) {
            return null;
        }
        Graph.Node<V> result = nodes[0];
        // 将头节点位置换到尾部
        swap(0, --size);
        // 然后下沉新头部
        under(0);
        // 表示当前弹出节点曾经入栈过并且已经弹出
        nodeIndex.put(result, -1);
        NodeRecord<V> record = new NodeRecord<>();
        record.node = result;
        record.distance = nodeDistance.get(result);
        return record;
    }
    
    public static class NodeRecord<V> {
        
        public Graph.Node<V> node;
        
        public int distance;
    
        @Override
        public String toString() {
            return "NodeRecord{" +
                    "node=" + node.value +
                    ", distance=" + distance +
                    '}';
        }
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public Map<Graph.Node<V>, Integer> getDistanceMap() {
        return nodeDistance;
    }
}
