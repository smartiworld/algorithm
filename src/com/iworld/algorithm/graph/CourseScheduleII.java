package com.iworld.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 210. Course Schedule II
 * Medium
 * 7956
 * 267
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take.
 * To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * https://leetcode.com/problems/course-schedule-ii/
 * @date 2022/9/30 16:59
 */
public class CourseScheduleII {
    
    public static class Node {
        int val;
        int in;
        List<Node> nexts = new ArrayList<>();
        public Node(int val) {
            this.val = val;
        }
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return ans;
        }
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            if (nodeMap.get(to) == null) {
                nodeMap.put(to, new Node(to));
            }
            int from = prerequisite[1];
            if (nodeMap.get(from) == null) {
                nodeMap.put(from, new Node(from));
            }
            Node toNode = nodeMap.get(to);
            Node fromNode = nodeMap.get(from);
            toNode.in++;
            fromNode.nexts.add(toNode);
        }
        int index = 0;
        LinkedList<Node> queue = new LinkedList<>();
        // 0~numCourses-1 是需要选择的课程 如果当前需要依赖数组中没有 则直接放在结果数组中
        for (int i = 0; i < numCourses; i++) {
            // 如果当前需要依赖数组中没有 则直接放在结果数组中
            if (!nodeMap.containsKey(i)) {
                ans[index++] = i;
            } else {
                if (nodeMap.get(i).in == 0) {
                    queue.add(nodeMap.get(i));
                }
            }
        }
        int needPrerequisiteNums = nodeMap.size();
        int count = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            ans[index++] = cur.val;
            count++;
            for (Node next : cur.nexts) {
                if (--next.in == 0) {
                    queue.add(next);
                }
            }
        }
        return count == needPrerequisiteNums ? ans : new int[0];
    }
    
    public static void main(String[] args) {
        // {{3,0},{0,1}}
        // 4
        // 期望结果{2,1,0,3}
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int numCourses = 6;
        CourseScheduleII courseScheduleII = new CourseScheduleII();
        int[] ans = courseScheduleII.findOrder(numCourses, prerequisites);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
