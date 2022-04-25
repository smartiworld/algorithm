package com.iworld.algorithm.unionset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 并查集
 * 在并查集中开始认为每一个样本都单独存在集合中，
 * boolean isSameSet(v,x) 查询样本v和x是否同一个集合
 * void union(x, y)将x和y所在集合合并成一个集合
 * @date 2022/4/10 16:21
 */
public class UnionSet<V> {
    
    Map<V, Node<V>> curMap = new HashMap<>();
    
    Map<Node<V>, Node<V>> parentMap = new HashMap<>();
    
    Map<Node<V>, Integer> vSetSize = new HashMap<>();
    
    public UnionSet(List<V> vs) {
        for (int i = 0; i < vs.size(); i++) {
            Node<V> node = new Node<>(vs.get(i));
            curMap.put(vs.get(i), node);
            parentMap.put(node, node);
            vSetSize.put(node, 1);
        }
    }
    
    public boolean isSameSet(V x, V y) {
        if (!curMap.containsKey(x) || !curMap.containsKey(y)) {
            return false;
        }
        return findParent(curMap.get(x)) == findParent(curMap.get(y));
    }
    
    public void union(V x, V y) {
        if (!curMap.containsKey(x) || !curMap.containsKey(y)) {
            return ;
        }
        Node<V> xParent = findParent(curMap.get(x));
        Node<V> yParent = findParent(curMap.get(y));
        if (xParent != yParent) {
            int xParentSize = vSetSize.get(xParent);
            int yParentSize = vSetSize.get(yParent);
            Node<V> bigParent = xParentSize > yParentSize ? xParent : yParent;
            Node<V> smallParent = bigParent == xParent ? yParent : xParent;
            parentMap.put(smallParent, bigParent);
            vSetSize.put(bigParent, xParentSize + yParentSize);
            vSetSize.remove(smallParent);
//            if (xParentSize < yParentSize) {
//                parentMap.put(xParent, yParent);
//                vSetSize.put(yParent, xParentSize + yParentSize);
//                vSetSize.remove(xParent);
//            } else {
//                parentMap.put(yParent, xParent);
//                vSetSize.put(xParent, xParentSize + yParentSize);
//                vSetSize.remove(yParent);
//            }
        }
    }
    
    private Node<V> findParent(Node<V> x) {
        Node<V> parent = x;
        Stack<Node<V>> stack = new Stack<>();
        while (parent != parentMap.get(x)) {
            // 将当前路径所有非父节点放入栈中
            stack.push(parent);
            parent = parentMap.get(x);
        }
        // 将路径上所有节点的父节点直接执向父节点
        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), parent);
        }
        return parent;
    }
    
    public int size() {
        return vSetSize.size();
    }
    
    static class Node<V> {
        V v;
        Node(V v) {
            this.v = v;
        }
    }
    
    public static void main(String[] args) {
    
    }
}
