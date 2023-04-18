package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 15.三数之和 medium
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * <p>
 * 链接：https://leetcode.cn/problems/3sum
 * @date 2022/5/20 21:55
 */
public class ThreeSumZero {
    
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
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
        Arrays.sort(nums);
        // 卡边界 边界值不满足 直接返回
        if (nums[0] > 0 || nums[nums.length - 1] < 0 || (nums[0] == 0 && nums[2] > 0)) {
            return result;
        }
        int r = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            //List<List<Integer>> lists = twoNumSum(nums, i + 1, r, -nums[i]);
            List<List<Integer>> lists = twoNumSum(nums, i + 1, r, -nums[i], i);
            if (lists.size() > 0) {
//                for (List<Integer> everyResult : lists) {
//                    everyResult.add(nums[i]);
//                    result.add(everyResult);
//                }
                result.addAll(lists);
            }
        }
        return result;
    }
    
    /**
     * 两数之和
     * @param nums     计算数组
     * @param l        数组左边界
     * @param r        数组右边界
     * @param target   目标值
     * @param cur      当前已经处理的位置 不传就对应调用方法注释部分
     * @return
     */
    private List<List<Integer>> twoNumSum(int[] nums, int l, int r, int target, int cur) {
        List<List<Integer>> result = new ArrayList<>();
        while (l < r) {
            int twoSum = nums[l] + nums[r];
            if (twoSum == target) {
                List<Integer> everyResult = new ArrayList<>();
                everyResult.add(nums[cur]);
                everyResult.add(nums[l]);
                everyResult.add(nums[r]);
                result.add(everyResult);
                l++;
                r--;
                while (l < r && nums[l] == nums[l - 1]) {
                    l++;
                }
                while (l < r && nums[r] == nums[r + 1]) {
                    r--;
                }
            } else if (twoSum < target) {
                l++;
            } else {
                r--;
            }
        }
        return result;
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
                        l++;
                    }
                    // 去除重复值影响
                    while (r > l && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
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
        } else if (threeNumSum > 0) {
            processSort3(nums, index, l, r - 1, resultMap);
        } else {
            processSort3(nums, index, l + 1, r, resultMap);
        }
        processSort3(nums, index + 1, index + 2, r, resultMap);
    }
    
    public List<List<Integer>> threeSum4(int[] nums) {
        // 数组中最大值
        int maxVal = Integer.MIN_VALUE;
        // 数组中最小值
        int minVal = Integer.MAX_VALUE;
        // 小于0的数字个数
        int negNums = 0;
        // 大于0的数字个数
        int posNums = 0;
        List<List<Integer>> result = new LinkedList<>();
        // 等于0的数字个数
        int zeroNums = 0;
        for (int num : nums) {
            if (num < minVal) {
                minVal = num;
            }
            if (num > maxVal) {
                maxVal = num;
            }
            if (num == 0) {
                zeroNums = zeroNums + 1;
            } else if (num > 0) {
                posNums = posNums + 1;
            } else {
                negNums = negNums + 1;
            }
        }
        if (zeroNums >= 3) {
            result.add(Arrays.asList(0, 0, 0));
        }
        if (minVal >= 0 || maxVal <= 0) {
            return result;
        }
        int[] negNumMap = new int[negNums];
        int[] posNumMap = new int[posNums];
        byte[] numMap = new byte[maxVal - minVal + 1];
        negNums = 0;
        posNums = 0;
        for (int num : nums) {
            if (numMap[num - minVal]++ != 0) {
                //frequency counter
                numMap[num - minVal] = 2;
            } else {
                if (num > 0) {
                    //distinct positive catcher
                    posNumMap[posNums] = num;
                    posNums = posNums + 1;
                } else if (num < 0) {
                    //distinct negative catcher
                    negNumMap[negNums] = num;
                    negNums = negNums + 1;
                }
            }
        }
        //sort only till posNumMap
        Arrays.parallelSort(posNumMap, 0, posNums);
        Arrays.parallelSort(negNumMap, 0, negNums);
        int posStart = 0;
        for (int i = negNums - 1; i >= 0; i = i - 1) {
            int nv = negNumMap[i];
            int minpv = (-nv) / 2;
            while (posStart < posNums && posNumMap[posStart] < minpv) {
                posStart++;
            }
            for (int j = posStart; j < posNums; j = j + 1) {
                int pv = posNumMap[j];
                int ln = 0 - nv - pv;
                if (ln >= nv && ln <= pv) {
                    if (numMap[ln - minVal] == 0) {
                        continue;
                    } else if (ln == pv || ln == nv) {
                        if (numMap[ln - minVal] > 1) {
                            result.add(Arrays.asList(nv, pv, ln));
                        }
                    } else {
                        result.add(Arrays.asList(nv, pv, ln));
                    }
                } else if (ln < nv) {
                    break;
                }
            }
            //System.out.println("Out");
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {-2, 0, 0, 0, 1, 1, 2};
        ThreeSumZero threeSumZero = new ThreeSumZero();
        System.out.println(threeSumZero.threeSum(nums));
        System.out.println(threeSumZero.threeSumSort(nums));
        System.out.println(threeSumZero.threeSumSort2(nums));
        System.out.println(threeSumZero.threeSumSort3(nums));
        System.out.println(threeSumZero.threeSum4(nums));
    }
}
