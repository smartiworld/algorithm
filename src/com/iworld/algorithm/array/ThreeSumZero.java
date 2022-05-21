package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 15.三数之和 medium
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * 链接：https://leetcode.cn/problems/3sum
 * @date 2022/5/20 21:55
 */
public class ThreeSumZero {
    
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        for (int num : nums) {
            // 处理多个0
            if (num == 0 && ++count == 3) {
                List<Integer> zeros = new ArrayList<>();
                zeros.add(0);
                zeros.add(0);
                zeros.add(0);
                result.add(zeros);
            }
        }
        Arrays.sort(nums);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        process(nums, 0, resultMap, new HashSet<>(), 0);
        for (Map.Entry<String, List<Integer>> stringListEntry : resultMap.entrySet()) {
            result.add(stringListEntry.getValue());
        }
        return result;
    }
    
    private void process(int[] nums, int index, Map<String, List<Integer>> resultMap, HashSet<Integer> indexSet, int sum) {
        if (indexSet.size() == 3) {
            if (sum == 0) {
                ArrayList<Integer> sums = new ArrayList<>();
                for (Integer integer : indexSet) {
                    sums.add(nums[integer]);
                }
                if (!resultMap.containsKey(sums.toString())) {
                    resultMap.put(sums.toString(), sums);
                }
            }
            return;
        }
        for (int i = index; i < nums.length; i++) {
            indexSet.add(i);
            process(nums, i + 1, resultMap, indexSet, sum + nums[i]);
            indexSet.remove(i);
        }
    }
    
    public List<List<Integer>> threeSumSort(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        int leastNearZeroIndex = 0;
        Arrays.sort(nums);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        if (nums[0] > 0 || nums[nums.length - 1] < 0 || (nums[0] == 0 && nums[2] > 0)) {
            return result;
        }
        if (nums[0] == 0 && nums[2] == 0) {
            List<Integer> zeros = new ArrayList<>();
            zeros.add(0);
            zeros.add(0);
            zeros.add(0);
            result.add(zeros);
        }
        for (int i = 0; i <nums.length; i++) {
            if (nums[i] >= 0) {
                leastNearZeroIndex = i;
                break;
            }
        }
        processSort(nums, 0, nums.length - 1, leastNearZeroIndex, leastNearZeroIndex, resultMap, nums[0] + nums[nums.length - 1] + nums[leastNearZeroIndex]);
        for (Map.Entry<String, List<Integer>> stringListEntry : resultMap.entrySet()) {
            result.add(stringListEntry.getValue());
        }
        return result;
    }
    
    private void processSort(int[] nums, int l, int r, int index, int m, Map<String, List<Integer>> resultMap, int sum) {
        if (r - l < 2 || index >= r || index <= l) {
            return ;
        }
        int twoNumSum = nums[l] + nums[r];
        int threeNumSum = twoNumSum + nums[index];
        if (threeNumSum == 0) {
            List<Integer> sumList = new ArrayList<>();
            sumList.add(nums[l]);
            sumList.add(nums[index]);
            sumList.add(nums[r]);
            String key = sumList.toString();
            if (!resultMap.containsKey(key)) {
                resultMap.put(key, sumList);
            }
        }
        if (threeNumSum > 0) {
            if (sum >= 0) {
                processSort(nums, l, r, index - 1, m, resultMap, threeNumSum);
            }
        }
        if (threeNumSum < 0) {
            if (sum <= 0) {
                processSort(nums, l, r, index + 1, m, resultMap, threeNumSum);
            }
        }
        if (l < m) {
            processSort(nums, l + 1, r, m, m, resultMap, nums[l + 1] + nums[r] + nums[index - 1]);
        }
        if (r > m) {
            processSort(nums, l, r - 1, m, m, resultMap, nums[l] + nums[r - 1] + nums[index + 1]);
        }
    }
    
    public List<List<Integer>> threeSumSort2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0 || (nums[0] == 0 && nums[2] > 0)) {
            return result;
        }
        Set<List<Integer>> lists = new HashSet<>();
        // 走到倒数第三个位置结束
        for (int i = 0; i < nums.length - 2; i++) {
            // 第一个位置如果大于0 此时后面值都已无解
            if (nums[i] > 0) {
                break;
            }
            // 去除重复值影响
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] + nums[i] == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 去除重复值影响
                    while (l < r && nums[l] == nums[l + 1]) {
                        l ++;
                    }
                    // 去除重复值影响
                    while (r > l && nums[r] == nums[r - 1]) {
                        r --;
                    }
                    l ++;
                    r --;
                } else if (nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }
    
    public List<List<Integer>> threeSumSort3(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        if (nums[0] > 0 || nums[nums.length - 1] < 0 || (nums[0] == 0 && nums[2] > 0)) {
            return result;
        }
        processSort3(nums, 0, 1, nums.length - 1, resultMap);
        for (Map.Entry<String, List<Integer>> stringListEntry : resultMap.entrySet()) {
            result.add(stringListEntry.getValue());
        }
        return result;
    }
    
    private void processSort3(int[] nums, int index, int l, int r, Map<String, List<Integer>> resultMap) {
        if (l >= r) {
            return;
        }
        int threeNumSum = nums[index] + nums[l] + nums[r];
        if (threeNumSum == 0) {
            List<Integer> sumList = Arrays.asList(nums[index], nums[l], nums[r]);
            String key = sumList.toString();
            if (!resultMap.containsKey(key)) {
                resultMap.put(key, sumList);
            }
            processSort3(nums, index, l + 1, r - 1, resultMap);
        } else if(threeNumSum > 0) {
            processSort3(nums, index, l, r - 1, resultMap);
        } else {
            processSort3(nums, index, l + 1, r, resultMap);
        }
        processSort3(nums, index + 1, index + 2, r, resultMap);
    }
    
    public static void main(String[] args) {
        int[] nums = {-2,0,1,1,2};
        ThreeSumZero threeSumZero = new ThreeSumZero();
        System.out.println(threeSumZero.threeSum(nums));
        System.out.println(threeSumZero.threeSumSort(nums));
        System.out.println(threeSumZero.threeSumSort2(nums));
        System.out.println(threeSumZero.threeSumSort3(nums));
    }
}
