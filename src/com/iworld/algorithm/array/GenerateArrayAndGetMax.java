package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定数生成数组 返回数组最大值
 * nums[0] = 0
 * nums[1] = 1
 * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 *
 * 链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array
 * @date 2021/8/23 13:35
 */
public class GenerateArrayAndGetMax {
    
    /**
     * 给定数生成数组 返回数组最大值
     * 简化 前推 将等式 变成当前i值变量
     * 偶数：nums[2*i]=num[i] --- nums[i]=num[i/2]
     * 奇数：nums[2 * i + 1] = nums[i] + nums[i + 1] --- nums[i] = nums[i/2] + nums[i/2 + 1]
     * num[i]=num[i/2]+(i%2)*nums[i/2 + 1];
     *
     * 输入：n = 7
     * 输出：3
     * 解释：根据规则：
     *   nums[0] = 0
     *   nums[1] = 1
     *   nums[(1 * 2) = 2] = nums[1] = 1
     *   nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
     *   nums[(2 * 2) = 4] = nums[2] = 1
     *   nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
     *   nums[(3 * 2) = 6] = nums[3] = 2
     *   nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
     * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
     * 链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array
     * @param num
     * @return
     */
    public int getMaximumGenerated(int num) {
        if (num == 0) {
            return 0;
        }
        int[] arr = new int[num + 1];
        arr[1] = 1;
        int max = arr[1];
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i/2] + (i % 2) * arr[i/2 + 1];
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public int getMaximumGenerated2(int num) {
        if (num == 0) {
            return 0;
        }
        int[] arr = new int[num + 1];
        arr[1] = 1;
        int max = arr[1];
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i/2] + (i % 2) * arr[i/2 + 1];
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        GenerateArrayAndGetMax generateArrayAndGetMax = new GenerateArrayAndGetMax();
        int maximumGenerated = generateArrayAndGetMax.getMaximumGenerated(0);
        System.out.println(maximumGenerated);
    }
}
