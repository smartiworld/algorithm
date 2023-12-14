package com.iworld.algorithm.array;

import com.iworld.algorithm.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.2.7 第k小数值对
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 例如arr = [3,1,2]，
 * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
 * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
 * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
 * (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 *
 * 给定一个数组arr，和整数k，返回第k小的数值对。
 * @date 2023/12/13 19:08
 */
public class KthSmallPair {
    
    public static int[] kthSmallPair(int[] nums, int k) {
        int len = nums.length;
        if (k > len * len) {
            return null;
        }
        int k1 = (k - 1) / len;
        int firstNum = getKth(nums, k1, 0, len - 1, 1);
        int lessFirstNumSize = 0;
        int firstNumSize = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == firstNum) {
                firstNumSize ++;
            }
            if (nums[i] < firstNum) {
                lessFirstNumSize ++;
            }
        }
        int k2 = (k - lessFirstNumSize * len - 1) / firstNumSize;
        int secondNum = getKth(nums, k2, 0, len - 1, 2);
        return new int[]{firstNum, secondNum};
    }
    
    private static int getKth(int[] nums, int k, int l, int r, int position) {
        int left = l;
        int right = r;
        int index = left;
        double random = Math.random();
        int i = l + (int) (random * (r - l + 1));
        int num = nums[i];
        while (index <= right) {
            if (nums[index] < num) {
                swap(nums, left++, index++);
            } else if (nums[index] > num) {
                swap(nums, right--, index);
            } else {
                index++;
            }
        }
//        System.out.println("position===" + position + "===left-----" + left + "===right-----" + right + "===l===" + l +  "===r===" + r + "===k====" + k + "===i===" + i);
//        ArrayUtil.printArray(nums);
        if (k >= left && k <= right) {
            return nums[left];
        } else if (k < left) {
            // System.out.println("position===" + position + "===left-----" + left + "===right-----" + right + "===l===" + l +  "===r===" + r + "===k====" + k + "===i===" + i);
            return getKth(nums, k, l, left - 1, position);
        } else {
            // System.out.println("position===" + position + "===left-----" + left + "===right-----" + right + "===l===" + l +  "===r===" + r + "===k====" + k + "===i===" + i);
            return getKth(nums, k, right + 1, r, position);
        }
    }
    
    private static int getKth2(int[] nums, int k, int l, int r) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int num = (int)(Math.random() * (right - left + 1));
            int[] partition = partition(nums, num, left, right);
            if (k >= partition[0] && k <= partition[1]) {
                return nums[left];
            } else if (k < partition[0]) {
                right = partition[0] - 1;
            } else {
                left = partition[1] + 1;
            }
        }
        return nums[left];
    }
    
    private static int[] partition(int[] nums, int num, int l, int r) {
        int left = l;
        int right = r;
        int index = 0;
        while (index <= right) {
            if (nums[index] < num) {
                swap(nums, left++, index++);
            } else if (nums[index] > num) {
                swap(nums, right--, index);
            } else {
                index++;
            }
        }
        return new int[]{left, right};
    }
    
    public static int[] kthSmallPairForce(int[] nums, int k) {
        int len = nums.length;
        Pair[] allPair = new Pair[len * len];
        int index = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                allPair[index++] = new Pair(nums[i], nums[j]);
            }
        }
        Arrays.sort(allPair, (o1, o2) -> o1.firstNum == o2.firstNum ? o1.secondNum - o2.secondNum : o1.firstNum - o2.firstNum);
        for (int i = 0; i < len * len; i++) {
            if (i == k - 1) {
                return new int[]{allPair[i].firstNum, allPair[i].secondNum};
            }
        }
        return new int[]{allPair[0].firstNum, allPair[0].secondNum};
    }
    
    public static class Pair {
        private int firstNum;
        private int secondNum;
        Pair(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }
    }
    
    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
    
    public static void main(String[] args) {
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int[] nums = ArrayUtil.generateSortArray(10, 8);
            int k = (int) (Math.random() * 100) + 1;
            int[] ints1 = kthSmallPair(nums, k);
            int[] ints2 = kthSmallPairForce(nums, k);
            if (ints1[0] != ints2[0] || ints1[1] != ints2[1]) {
                System.out.println("=======================");
                ArrayUtil.printArray(nums);
                ArrayUtil.printArray(ints1);
                ArrayUtil.printArray(ints2);
            }
        }
//        int times = 1000;
//        for (int i = 0; i < times; i++) {
//            int[] nums = {2, 4, 4, 4, 5, 5, 6, 6, 6, 7};
//            int k = 50;
//            int[] ints1 = kthSmallPair(nums, k);
//            System.out.println(ints1[0] + "========" + ints1[1]);
//            int[] ints2 = kthSmallPairForce(nums, k);
//            System.out.println(ints2[0] + "========" + ints2[1]);
//            System.out.println();
//        }
//        int times = 1000;
//        for (int i = 0; i < times; i++) {
//            int[] nums = {2, 4, 4, 4, 5, 5, 6, 6, 6, 7};
//            int kthTest = getKthTest(nums, 4, 0, 9, 2);
//            System.out.println(kthTest);
//        }
        //{2, 4, 4, 4, 5, 5, 6, 6, 6, 7};
        //position===1===left-----0===right-----0===l===0===r===9===k====4===i===0
        //{2,4,4,5,5,6,6,6,7,4}
        //position===1===left-----2===right-----4===l===1===r===9===k====4===i===9
        //{2,4,4,4,5,6,6,7,6,5}
        //firstNum=====4===k1=====4===k2===13
        //position===2===left-----1===right-----3===l===0===r===9===k====13===i===1
        //{2,4,4,4,6,6,7,6,5,5}
        //position===2===left-----6===right-----8===l===4===r===9===k====13===i===5
        //{2,4,4,4,5,5,6,6,6,7}
        //position===2===left-----9===right-----9===l===9===r===9===k====13===i===9
        //{2,4,4,4,5,5,6,6,6,7}
        //position===2===left-----10===right-----9===l===10===r===9===k====13===i===10
        //{2,4,4,4,5,5,6,6,6,7}
//        int[] nums = {2,4,4,5,5,6,6,6,7,4};
//        getKthTest(nums, 4, 1, 9, 9);
    }
    
    private static int getKthTest(int[] nums, int k, int l, int r, int position) {
        int left = l;
        int right = r;
        int index = left;
        double random = Math.random();
        int i = position;
        while (index <= right) {
            int num = nums[i];
            if (nums[index] < num) {
                swap(nums, left++, index++);
            } else if (nums[index] > num) {
                swap(nums, right--, index);
            } else {
                index++;
            }
        }
        System.out.println("position===" + position + "===left-----" + left + "===right-----" + right + "===l===" + l +  "===r===" + r + "===k====" + k + "===i===" + i);
        ArrayUtil.printArray(nums);
        if (k >= left && k <= right) {
            if (nums[left] == 4) {
                System.out.println(nums[i] + "====random===" + random + "=====l====" + l + "=====r=====" + r);
            }
            return nums[left];
        } else if (k < left) {
            // System.out.println("position===" + position + "===left-----" + left + "===right-----" + right + "===l===" + l +  "===r===" + r + "===k====" + k + "===i===" + i);
            return getKthTest(nums, k, l, left - 1, position);
        } else {
            // System.out.println("position===" + position + "===left-----" + left + "===right-----" + right + "===l===" + l +  "===r===" + r + "===k====" + k + "===i===" + i);
            return getKthTest(nums, k, right + 1, r, position);
        }
    }
}
