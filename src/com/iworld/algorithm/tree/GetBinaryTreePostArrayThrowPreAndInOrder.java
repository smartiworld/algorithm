package com.iworld.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.4.3 根据二叉树先序中序返回后续结果
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
 * {4,5,2,6,7,3,1}
 * @date 2023/12/31 17:29
 */
public class GetBinaryTreePostArrayThrowPreAndInOrder {
    
    public static int[] getPostArray(int[] preOrder, int[] inOrder) {
        if (preOrder.length != inOrder.length) {
            return null;
        }
        int[] result = new int[preOrder.length];
        int root = getPostArrayProcess(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1, result, new int[]{0});
        result[preOrder.length - 1] = root;
        return result;
    }
    
    /**
     * 生成二叉树
     * @param preOrder 先序
     * @param inOrder  中序
     * @param preStart 先序开始位置
     * @param preEnd   先序结束位置
     * @param inStart  中序开始位置
     * @param inEnd    中序结束位置
     * @return
     */
    private static CommonBinaryTree<Integer> generatorBinaryTree(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart == preEnd) {
            return new CommonBinaryTree<>(preOrder[preEnd]);
        }
        int parentNum = preOrder[preStart];
        int inEndIndex = findEndIndex(parentNum, inStart, inOrder, inEnd);
        int len = inEndIndex - inStart;
        int preEndIndex = preStart + len;
        CommonBinaryTree<Integer> left = generatorBinaryTree(preOrder, inOrder, preStart + 1, preEndIndex, inStart, inEndIndex - 1);
        CommonBinaryTree<Integer> right = generatorBinaryTree(preOrder, inOrder, preEndIndex + 1, preEnd, inEndIndex + 1, inEnd);
        CommonBinaryTree<Integer> parent = new CommonBinaryTree<>(parentNum);
        parent.left = left;
        parent.right = right;
        return parent;
    }
    
    /**
     * 返回当前位置数字 深度处理
     * @param preOrder 先序数组
     * @param inOrder  中序数组
     * @param preStart 先序开始位置
     * @param preEnd   先序结束位置
     * @param inStart  中序开始位置
     * @param inEnd    中序结束位置
     * @param result   后续结果数组
     * @param index    后续数组填充位置
     * @return
     */
    private static int getPostArrayProcess(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd, int[] result, int[] index) {
        if (preStart == preEnd) {
            return preOrder[preEnd];
        }
        int parentNum = preOrder[preStart];
        int inEndIndex = findEndIndex(parentNum, inStart, inOrder, inEnd);
        int len = inEndIndex - inStart;
        int preEndIndex = preStart + len;
        int left = getPostArrayProcess(preOrder, inOrder, preStart + 1, preEndIndex, inStart, inEndIndex - 1, result, index);
        result[index[0]++] = left;
        int right = getPostArrayProcess(preOrder, inOrder, preEndIndex + 1, preEnd, inEndIndex + 1, inEnd, result, index);
        result[index[0]++] = right;
        return parentNum;
    }
    
    /**
     * best
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static int[] getPostArray1(int[] preOrder, int[] inOrder) {
        if (preOrder.length != inOrder.length) {
            return null;
        }
        int[] result = new int[preOrder.length];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            indexMap.put(inOrder[i], i);
        }
        int root = getPostArrayProcess1(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1, result, new int[]{0}, indexMap);
        result[preOrder.length - 1] = root;
        return result;
    }
    
    private static int getPostArrayProcess1(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd, int[] result, int[] index, Map<Integer, Integer> indexMap) {
        if (preStart == preEnd) {
            return preOrder[preEnd];
        }
        int parentNum = preOrder[preStart];
        int inEndIndex = indexMap.get(parentNum);
        int len = inEndIndex - inStart;
        int preEndIndex = preStart + len;
        int left = getPostArrayProcess1(preOrder, inOrder, preStart + 1, preEndIndex, inStart, inEndIndex - 1, result, index, indexMap);
        result[index[0]++] = left;
        int right = getPostArrayProcess1(preOrder, inOrder, preEndIndex + 1, preEnd, inEndIndex + 1, inEnd, result, index, indexMap);
        result[index[0]++] = right;
        return parentNum;
    }
    
    private static int findEndIndex(int num, int start, int[] nums, int end) {
        for (int i = start; i <= end; i++) {
            if (nums[i] == num) {
                return i;
            }
        }
        return -1;
    }
    
    // for test
    public static int[] getPreArray(CommonBinaryTree<Integer> head) {
        ArrayList<Integer> arr = new ArrayList<>();
        fillPreArray(head, arr);
        int[] ans = new int[arr.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arr.get(i);
        }
        return ans;
    }
    
    // for test
    public static void fillPreArray(CommonBinaryTree<Integer> head, ArrayList<Integer> arr) {
        if (head == null) {
            return;
        }
        arr.add(head.value);
        fillPreArray(head.left, arr);
        fillPreArray(head.right, arr);
    }
    
    // for test
    public static int[] getInArray(CommonBinaryTree<Integer> head) {
        ArrayList<Integer> arr = new ArrayList<>();
        fillInArray(head, arr);
        int[] ans = new int[arr.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arr.get(i);
        }
        return ans;
    }
    
    // for test
    public static void fillInArray(CommonBinaryTree<Integer> head, ArrayList<Integer> arr) {
        if (head == null) {
            return;
        }
        fillInArray(head.left, arr);
        arr.add(head.value);
        fillInArray(head.right, arr);
    }
    
    // for test
    public static int[] getPosArray(CommonBinaryTree<Integer> head) {
        ArrayList<Integer> arr = new ArrayList<>();
        fillPostArray(head, arr);
        int[] ans = new int[arr.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arr.get(i);
        }
        return ans;
    }
    
    // for test
    public static void fillPostArray(CommonBinaryTree<Integer> head, ArrayList<Integer> arr) {
        if (head == null) {
            return;
        }
        fillPostArray(head.left, arr);
        fillPostArray(head.right, arr);
        arr.add(head.value);
    }
    
    // for test
    public static CommonBinaryTree<Integer> generateRandomTree(int value, int height) {
        HashSet<Integer> hasValue = new HashSet<Integer>();
        return createTree(value, 1, height, hasValue);
    }
    
    // for test
    public static CommonBinaryTree<Integer> createTree(int value, int level, int height, HashSet<Integer> hasValue) {
        if (level > height) {
            return null;
        }
        int cur = 0;
        do {
            cur = (int) (Math.random() * value) + 1;
        } while (hasValue.contains(cur));
        hasValue.add(cur);
        CommonBinaryTree<Integer> head = new CommonBinaryTree<Integer>(cur);
        head.left = createTree(value, level + 1, height, hasValue);
        head.right = createTree(value, level + 1, height, hasValue);
        return head;
    }
    
    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        // int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
        // int[] in = { 4, 2, 5, 1, 6, 3, 7 };
//        int[] pre = { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15 };
//        int[] in = { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
//        CommonBinaryTree<Integer> tree = generatorBinaryTree(pre, in, 0, pre.length - 1, 0, in.length - 1);
//        System.out.println(tree);
//        int[] postArray = getPostArray(pre, in);
//        ArrayUtil.printArray(postArray);
        System.out.println("test begin");
        int n = 5;
        int value = 1000;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            CommonBinaryTree<Integer> head = generateRandomTree(value, n);
            int[] pre = getPreArray(head);
            int[] in = getInArray(head);
            int[] pos = getPosArray(head);
            int[] ans1 = getPostArray(pre, in);
            int[] ans2 = getPostArray1(pre, in);
            if (!isEqual(pos, ans1)) {
                System.out.println("Oops!");
            }
            if (!isEqual(pos, ans2)) {
                System.out.println("2 Oops!");
            }
        }
        System.out.println("test end");
        
    }
    
    public static int[] preInToPos2(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int len = pre.length;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            inMap.put(in[i], i);
        }
        int[] pos = new int[len];
        process2(pre, 0, len - 1, in, 0, len - 1, pos, 0, len - 1, inMap);
        return pos;
    }
    
    /**
     *
     * @param pre  先序数组
     * @param L1   先序数组开始位置
     * @param R1   先序数组结束位置
     * @param in   中序数组
     * @param L2   中序数组开始位置
     * @param R2   中序数组结束位置
     * @param pos  需要填充后序数组
     * @param L3   后续数组开始位置
     * @param R3   后续数组结束位置
     * @param inMap 中序数组下标map
     */
    public static void process2(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3,
                                HashMap<Integer, Integer> inMap) {
        if (L1 > R1) {
            return;
        }
        if (L1 == R1) {
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];
        int mid = inMap.get(pre[L1]);
        int leftSize = mid - L2;
        process2(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);
        // R3位置已经被填充
        process2(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);
    }
}
