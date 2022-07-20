package com.iworld.algorithm.list.ordered;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author gq.cai
 * @version 1.0
 * @description 1206. Design Skiplist   Hard
 * Design a Skiplist without using any built-in libraries.
 *
 * A skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists is just simple linked lists.
 *
 * For example, we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist works this way:
 *
 *
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 *
 * You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add, erase and search can be faster than O(n). It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).
 *
 * See more about Skiplist: https://en.wikipedia.org/wiki/Skip_list
 *
 * Implement the Skiplist class:
 *
 * Skiplist() Initializes the object of the skiplist.
 * bool search(int target) Returns true if the integer target exists in the Skiplist or false otherwise.
 * void add(int num) Inserts the value num into the SkipList.
 * bool erase(int num) Removes the value num from the Skiplist and returns true. If num does not exist in the Skiplist, do nothing and return false. If there exist multiple num values, removing any one of them is fine.
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 *
 * Example 1:
 *
 * Input
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * Output
 * [null, null, null, null, false, null, true, false, true, false]
 *
 * Explanation
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0); // return False
 * skiplist.add(4);
 * skiplist.search(1); // return True
 * skiplist.erase(0);  // return False, 0 is not in skiplist.
 * skiplist.erase(1);  // return True
 * skiplist.search(1); // return False, 1 has already been erased.
 *
 * Constraints:
 *
 * 0 <= num, target <= 2 * 104
 * At most 5 * 104 calls will be made to search, add, and erase.
 * https://leetcode.com/problems/design-skiplist/
 * @date 2022/7/20 14:25
 */
public class SkipListSet {
    
    private Node head;
    private static final double LEVEL_FACTOR = 0.5;
    private int maxLevel;
    
    public SkipListSet() {
        head = new Node(Integer.MIN_VALUE);
        head.next.add(null);
        maxLevel = 0;
    }
    
    public boolean search(int target) {
        Node lessNode = findLessMostRightInTree(target);
        return lessNode.next.get(0) != null && lessNode.next.get(0).v == target;
    }
    
    public void add(int num) {
        int newLevel = 0;
        while (Math.random() < LEVEL_FACTOR) {
            newLevel++;
        }
        while (newLevel > maxLevel) {
            maxLevel++;
            head.next.add(null);
        }
        Node node = new Node(num);
        for (int i = 0; i <= newLevel; i++) {
            node.next.add(null);
        }
        int level = maxLevel;
        Node pre = head;
        while (level >= 0) {
            pre = findLessMostRightInLevel(pre, level, num);
            if (level <= newLevel) {
                node.next.set(level, pre.next.get(level));
                pre.next.set(level, node);
            }
            level--;
        }
    }
    
    public boolean erase(int num) {
        boolean isDelete = search(num);
        if (isDelete) {
            int level = maxLevel;
            Node pre = head;
            while (level >= 0) {
                pre = findLessMostRightInLevel(pre, level, num);
                if (pre.next.get(level) != null && pre.next.get(level).v == num) {
                    pre.next.set(level, pre.next.get(level).next.get(level));
                }
                if (level != 0 && pre.next.get(level) == null && pre == head) {
                    pre.next.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }
        return isDelete;
    }
    
    private Node findLessMostRightInTree(int num) {
        int level = maxLevel;
        Node pre = head;
        while (level >= 0) {
            pre = findLessMostRightInLevel(pre, level--, num);
        }
        return pre;
    }
    
    private Node findLessMostRightInLevel(Node cur, int level, int target) {
        Node pre = cur;
        Node next = cur.next.get(level);
        while (next != null && next.v < target) {
            pre = next;
            next = next.next.get(level);
        }
        return pre;
    }
    
    static class Node {
        int v;
        ArrayList<Node> next;
        
        Node(int v) {
            this.v = v;
            next = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //["Skiplist","add","add","add","add","search","erase","search","search","search"]
        //[[],[0],[5],[2],[1],[0],[5],[2],[3],[2]]
        String[] methodNames = {"add","add","add","add","search","erase","search","search","search"};
        int[][] param = {{0},{5},{2},{1},{0},{5},{2},{3},{2}};
        Class<SkipListSet> skipListSetClass = SkipListSet.class;
        SkipListSet skipListSet = skipListSetClass.getDeclaredConstructor().newInstance();
        for (int i = 0; i < methodNames.length; i++) {
            Method declaredMethod = skipListSetClass.getDeclaredMethod(methodNames[i], int.class);
            declaredMethod.setAccessible(true);
            System.out.println(declaredMethod.getName());
            Object invoke = declaredMethod.invoke(skipListSet, param[i][0]);
            System.out.println(invoke);
            System.out.println("==================");
        }
        
    }
}
