package com.iworld.algorithm.tree;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/12/21 10:12
 */
public class PrintBinaryTree {
    private static boolean isTest;
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(1);
        BinaryTree binaryTree1 = new BinaryTree(2);
        BinaryTree binaryTree2 = new BinaryTree(3);
        binaryTree.left = binaryTree1;
        binaryTree.right = binaryTree2;
        BinaryTree binaryTree3 = new BinaryTree(4);
        BinaryTree binaryTree4 = new BinaryTree(5);
        binaryTree2.left = binaryTree4;
        binaryTree2.right = binaryTree3;
        BinaryTree binaryTree5 = new BinaryTree(6);
        BinaryTree binaryTree6 = new BinaryTree(7);
        binaryTree1.left = binaryTree6;
        binaryTree1.right = binaryTree5;
        //printTree(binaryTree);
        System.out.println("asdf".startsWith("ad"));
        System.out.println(isTest);
        Double startElectricity = 35.476;
        Double endElectricity = 45.611999999999995;
        BigDecimal diff = BigDecimal.valueOf(startElectricity).subtract(BigDecimal.valueOf(endElectricity));
        
    }
    
    public static void printTree (BinaryTree binaryTree) {
        LinkedBlockingQueue<BinaryTree> lbq = new LinkedBlockingQueue<>();
        lbq.offer(binaryTree);
        Stack<BinaryTree> stack = new Stack<BinaryTree>();
        int count = 0;
        int floor = 0;
        while (lbq.peek() != null) {
            BinaryTree cBinaryTree = lbq.poll();
            System.out.println(cBinaryTree.value);
            if (floor % 2 == 0) {
                lbq.offer(cBinaryTree.right);
                lbq.offer(cBinaryTree.left);
            } else {
                lbq.offer(cBinaryTree.left);
                lbq.offer(cBinaryTree.right);
            }
            floor ++;
        }
    }
    
    
    
    public static void floorPrint () {
    
    }
    
    public static void print () {
    
    }
    
    static class BinaryTree {
        BinaryTree left;
        BinaryTree right;
        int value;
        
        BinaryTree (int value) {
            this.value = value;
        }
    }
}
