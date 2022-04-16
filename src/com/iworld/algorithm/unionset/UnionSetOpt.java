package com.iworld.algorithm.unionset;

import java.util.Collection;
import java.util.HashMap;
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
public class UnionSetOpt<V> {
    
    Map<V, V> parentMap = new HashMap<>();
    
    Map<V, Integer> vSetSize = new HashMap<>();
    
    public UnionSetOpt() {
    
    }
    
    public UnionSetOpt(Collection<V> vs) {
        for (V v : vs) {
            parentMap.put(v, v);
            vSetSize.put(v, 1);
        }
    }
    
    public boolean isSameSet(V x, V y) {
        if (!parentMap.containsKey(x) || !parentMap.containsKey(y)) {
            return false;
        }
        return findParent(x) == findParent(y);
    }
    
    public void union(V x, V y) {
        if (!parentMap.containsKey(x) || !parentMap.containsKey(y)) {
            return ;
        }
        V xParent = findParent(x);
        V yParent = findParent(y);
        if (xParent != yParent) {
            int xParentSize = vSetSize.get(xParent);
            int yParentSize = vSetSize.get(yParent);
            V bigParent = xParentSize > yParentSize ? xParent : yParent;
            V smallParent = bigParent == xParent ? yParent : xParent;
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
    
    private V findParent(V v) {
        V parent = v;
        Stack<V> stack = new Stack<>();
        while (parent != parentMap.get(v)) {
            // 将当前路径所有非父节点放入栈中
            stack.push(parent);
            parent = parentMap.get(v);
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
    
    public static void main(String[] args) {
    
    }
}
