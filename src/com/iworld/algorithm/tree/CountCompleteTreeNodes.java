package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 统计完全二叉树结点的个数
 * 时间复杂度低于O(n)
 * 1.获取完全二叉树的最大高度
 * 2.获取根节点右树高度
 * 2.1如果右树高度小于左树高度 表示右树是满树 此时可以计算出右树所有节点 递归左树
 * 2^(height-1)-1
 * 2.2如果右树高度和左树高度相等 表示左树是满树 此时可以计算左树节点 递归右树
 * 2^(height)-1
 * 222. Count Complete Tree Nodes
 * Medium
 * 7K
 * 394
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5 * 10^4].
 * 0 <= Node.val <= 5 * 10^4
 * The tree is guaranteed to be complete.
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * @date 2023/1/7 18:58
 */
public class CountCompleteTreeNodes {
    
    public int nodeCount(CommonBinaryTree<Integer> root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }
    
    private int process(CommonBinaryTree<Integer> root) {
        if (root.left == null) {
            return 1;
        }
        if (root.right == null) {
            return 2;
        }
        int leftHeight = completeTreeHeight(root.left);
        int rightHeight = completeTreeHeight(root.right);
        if (leftHeight == rightHeight) {
            // 右树高度等于树的高度 左树是满二叉树
            int leftNodeCount = 1 << leftHeight;
            return leftNodeCount + process(root.right);
        } else {
            // 右树高度小于树的高度 右树是满二叉树
            int rightNodeCount = 1 <<  rightHeight;
            return rightNodeCount + process(root.left);
        }
    }
    
    /**
     * 获取完全二叉树的深度
     * @param root
     * @return
     */
    private int completeTreeHeight(CommonBinaryTree<Integer> root) {
        int height = 0;
        CommonBinaryTree<Integer> cur = root;
        while (cur != null) {
            height++;
            cur = cur.left;
        }
        return height;
    }
    
    
    /**
     * 请保证head为头的树，是完全二叉树
     * 对比上个 减少求一个子树高度遍历
     */
    public static int nodeNum(CommonBinaryTree<Integer> head) {
        if (head == null) {
            return 0;
        }
        // mostLeftLevel(head, 1)
        return bs(head, 1, mostLeftLevel(head, 1));
    }
    
    // node在第level层，h是总的深度（h永远不变，全局变量
    // 以node为头的完全二叉树，节点个数是多少
    public static int bs(CommonBinaryTree<Integer> node, int Level, int h) {
        if (Level == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, Level + 1) == h) {
            return (1 << (h - Level)) + bs(node.right, Level + 1, h);
        } else {
            return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
        }
    }
    
    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    // node为头的子树，一定是完全二叉树
    public static int mostLeftLevel(CommonBinaryTree<Integer> node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
    
    public static void main(String[] args) {
        CommonBinaryTree<Integer> root = new CommonBinaryTree<>(1);
        CommonBinaryTree<Integer> l1 = new CommonBinaryTree<>(2);
        root.left = l1;
        CommonBinaryTree<Integer> r1 = new CommonBinaryTree<>(3);
        root.right = r1;
        CommonBinaryTree<Integer> l2 = new CommonBinaryTree<>(4);
        l1.left = l2;
        CommonBinaryTree<Integer> l3 = new CommonBinaryTree<>(5);
        l1.right = l3;
        CommonBinaryTree<Integer> r11 = new CommonBinaryTree<>(6);
        r1.left = r11;
        CommonBinaryTree<Integer> r12 = new CommonBinaryTree<>(7);
        r1.right = r12;
        CountCompleteTreeNodes binaryTreeNodeCount = new CountCompleteTreeNodes();
        System.out.println(binaryTreeNodeCount.nodeCount(root));
        System.out.println(nodeNum(root));
    }
}
