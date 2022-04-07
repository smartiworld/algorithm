package com.iworld.algorithm.greedy;

import java.util.HashSet;

/**
 * @author gq.cai
 * @version 1.0
 * @description 安装最少数量的灯
 * 给定一个字符串X表示墙，不可以放灯，不需要点亮，
 * .表示居民点，可以放灯，需要被点亮，如果灯放i位置，i-1和i+1位置可以被点亮。
 * 点亮str需要被点亮位置，至少需要多少灯
 * @date 2022/4/5 17:00
 */
public class ArrangeLight {
    
    /**
     * 暴力解 穷举各位置放灯和不放灯
     * @param positions
     * @return
     */
    public int minLight(String positions) {
        if (positions == null || positions.length() == 0) {
            return 0;
        }
        return process(positions.toCharArray(), 0, new HashSet<>());
    }
    
    /**
     * index 放灯与否，index前位置已处理，放灯位置放入light中
     * @param positions
     * @param index
     * @param lights
     * @return
     */
    private int process(char[] positions, int index, HashSet<Integer> lights) {
        if (index == positions.length) {
            for (int i = 0; i < positions.length; i++) {
                if (positions[i] == '.') {
                    if (!lights.contains(i - 1) && !lights.contains(i + 1) && !lights.contains(i)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(positions, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (positions[index] == '.') {
                lights.add(index);
                yes = process(positions, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(yes, no);
        }
    }
    
    /**
     * 如果当前位置为X不需要放灯，如果当前位置为.，此时必须装灯
     * 决定下次装灯的位置，看后一位置是否为.如果不为.则跳到.后一个位置(.X跳到X后位置)
     * 如果为.，看后位置，最多跳过三个.(... 跳最后一个)
     * @param position
     * @return
     */
    private int minLightUseGreedy(String position) {
        if (position == null || position.length() < 1) {
            return 0;
        }
        char[] chars = position.toCharArray();
        int result = 0;
        int index = 0;
        while (index < chars.length) {
            // 不管第一个位置是.还是X都需要走一步index+1
            if (chars[index++] == '.') {
                // 只要第一个位置有了. 一定需要放灯
                result ++;
                if (index == chars.length) {
                    break;
                }
                // 并且位置跳到下一个位置 index+2
                if (chars[index++] == '.') {
                    //如果第二个位置还是.  则继续跳到下一个位置 index+3
                    index ++;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        String s = "X..X......X.X....X...XXX....";
        ArrangeLight arrangeLight = new ArrangeLight();
        int i = arrangeLight.minLight(s);
        System.out.println(i);
        int i1 = arrangeLight.minLightUseGreedy(s);
        System.out.println(i1);
    }
}