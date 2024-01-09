package com.iworld.algorithm.util;

import com.iworld.algorithm.tree.CommonBinaryTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2024/1/8 19:51
 */
public class CommonBinaryTreeUtils {
    
    public static CommonBinaryTree<Integer> getIntegerBinaryTree(int level, int max) {
        if (level < 0) {
            return null;
        }
        return process(level, max, new HashSet<Integer>());
    }
    
    private static CommonBinaryTree<Integer> process(int level, int max, Set<Integer> set) {
        Random random = new Random();
        int val;
        do {
            val = random.nextInt(max) + 1;
        } while (set.contains(val));
        set.add(val);
        CommonBinaryTree<Integer> head = new CommonBinaryTree<>(val);
        if (level == 0) {
            return head;
        }
        head.left = process(level - 1, max, set);
        head.right = process(level - 1, max, set);
        return head;
    }
    
    public static <T> List<T> inOrder(CommonBinaryTree<T> root) {
        if (root == null) {
            return null;
        }
        List<T> ans = new ArrayList<>();
        Stack<CommonBinaryTree<T>> stack = new Stack<>();
        CommonBinaryTree<T> head = root;
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                CommonBinaryTree<T> pop = stack.pop();
                ans.add(pop.value);
                head = pop.right;
            }
        }
        return ans;
    }
    
    /**
     * 一次判断栈是否空
     * @param root
     * @param <T>
     * @return
     */
    public static <T> List<T> postOrder1(CommonBinaryTree<T> root) {
        if (root == null) {
            return null;
        }
        List<T> ans = new ArrayList<>();
        Stack<CommonBinaryTree<T>> stack = new Stack<>();
        CommonBinaryTree<T> head = root;
        CommonBinaryTree<T> point = head;
        stack.push(head);
        while (!stack.isEmpty()) {
            if (head.left != null && head.left != point && head.right != point) {
                stack.push(head.left);
                head = head.left;
            } else if (head.right != null && head.right != point) {
                stack.push(head.right);
                head = head.right;
            } else {
                CommonBinaryTree<T> pop = stack.pop();
                ans.add(pop.value);
                if (stack.isEmpty()) {
                    break;
                }
                head = stack.peek();
                point = pop;
            }
        }
        return ans;
    }
    
    public static <T> List<T> postOrder(CommonBinaryTree<T> root) {
        if (root == null) {
            return null;
        }
        List<T> ans = new ArrayList<>();
        Stack<CommonBinaryTree<T>> stack = new Stack<>();
        CommonBinaryTree<T> head = root;
        CommonBinaryTree<T> point = head;
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.peek();
            if (head.left != null && head.left != point && head.right != point) {
                stack.push(head.left);
            } else if (head.right != null && head.right != point) {
                stack.push(head.right);
            } else {
                CommonBinaryTree<T> pop = stack.pop();
                ans.add(pop.value);
                point = pop;
            }
        }
        return ans;
    }
    
    
    
    public static void main(String[] args) {
        int level = 2;
        int max = 10;
        CommonBinaryTree<Integer> integerBinaryTree = getIntegerBinaryTree(level, max);
        List<Integer> objects = inOrder(integerBinaryTree);
        System.out.println(objects);
        List<Integer> postOrder = postOrder(integerBinaryTree);
        System.out.println(postOrder);
        List<Integer> postOrder1 = postOrder1(integerBinaryTree);
        System.out.println(postOrder1);
    }
}
