package com.iworld.algorithm.dp.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 组合字符串  动态规划  记忆化搜索
 * 给定一个字符串 给定一个字符类型的数组，arr中每个字符串代表一个贴纸，可以把单个字符剪开使用
 * 拼接出给定的字符串，返回需要至少多少张贴纸可以完成任务
 * @date 2022/5/15 20:55
 */
public class DPSpliceString {

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
        // 目标字符串转换为数字类型
        int[] targets = new int[26];
        for (char c : target.toCharArray()) {
            targets[c - 'a'] ++;
        }
        return process(useMap, targets, 0, 0);
    }
    
    /**
     * 处理index位置贴纸 0~index-1已经处理了
     * 数组下标为字符码
     * 1.如果taget数组已经没有未匹配字符数量 表示当前使用的贴纸可以拼出target字符
     * 2.如果目标字符没有匹配出 并且已经走到最后贴纸 表示之前选择的贴纸不能匹配目标字符
     * 3.检查当前位置贴纸是否可以匹配目标字符
     * 3.1不可以匹配则直接到来下一个贴纸位置
     * 3.2可以匹配 目标字符减掉当前贴纸 来到下个位置
     * 3.3可以匹配 也可以不选择当前贴纸 直接来到下一个位置
     * 3.4将3.2和3.3结果选出使用贴纸最小数量
     * @param useMap    贴纸map                行为每个贴纸 列为当前贴纸的字符 value表示当前贴纸字符个数
     * @param targets   匹配目标字符剩余未匹配的  目标字符数量
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
        // 使用当前贴纸 检查贴纸中字符是否存在可以减掉目标字符leastOneChar
        for (int i = 0; i < ints.length; i++) {
            if (!leastOneChar && tmp[i] > 0 && ints[i] > 0) {
                // 当前贴纸字符可以匹配目标字符
                leastOneChar = true;
            }
            // 当前字符使用贴纸后还剩多少个字符没有匹配
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
    
    /**
     * 匹配后 每次从开始贴纸匹配
     * 从头贴纸开始 每个贴纸使用后从头贴纸再匹配 直到全部匹配
     * 如果当前贴纸不匹配 跳出当前循环
     * @param useMap   贴纸  固定不变
     * @param targets  目标数组
     * @return
     */
    private static int process2(int[][] useMap, int[] targets) {
        if (isEmpty(targets)) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < useMap.length; i++) {
            // 当前行数的字符串 即数组useArray index位置
            int[] ints = useMap[i];
            boolean leastOneChar = false;
            for (int j = 0; j < ints.length; j++) {
                if (targets[j] > 0 && ints[j] > 0) {
                    leastOneChar = true;
                    break;
                }
            }
            // 当前位置贴纸 不能匹配目标字符串的任何位置
            if (!leastOneChar) {
                continue;
            }
            int[] tmp = new int[targets.length];
            System.arraycopy(targets, 0, tmp, 0, targets.length);
            for (int j = 0; j < ints.length; j++) {
                tmp[j] = Math.max(tmp[j] - ints[j], 0);
            }
            int ans = process2(useMap, tmp);
            if (ans != -1) {
                result = Math.min(result, ans + 1);
            }
        }
        return result == Integer.MAX_VALUE ?  -1 : result;
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
            // 尝试当前贴纸是否有匹配字符首个字符 不存在则不需要当前贴纸
            if (ints[chars[0] - 'a'] == 0 ) {
                continue;
            }
            // 收集未完全被贴纸匹配的字符
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
    
    public static int minUseStringCount3Cache(String[] useArray, String target) {
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
        return process3Cache(useMap, target, map);
    }
    
    private static int process3Cache(int[][] useMap, String rest, Map<String, Integer> map) {
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
            // 贴纸不存在目标字符首字母
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
            int ans = process3Cache(useMap, tmpTarget, map);
            if (ans != -1) {
                result = Math.min(ans + 1, result);
            }
        }
        map.put(rest, result == Integer.MAX_VALUE ? -1 : result);
        return map.get(rest);
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
        return process4(useMap, target, 0);
    }
    
    /**
     * 处理index位置贴纸 0~index-1已经处理了
     * @param useMap    贴纸map
     * @param rest   匹配目标字符剩余未匹配的
     * @param index    来到贴纸位置
     * @return
     */
    private static int process4(int[][] useMap, String rest, int index) {
        // 未用完贴纸 字符串已经全部全部匹配完 返回使用贴纸数量
        if ("".equals(rest)) {
            return 0;
        }
        // 如果来到最后位置 还没有匹配上贴纸 直接返回最大值 表示无解
        if (index == useMap.length) {
            return -1;
        }
        // 当前行数的字符串 即数组useArray index位置
        int[] ints = useMap[index];
        // 贴纸上至少有一个目标字符状态
        boolean leastOneChar = false;
        char[] chars = rest.toCharArray();
        int[] targets = new int[26];
        for (char aChar : chars) {
            targets[aChar - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            if (targets[i] > 0) {
                if (!leastOneChar && ints[i] > 0) {
                    leastOneChar = true;
                }
            }
            for (int j = 0; j < Math.max(targets[i] - ints[i], 0); j++) {
                sb.append((char) (i + 'a'));
            }
        }
        // 当前字符串没办法减少目标字符串 没有使用当前贴纸
        if (!leastOneChar) {
            return process4(useMap, rest, index + 1);
        } else {
            // 使用当前贴纸 然后继续使用当前贴纸 使用则在返回结果+1
            int use1 = process4(useMap, sb.toString(), index);
            // 不使用当前贴纸 来到下一个贴纸位置  不使用结果不变
            int use2 = process4(useMap, rest, index + 1);
            if (use1 == -1 && use2 == -1) {
                return -1;
            }
            if (use1 == -1) {
                return use2;
            }
            if (use2 == -1) {
                return use1 + 1;
            }
            // 那种方案使用贴纸最少
            return Math.min(use1 + 1, use2);
        }
    }
    
    public static int minUseStringCount4Cache(String[] useArray, String target) {
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
        map.put("0" + "", 0);
        return process4Cahce(useMap, target, 0, map);
    }
    
    /**
     * 处理index位置贴纸 0~index-1已经处理了
     * @param useMap    贴纸map
     * @param rest   匹配目标字符剩余未匹配的
     * @param index    来到贴纸位置
     * @return
     */
    private static int process4Cahce(int[][] useMap, String rest, int index, Map<String, Integer> map) {
        String key = index + rest;
        // 未用完贴纸 字符串已经全部全部匹配完 返回使用贴纸数量
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if ("".equals(rest)) {
            return 0;
        }
        // 如果来到最后位置 还没有匹配上贴纸 直接返回最大值 表示无解
        if (index == useMap.length) {
            return -1;
        }
        // 当前行数的字符串 即数组useArray index位置
        int[] ints = useMap[index];
        // 贴纸上至少有一个目标字符状态
        boolean leastOneChar = false;
        char[] chars = rest.toCharArray();
        int[] targets = new int[26];
        for (char aChar : chars) {
            targets[aChar - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            if (targets[i] > 0) {
                if (!leastOneChar && ints[i] > 0) {
                    leastOneChar = true;
                }
                for (int j = 0; j < Math.max(targets[i] - ints[i], 0); j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        int result = -1;
        // 当前字符串没办法减少目标字符串 没有使用当前贴纸
        if (!leastOneChar) {
            result = process4Cahce(useMap, rest, index + 1, map);
        } else {
            // 使用当前贴纸 然后继续使用当前贴纸
            int use1 = process4Cahce(useMap, sb.toString(), index, map);
            // 不使用当前贴纸 来到下一个贴纸位置
            int use2 = process4Cahce(useMap, rest, index + 1, map);
            if (use1 != -1 && use2 != -1) {
                result = Math.min(use1 + 1, use2);
            } else if (use1 != -1) {
                result = use1 + 1;
            } else if (use2 != -1) {
                result = use2;
            }
        }
        map.put(key, result);
        return result;
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
        String[] useArray = {"aaaa", "acb", "aaaccc", "cc", "abbabc"};
        String target = "aabbccbbaaccbcacbaa";
        System.out.println(minUseStringCount(useArray, target));
        System.out.println(minUseStringCount2(useArray, target));
        System.out.println(minUseStringCount3(useArray, target));
        System.out.println(minUseStringCount4(useArray, target));
        // key可变参数少
        System.out.println(minUseStringCount3Cache(useArray, target));
        System.out.println(minUseStringCount4Cache(useArray, target));
    }
}
