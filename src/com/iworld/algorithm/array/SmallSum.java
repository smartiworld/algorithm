package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 归并排序 求数组小和  当左边数小于右边数的时候产生小和
 * @date 2021/7/10 17:36
 */
public class SmallSum {
    
    /**
     * 求数组小和
     * 利用归并排序每次合并比较 当左边数小于右边数的时候产生小和 右边数到right位置数都会大于当前左边数
     * 一并计算出 (right - r + 1)
     * @param arr
     */
    public static int mergeAndSum(int[] arr, int left, int mid, int right) {
        int[] copyArr = new int[right - left + 1];
        int sum = 0;
        int i = 0;
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            //只有每次merge 才对比当前数，计算右边比当前数大 只能在右边 归并集中
            //合并完后不会再产生小和计算 在下一次合并后对比右边数值
            //(right - r + 1) 右边已经排序好 如果r位置数大于l位置数 则产生小和 r-right的数 一定比当前数大 一并计算出
            //然后排序
            sum += arr[l] < arr[r] ? (right - r + 1) * arr[l] : 0;
            copyArr[i++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
        }
        while (l <= mid) {
            copyArr[i++] = arr[l++];
        }
        while (r <= mid) {
            copyArr[i++] = arr[r++];
        }
        for (i = 0; i < copyArr.length; i++) {
            arr[left + i] = copyArr[i];
        }
        return sum;
    }
    
    public static int split(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return split(arr, left, mid) + split(arr, mid + 1, right) + mergeAndSum(arr, left, mid, right);
    }
    
    /**
     * 归并排序计算数组中小和
     * @param arr
     * @return
     */
    public static int smallSum(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        return split(arr, 0, arr.length);
    }
    
    public static void main(String[] args) {
        SmallSum s = new SmallSum();
//        long reverse = s.reverse(10L);
//        System.out.println(reverse);
        List<Node> nodes = new ArrayList<>();
        Node n1 = new Node(1, 0, "AA");
        Node n2 = new Node(2, 1, "BB");
        Node n3 = new Node(3, 1, "CC");
        Node n4 = new Node(4, 3, "DD");
        Node n5 = new Node(5, 3, "EE");
        Node n6 = new Node(6, 2, "FF");
        Node n7 = new Node(7, 2, "GG");
        Node n8 = new Node(8, 4, "HH");
        Node n9 = new Node(9, 5, "II");
        Node n10 = new Node(10, 0, "JJ");
        Node n11 = new Node(11, 10, "KK");
        Node n12 = new Node(12, 10, "LL");
        Node n13 = new Node(13, 12, "MM");
        Node n14 = new Node(14, 13, "NN");
        Node n15 = new Node(15, 14, "GG");
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);
        nodes.add(n8);
        nodes.add(n9);
        nodes.add(n10);
        nodes.add(n11);
        nodes.add(n12);
        nodes.add(n13);
        nodes.add(n14);
        nodes.add(n15);
        printTree(nodes);
    }
    
    /**
     * 反转数字
     * @param val
     * @return
     */
    public long reverse(long val) {
        long res = 0;
        while (val != 0) {
            long num = val % 10;
            res = res * 10 + num;
            val = val / 10;
        }
        return res;
    }
    // 维护parent_id和节点关系
    static Map<Integer, List<Node>> maps = new HashMap<>();
    // 存放已经打印的节点
    static Set<Integer> idSet = new HashSet<>();
    
    /**
     * 节点已知父节点 按要求打印节点层次结构
     * @param nodes
     */
    public static void printTree(List<Node> nodes) {
        // 遍历所有节点 将节点parent_id和当前节点放入map  父节点下可能多个孩子
        for (Node node : nodes) {
            Integer parentId = node.getParentId();
            List<Node> mapNodes = maps.get(parentId);
            if (mapNodes == null) {
                mapNodes = new ArrayList<>();
                maps.put(parentId, mapNodes);
            }
            mapNodes.add(node);
        }
        print(nodes, -1);
    }
    
    /**
     * 递归输出节点 c控制层数 根据层数 输出多少空格
     * @param nodes
     * @param c
     */
    public static void print(List<Node> nodes, int c) {
        if (nodes == null || nodes.size() == 0) {
            return;
        }
        c ++;
        for (Node node : nodes) {
            if (idSet.contains(node.getId())) {
                continue;
            }
            for (int i = 0; i < c; i++) {
                System.out.print("  ");
            }
            System.out.println(node.getName());
            idSet.add(node.getId());
            // 然后通过自己id找出所有的孩子节点 打印
            print(maps.get(node.getId()), c);
        }
    }
    static class Node{
        private int id;
        private int parentId;
        private String name;
        Node(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public int getParentId() {
            return parentId;
        }
    
        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
}
