package com.iworld.algorithm.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 图的遍历
 * @date 2022/4/14 23:13
 */
public class GraphIterator {
    
    /**
     * 图的宽度优先遍历BFS
     * 以图的一个节点开始
     * @param node
     */
    public static List<Integer> breathFirstSearch(Graph.Node<Integer> node) {
        if (node == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Queue<Graph.Node<Integer>> queue = new LinkedList<>();
        Set<Graph.Node<Integer>> nodes = new HashSet<>();
        queue.offer(node);
        nodes.add(node);
        while (!queue.isEmpty()) {
            Graph.Node<Integer> poll = queue.poll();
            result.add(poll.value);
            for (Graph.Node<Integer> next : poll.nexts) {
                if (!nodes.contains(next)) {
                    queue.offer(next);
                    nodes.add(next);
                }
            }
        }
        return result;
    }
    
    /**
     * 图的深度优先遍历
     * @param node
     * @return
     */
    public static List<Integer> depthFirstSearch(Graph.Node<Integer> node) {
        if (node == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Stack<Graph.Node<Integer>> stack = new Stack<>();
        Set<Graph.Node<Integer>> nodes = new HashSet<>();
        stack.push(node);
        nodes.add(node);
        result.add(node.value);
        while (!stack.isEmpty()) {
            Graph.Node<Integer> pop = stack.pop();
            for (Graph.Node<Integer> next : pop.nexts) {
                if(!nodes.contains(next)) {
                    nodes.add(next);
                    stack.push(pop);
                    stack.push(next);
                    result.add(next.value);
                    break;
                }
            }
        }
        return result;
    }
}
