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
 * @description 207. Course Schedule
 * Medium
 * 11183
 * 438
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi
 * first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * https://leetcode.com/problems/course-schedule/
 * @date 2022/9/6 16:08
 */
public class CourseSchedule {
    
    static class Node {
        int val;
        int in;
        List<Node> nexts = new ArrayList<>();
        
        Node(){
        }
        
        Node(int val) {
            this.val = val;
        }
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        if (n == 0 || n == 1) {
            return true;
        }
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            if (!nodeMap.containsKey(to)) {
                nodeMap.put(to, new Node(to));
            }
            int from = prerequisite[1];
            if (!nodeMap.containsKey(from)) {
                nodeMap.put(from, new Node(from));
            }
            Node toNode = nodeMap.get(to);
            Node fromNode = nodeMap.get(from);
            fromNode.nexts.add(toNode);
            toNode.in++;
        }
        int needCount = nodeMap.size();
        Queue<Node> queue = new LinkedList<>();
        for (Node node : nodeMap.values()) {
            if (node.in == 0) {
                queue.add(node);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            count++;
            for (Node node : poll.nexts) {
                node.in--;
                if (node.in == 0) {
                    queue.add(node);
                }
            }
        }
        return count == needCount;
    }
    
    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] prerequisites = {{1,0},{0,1}};
        int numCourse = 2;
        System.out.println(courseSchedule.canFinish(numCourse, prerequisites));
    }
}
