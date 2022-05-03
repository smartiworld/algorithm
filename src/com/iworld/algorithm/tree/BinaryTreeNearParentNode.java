package com.iworld.algorithm.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 查找两个节点最近的父节点
 * @date 2022/3/29 18:59
 */
public class BinaryTreeNearParentNode {
    
    
    /**
     * 查找两个节点在一颗树上最近的父节点
     * 维护一个子节点和父节点map，将第一个节点所有父节点放入一个set集合，
     * 从父节点map中查找第二个节点父节点，如果存在第一个父节点集合中则停止查找返回
     *
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    public static CommonBinaryTree<Integer> findTowNodeParentUseMap(CommonBinaryTree<Integer> root, CommonBinaryTree<Integer> t1, CommonBinaryTree<Integer> t2) {
        // key孩子节点  value父节点
        Map<CommonBinaryTree<Integer>, CommonBinaryTree<Integer>> chileParentMap = new HashMap<>();
        chileParentMap.put(root, null);
        fillParent(root, chileParentMap);
        CommonBinaryTree<Integer> t1Parent = t1;
        // 存放t1及t1的父节点的set集合
        Set<CommonBinaryTree<Integer>> t1ParentSet = new HashSet<>();
        while (t1Parent != null) {
            t1ParentSet.add(t1Parent);
            t1Parent = chileParentMap.get(t1Parent);
        }
        CommonBinaryTree<Integer> t2Parent = t2;
        while (t2Parent != null) {
            if (t1ParentSet.contains(t2Parent)) {
                return t2Parent;
            }
            t2Parent = chileParentMap.get(t2Parent);
        }
        return null;
    }
    
    /**
     * 递归将给定节点的所有子节点 放入map中 key 子节点  value 父节点
     * @param parent
     * @param chileParentMap
     */
    public static void fillParent(CommonBinaryTree<Integer> parent, Map<CommonBinaryTree<Integer>, CommonBinaryTree<Integer>> chileParentMap) {
        if (parent != null && parent.left != null) {
            chileParentMap.put(parent.left, parent);
            fillParent(parent.left, chileParentMap);
        }
        if (parent != null && parent.right != null) {
            chileParentMap.put(parent.right, parent);
            fillParent(parent.right, chileParentMap);
        }
    }
    
    /**
     * 1.都不在一颗子树
     * 2.只一个在子树
     * 3.都在子树
     *   3.1都在子树的右树
     *   3.2都在子树的左树
     *   3.3在子树左右树
     *   3.4右一个为子树的根节点
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    public CommonBinaryTree<Integer> findTowNodeParent(CommonBinaryTree<Integer> root, CommonBinaryTree<Integer> t1, CommonBinaryTree<Integer> t2) {
        return getTreeInfo(root, t1, t2).parent;
    }
    
    /**
     * 递归找出两个节点在子树相交树信息
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    private TreeInfo getTreeInfo(CommonBinaryTree<Integer> root, CommonBinaryTree<Integer> t1, CommonBinaryTree<Integer> t2) {
        if (root == null) {
            return new TreeInfo(null, false, false);
        }
        // 左子树
        TreeInfo leftTreeInfo = getTreeInfo(root.left, t1, t2);
        // 右子树
        TreeInfo rightTreeInfo = getTreeInfo(root.right, t1, t2);
        boolean findT1 = leftTreeInfo.findT1 || rightTreeInfo.findT1 || root == t1;
        boolean findT2 = leftTreeInfo.findT2 || rightTreeInfo.findT2 || root == t2;
        CommonBinaryTree<Integer> parent = null;
        // 左树已经找到最近父节点
        if (leftTreeInfo.parent != null) {
            parent = leftTreeInfo.parent;
        }
        // 右数已经找到最近父节点
        if (rightTreeInfo.parent != null) {
            parent = rightTreeInfo.parent;
        }
        // 左右没有最近父节点 左右已找到两个节点节点
        if (parent == null) {
            if (findT1 && findT2) {
                parent = root;
            }
        }
        return new TreeInfo(parent, findT1, findT2);
    }
    
    
    
    public static class TreeInfo<E> {
        // 子树两个节点相交点
        public CommonBinaryTree<E> parent;
        // 子树有没有找到t1节点
        public boolean findT1;
        // 子树有没有找到t2节点
        public boolean findT2;
        
        public TreeInfo(CommonBinaryTree<E> parent, boolean findT1, boolean findT2) {
            this.parent = parent;
            this.findT1 = findT1;
            this.findT2 = findT2;
        }
        
    }
    
    public static void main(String[] args) {
        CommonBinaryTree<Integer> head = new CommonBinaryTree<>(1);
        CommonBinaryTree<Integer> tree2 = new CommonBinaryTree<>(2);
        CommonBinaryTree<Integer> tree3 = new CommonBinaryTree<>(3);
        head.left = tree2;
        head.right = tree3;
        CommonBinaryTree<Integer> tree4 = new CommonBinaryTree<>(4);
        CommonBinaryTree<Integer> tree5 = new CommonBinaryTree<>(5);
        tree2.left = tree4;
        tree2.right = tree5;
        CommonBinaryTree<Integer> tree6 = new CommonBinaryTree<>(6);
        CommonBinaryTree<Integer> tree7 = new CommonBinaryTree<>(7);
        tree3.left = tree6;
        tree3.right = tree7;
        CommonBinaryTree<Integer> tree8 = new CommonBinaryTree<>(8);
        CommonBinaryTree<Integer> tree9 = new CommonBinaryTree<>(9);
        tree4.left = tree8;
        tree4.right = tree9;
        CommonBinaryTree<Integer> tree10 = new CommonBinaryTree<>(10);
        //CommonBinaryTree<Integer> tree11 = new CommonBinaryTree<>(11);
        tree5.left = tree10;
        //tree5.right = tree11;
        //CommonBinaryTree<Integer> tree12 = new CommonBinaryTree<>(12);
        //tree6.left = tree12;
        //CommonBinaryTree<Integer> tree13 = new CommonBinaryTree<>(13);
        //tree7.right = tree13;
        BinaryTreeNearParentNode binaryTreeNearParentNode = new BinaryTreeNearParentNode();
        CommonBinaryTree<Integer> towNodeParentUseMap = findTowNodeParentUseMap(head, tree4, tree9);
        System.out.println(towNodeParentUseMap);
        CommonBinaryTree<Integer> towNodeParent = binaryTreeNearParentNode.findTowNodeParent(head, tree4, tree9);
        System.out.println(towNodeParent);
    }
}
