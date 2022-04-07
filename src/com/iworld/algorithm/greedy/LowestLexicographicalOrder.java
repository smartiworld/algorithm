package com.iworld.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 将一个数组拼接起来 得到最小字典序
 * @date 2022/4/4 23:39
 */
public class LowestLexicographicalOrder {
    
    /**
     * 暴力方式获取最小字典序
     * @param strs
     * @return
     */
    public String lowestString(String[] strs) {
        if (strs == null || strs.length < 1) {
            return null;
        }
        Set<Integer> use = new HashSet<>();
        List<String> all = new ArrayList<>();
        arrangementString(all, use, "", strs);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).compareTo(lowest) < 0) {
                lowest = all.get(i);
            }
        }
        return lowest;
    }
    
    /**
     * 全排列strs 字符串
     * @param all   所有排列结果
     * @param use   当前操作已经被使用得字符串索引
     * @param result 拼接后得字符串
     * @param strs   原操作数组
     */
    public void arrangementString(List<String> all, Set<Integer> use, String result, String[] strs) {
        if (use.size() == strs.length) {
            all.add(result);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    arrangementString(all, use, result + strs[i], strs);
                    use.remove(i);
                }
            }
        }
    }
    
    /**
     * 根据前后拼接后的字符排序 排序后将数组中的字符全部拼接后得出最小字典序
     * @param strs
     * @return
     */
    public String lowestStringUseGreedy(String[] strs) {
        StringBuilder result = new StringBuilder();
        if (strs != null && strs.length > 0) {
            Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
            for (int i = 0; i < strs.length; i++) {
                result.append(strs[i]);
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        String[] strs = {"ab", "a", "bd", "ce", "ba"};
        LowestLexicographicalOrder lowestLexicographicalOrder = new LowestLexicographicalOrder();
        String s = lowestLexicographicalOrder.lowestString(strs);
        System.out.println(s);
        String s1 = lowestLexicographicalOrder.lowestStringUseGreedy(strs);
        System.out.println(s1);
    }
}
