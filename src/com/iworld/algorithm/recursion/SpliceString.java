package com.iworld.algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 组合字符串
 * 给定一个字符串 给定一个字符类型的数组，arr中每个字符串代表一个贴纸，可以把单个字符剪开使用
 * 拼接出给定的字符串，返回需要至少多少张贴纸可以完成任务
 * @date 2022/5/15 20:55
 */
public class SpliceString {

    public static int minUseStringCount(String[] useArray, String target) {
        if (useArray == null || useArray.length <= 0 || target == null) {
            return 0;
        }
        // 贴纸数组转换为int类型ASCII码
        // 行对应数组中字符 列为每个字符串
        int[][] useMap = new int[useArray.length][26];
        for (int i = 0; i < useArray.length; i++) {
            String s = useArray[i];
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                useMap[i][aChar - 'a']++;
            }
        }
        int[] targets = new int[26];
        for (char c : target.toCharArray()) {
            targets[c - 'a'] ++;
        }
        return process(useMap, targets, 0, 0);
    }
    
    /**
     * 处理index位置贴纸 0~index-1已经处理了
     * @param useMap    贴纸map
     * @param targets   匹配目标字符剩余未匹配的
     * @param index    来到贴纸位置
     * @param count    已经使用的贴纸数量
     * @return
     */
    private static int process(int[][] useMap, int[] targets, int index, int count) {
        // 未用完贴纸 字符串已经全部全部匹配完 返回使用贴纸数量
        if (index <= useMap.length && isEmpty(targets)) {
            return count;
        }
        // 如果来到最后位置 还没有匹配上贴纸 直接返回最大值 表示无解
        if (index == useMap.length) {
            return Integer.MAX_VALUE;
        }
        // 当前行数的字符串 即数组useArray index位置
        int[] ints = useMap[index];
        // 贴纸上至少有一个目标字符状态
        boolean leastOneChar = false;
        int[] tmp = new int[targets.length];
        System.arraycopy(targets, 0, tmp, 0, targets.length);
        for (int i = 0; i < ints.length; i++) {
            if (!leastOneChar && tmp[i] > 0 && ints[i] > 0) {
                leastOneChar = true;
            }
            tmp[i] = Math.max(tmp[i] - ints[i], 0);
        }
        // 当前字符串没办法减少目标字符串 没有使用当前贴纸
        if (!leastOneChar) {
            return process(useMap, targets, index + 1, count);
        } else {
            // 使用当前贴纸 然后继续使用当前贴纸
            int use1 = process(useMap, tmp, index, count + 1);
            // 不使用当前贴纸 来到下一个贴纸位置
            int use2 = process(useMap, targets, index + 1, count);
            // 那种方案使用贴纸最少
            return Math.min(use1, use2);
        }
    }
    
    public static int minUseStringCount2(String[] useArray, String target) {
        if (useArray == null || useArray.length <= 0 || target == null) {
            return 0;
        }
        // 贴纸数组转换为int类型ASCII码
        // 行对应数组中字符 列为每个字符串
        int[][] useMap = new int[useArray.length][26];
        for (int i = 0; i < useArray.length; i++) {
            String s = useArray[i];
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                useMap[i][aChar - 'a']++;
            }
        }
        int[] targets = new int[26];
        for (char c : target.toCharArray()) {
            targets[c - 'a'] ++;
        }
        return process2(useMap, targets);
    }
    
    private static int process2(int[][] useMap, int[] targets) {
        // 空字符串需要0张贴纸
        if (isEmpty(targets)) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < useMap.length; i++) {
            // 必须处理有目标开头字符的贴纸 缩小范围
            int[] ints = useMap[i];
            if (!isHaveSameChar(ints, targets)) {
                continue;
            }
            int[] tmp = new int[targets.length];
            System.arraycopy(targets, 0, tmp, 0, targets.length);
            for (int j = 0; j < 26; j++) {
                if (targets[j] > 0) {
                    tmp[j] = Math.max(tmp[j] - ints[j], 0);
                }
            }
            int ans = process2(useMap, tmp);
            if (ans != -1) {
                result = Math.min(ans + 1, result);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    public static int minUseStringCount3(String[] useArray, String target) {
        if (useArray == null || useArray.length <= 0 || target == null) {
            return 0;
        }
        // 贴纸数组转换为int类型ASCII码
        // 行对应数组中字符 列为每个字符串
        int[][] useMap = new int[useArray.length][26];
        for (int i = 0; i < useArray.length; i++) {
            String s = useArray[i];
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                useMap[i][aChar - 'a']++;
            }
        }
        return process3(useMap, target);
    }
    
    private static int process3(int[][] useMap, String rest) {
        // 空字符串需要0张贴纸
        if ("".equals(rest)) {
            return 0;
        }
        int[] targets = new int[26];
        char[] chars = rest.toCharArray();
        for (char c : chars) {
            targets[c - 'a'] ++;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < useMap.length; i++) {
            // 必须处理有目标开头字符的贴纸 缩小范围
            int[] ints = useMap[i];
            if (ints[chars[0] - 'a'] == 0 ) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (targets[j] > 0) {
                    for (int k = 0; k < Math.max(targets[j] - ints[j], 0); k++) {
                        sb.append((char) (j + 'a'));
                    }
                }
            }
            String tmpTarget = sb.toString();
            int ans = process3(useMap, tmpTarget);
            if (ans != -1) {
                result = Math.min(ans + 1, result);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    public static int minUseStringCount4(String[] useArray, String target) {
        if (useArray == null || useArray.length <= 0 || target == null) {
            return 0;
        }
        // 贴纸数组转换为int类型ASCII码
        // 行对应数组中字符 列为每个字符串
        int[][] useMap = new int[useArray.length][26];
        for (int i = 0; i < useArray.length; i++) {
            String s = useArray[i];
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                useMap[i][aChar - 'a']++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("", 0);
        return process4(useMap, target, map);
    }
    
    private static int process4(int[][] useMap, String rest, Map<String, Integer> map) {
        // 空字符串需要0张贴纸
        if (map.containsKey(rest)) {
            return map.get(rest);
        }
        int[] targets = new int[26];
        char[] chars = rest.toCharArray();
        for (char c : chars) {
            targets[c - 'a'] ++;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < useMap.length; i++) {
            // 必须处理有目标开头字符的贴纸 缩小范围
            int[] ints = useMap[i];
            if (ints[chars[0] - 'a'] == 0 ) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (targets[j] > 0) {
                    for (int k = 0; k < Math.max(targets[j] - ints[j], 0); k++) {
                        sb.append((char) (j + 'a'));
                    }
                }
            }
            String tmpTarget = sb.toString();
            int ans = process4(useMap, tmpTarget, map);
            if (ans != -1) {
                result = Math.min(ans + 1, result);
            }
        }
        map.put(rest, result == Integer.MAX_VALUE ? -1 : result);
        return map.get(rest);
    }
    
    /**
     * 判断目标字符串是否已经处理完毕
     * @param target
     * @return
     */
    private static boolean isEmpty(int[] target) {
        boolean isEmpty = true;
        for (int i = 0; i < target.length; i++) {
            if (target[i] > 0) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }
    
    private static boolean isHaveSameChar(int[] ints, int[] targets) {
        boolean leastOneChar = false;
        for (int j = 0; j < ints.length; j++) {
            if (targets[j] > 0 && ints[j] > 0) {
                leastOneChar = true;
            }
        }
        return leastOneChar;
    }
    
    public static void main(String[] args) {
        String[] useArray = {"aa", "acb", "aacc", "cc", "abb"};
        String target = "aabbcc";
        System.out.println(minUseStringCount(useArray, target));
        System.out.println(minUseStringCount2(useArray, target));
        System.out.println(minUseStringCount3(useArray, target));
        System.out.println(minUseStringCount4(useArray, target));
    }
}
