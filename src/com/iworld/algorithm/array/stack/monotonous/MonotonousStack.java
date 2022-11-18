package com.iworld.algorithm.array.stack.monotonous;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 单调栈 解决数组小于或者大于当前位置最进的左右位置
 * 1.小于当前位置的栈底保存最小值 入栈元素必须大于栈顶元素
 * 2.大于当前位置的栈底保存最大值 入栈元素必须小于栈顶元素
 * @date 2022/11/17 17:18
 */
public class MonotonousStack {
    /**
     * 数组中没有重复值 找出小于离自己最近左右值
     * 返回结果数组行下标表示数组中的下标 0表示左边位置 1表示右边位置
     * 1.如果栈不为空并且压入元素小于栈顶元素的时候 需要弹出原栈顶元素并且结算
     * 2.数组走完的时候 栈中存在没有结算的位置 需要将栈中元素结算 此时已经没有右边小于栈中元素值
     * @param nums
     * @return
     */
    public int[][] getNearLessNoRepeat(int[] nums) {
        int len = nums.length;
        // 数组行代表数组下边位置 0位置表示小于当前数组最近的左边位置 1位置表示小于当前数最近的右边位置
        int[][] ans = new int[len][2];
        // 栈 保存数组下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int pop = stack.pop();
                ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                ans[pop][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            ans[pop][1] = -1;
        }
        return ans;
    }
    
    /**
     * 数组中可以有重复值 找出小于离自己最近左右值
     * 返回结果数组行下标表示数组中的下标 0表示左边位置 1表示右边位置
     * 栈中放的是相同元素下标集合
     * 1.如果栈不为空并且压入元素小于栈顶元素的时候 需要弹出原栈顶元素并且结算
     * 2.数组走完的时候 栈中存在没有结算的位置 需要将栈中元素结算 此时已经没有右边小于栈中元素值
     * @param nums
     * @return
     */
    public int[][] getNearLess(int[] nums) {
        int len = nums.length;
        // 数组行代表数组下边位置 0位置表示小于当前数组最近的左边位置 1位置表示小于当前数最近的右边位置
        int[][] ans = new int[len][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek().get(0)] > nums[i]) {
                List<Integer> pop = stack.pop();
                for (Integer index : pop) {
                    ans[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    ans[index][1] = i;
                }
            }
            if (!stack.isEmpty() && nums[stack.peek().get(0)] == nums[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> indexs = new ArrayList<>();
                indexs.add(i);
                stack.push(indexs);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            for (Integer index : pop) {
                ans[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                ans[index][1] = -1;
            }
        }
        return ans;
    }
}
