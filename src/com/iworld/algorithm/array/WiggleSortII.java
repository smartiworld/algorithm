package com.iworld.algorithm.array;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author gq.cai
 * @version 1.0
 * @description 324. Wiggle Sort II
 * Medium
 * 2421
 * 852
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 *
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 * Example 2:
 *
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 *
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 * https://leetcode.com/problems/wiggle-sort-ii/
 * @date 2022/10/12 21:04
 */
public class WiggleSortII {
    
    public void wiggleSort(int[] nums) {
    
    }
    
    
    private void partition(int[] nums, int l, int r, int k) {
    
    }
    
    public static void shuffle(int[] nums, int l, int r) {
        while (r - l + 1 > 0) {
            int lenAndOne = r - l + 2;
            int bloom = 3;
            int k = 1;
            while (bloom <= lenAndOne / 3) {
                bloom *= 3;
                k++;
            }
            int m = (bloom - 1) / 2;
            
            int mid = (l + r) / 2;
            rotate(nums, l + m, mid, mid + m);
            cycles(nums, l - 1, bloom, k);
            l = l + bloom - 1;
        }
    }
    
    public static void rotate(int[] arr, int l, int m, int r) {
        reverse(arr, l, m);
        reverse(arr, m + 1, r);
        reverse(arr, l, r);
    }
    
    public static void cycles(int[] nums, int base, int bloom, int k) {
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int next = (2 * trigger) % bloom;
            int cur = next;
            int record = nums[next + base];
            int tmp = 0;
            nums[next + base] = nums[trigger + base];
            while (cur != trigger) {
                next = (2 * cur) % bloom;
                tmp = nums[next + base];
                nums[next + base] = record;
                cur = next;
                record = tmp;
            }
        }
    }
    
    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    public static void main(String[] args) throws IllegalAccessException {
    }
    
    private static boolean compareSameClassObj(Object obj1, Object obj2) throws IllegalAccessException {
        if (obj1 == null) {
            return obj2 == null;
        }
        if (obj2 == null || obj1.getClass() != obj2.getClass()) {
            return false;
        }
        Field[] o1Fields = obj1.getClass().getDeclaredFields();
        Field[] o2Fields = obj2.getClass().getDeclaredFields();
        Map<String, Field> fieldNameMap = Arrays.stream(o2Fields).collect(Collectors.toMap(Field::getName, Function.identity(), (p1, p2) -> p1));
        for (Field field : o1Fields) {
            field.getType().isPrimitive();
            field.setAccessible(true);
            String o1FieldName = field.getName();
            Field o2Field = fieldNameMap.get(o1FieldName);
            o2Field.setAccessible(true);
            if (!Objects.equals(field.get(obj1), o2Field.get(obj2))) {
                return false;
            }
        }
        return true;
    }
    
    private static final int CPU_NUMBER = Runtime.getRuntime().availableProcessors();
    
    private static final ConcurrentMap<Class<?>, ThreadPoolExecutor> EXECUTOR_MAP = new ConcurrentHashMap<Class<?>, ThreadPoolExecutor>();
    
    public static List<Integer> getIntegers(int size) {
        List<Integer> ans = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ans.add(i);
        }
        return ans;
    }
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(CPU_NUMBER * 2, CPU_NUMBER * 4, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
    }
    
}
