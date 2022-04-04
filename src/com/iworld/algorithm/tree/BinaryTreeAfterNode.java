package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给一个节点 返回当前节点的后继节点
 * 后继节点，中序遍历当前节点的后的节点
 * @date 2022/3/10 18:23
 */
public class BinaryTreeAfterNode {
    
    /**
     * 如果当前节点有右孩子 则后继节点为当前节点右孩子
     * 如果当前节点为左孩子则后继节点为当前节点的父节点
     * 查找当前节点的父节点 当被查找父节点为左孩子时，此时当前查找到的父节点的父节点为当前节点后继节点
     * @param head
     * @return
     */
    public static InnerBinaryTree getNextNode(InnerBinaryTree head) {
        if (head == null) {
            return head;
        }
        // 如果当前节点有右孩子 则后继节点为当前节点右孩子
        if (head.right != null) {
            return head.right;
        }
        //如果当前节点为左子树 后继节点则为当前节点的父节点
        InnerBinaryTree parent = head.parent;
        if (head == parent.left){
            return parent;
        }
        // 查找当前节点的父节点 当被查找父节点为左孩子时，此时当前查找到的父节点的父节点为当前节点后继节点
        InnerBinaryTree nextNode = getRightNextNode(parent);
        return nextNode;
    }
    
    /**
     * 查找右叶子节点 父节点是左孩子的父节点，直到父节点为空节点
     * @param right
     * @return
     */
    public static InnerBinaryTree getRightNextNode(InnerBinaryTree right) {
        InnerBinaryTree parent = right.parent;
        if (parent == null) {
            return null;
        }
        // 当前节点为左孩子节点
        if (right == parent.left){
            return parent;
        }
        InnerBinaryTree nextNode = getRightNextNode(parent);
        return nextNode;
    }
    
    public static void main(String[] args) {
        InnerBinaryTree root = new InnerBinaryTree(1);
        InnerBinaryTree node2 = new InnerBinaryTree(2);
        InnerBinaryTree node3 = new InnerBinaryTree(3);
        root.left = node2;
        root.right = node3;
        node2.parent = node3.parent = root;
        InnerBinaryTree node4 = new InnerBinaryTree(4);
        InnerBinaryTree node5 = new InnerBinaryTree(5);
        node2.left = node4;
        node2.right = node5;
        node4.parent = node5.parent = node2;
        InnerBinaryTree node6 = new InnerBinaryTree(6);
        InnerBinaryTree node7 = new InnerBinaryTree(7);
        node3.left = node6;
        node3.right = node7;
        node6.parent = node7.parent = node3;
        InnerBinaryTree nextNode = getNextNode(node2);
        System.out.println(nextNode);
    }


    static class InnerBinaryTree {
        private int val;
        
        private InnerBinaryTree left;
        
        private InnerBinaryTree right;
        
        private InnerBinaryTree parent;
        
        InnerBinaryTree(int val) {
            this.val = val;
        }
    
        @Override
        public String toString() {
            return "InnerBinaryTree{" +
                    "val=" + val +
                    '}';
        }
    }
}
