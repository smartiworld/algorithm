package com.iworld.algorithm.array.heap;

import com.iworld.algorithm.util.ArrayUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.3.5 词频最大的前K个字符串
 * 给定一个由字符串组成的数组String[] strs，给定一个正数K
 *
 * 返回词频最大的前K个字符串，假设结果是唯一的
 * @date 2023/12/19 14:23
 */
public class TopKTimesElements {
    
    public static String[] topKTimesElements(String[] strs, int k) {
        Map<String, ElementCountInfo> map = new HashMap<>();
        for (String str : strs) {
            ElementCountInfo elementCountInfo = map.get(str);
            if (elementCountInfo == null) {
                elementCountInfo = new ElementCountInfo(str, 1);
                map.put(str, elementCountInfo);
            } else {
                elementCountInfo.count ++;
            }
        }
        ElementCountInfo[] elementCountInfos = new ElementCountInfo[map.size()];
        int index = 0;
        for (Map.Entry<String, ElementCountInfo> entry : map.entrySet()) {
            elementCountInfos[index++] = entry.getValue();
        }
        // 获取的刚好是前k大
        String[] result = new String[k];
        ElementCountInfo topKTimesElement = getKthTimesElements(elementCountInfos, index - k);
        if (topKTimesElement != null) {
            int i = 0;
            result[i++] = topKTimesElement.str;
            for (ElementCountInfo elementCountInfo : elementCountInfos) {
                if (!topKTimesElement.str.equals(elementCountInfo.str) && elementCountInfo.count >= topKTimesElement.count) {
                    result[i++] = elementCountInfo.str;
                }
            }
        }
        return result;
    }
    
    /**
     * 获取出现第k次数 字符串
     * @param elementCountInfos
     * @param k
     * @return
     */
    public static ElementCountInfo getKthTimesElements(ElementCountInfo[] elementCountInfos, int k) {
        int l = 0;
        int r = elementCountInfos.length - 1;
        while (l <= r) {
            int less = l;
            int big = r;
            int index = l;
            ElementCountInfo elementCountInfo = elementCountInfos[l + (int)(Math.random() * (r - l + 1))];
            while (index <= big) {
                if (elementCountInfo.count > elementCountInfos[index].count) {
                    swap(elementCountInfos, less++, index++);
                } else if (elementCountInfo.count < elementCountInfos[index].count) {
                    swap(elementCountInfos, index, big--);
                } else {
                    index++;
                }
            }
            if (less <= k && k <= big) {
                return elementCountInfos[less];
            } else if (k < less) {
                r = less - 1;
            } else {
                l = big + 1;
            }
        }
        return null;
    }
    
    private static void swap(ElementCountInfo[] elementCountInfos, int l, int r) {
        ElementCountInfo tmp = elementCountInfos[l];
        elementCountInfos[l] = elementCountInfos[r];
        elementCountInfos[r] = tmp;
    }
    
    public static class ElementCountInfo {
        private String str;
        private int count;
        public ElementCountInfo(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
    
    public static String[] topKTimesElementsHeap(String[] strs, int k) {
        Map<String, ElementCountInfo> map = new HashMap<>();
        for (String str : strs) {
            ElementCountInfo elementCountInfo = map.get(str);
            if (elementCountInfo == null) {
                elementCountInfo = new ElementCountInfo(str, 1);
                map.put(str, elementCountInfo);
            } else {
                elementCountInfo.count ++;
            }
        }
        PriorityQueue<ElementCountInfo> heap = new PriorityQueue<>(k, new ElementCountInfoComparator());
        for (Map.Entry<String, ElementCountInfo> entry : map.entrySet()) {
            ElementCountInfo elementCountInfo = entry.getValue();
            if (heap.size() < k) {
                heap.add(elementCountInfo);
            } else if (elementCountInfo.count > heap.peek().count) {
                heap.poll();
                heap.add(elementCountInfo);
            }
        }
        // 获取的刚好是前k大
        String[] result = new String[k];
        int index = 0;
        while (!heap.isEmpty()) {
            result[index++] = heap.poll().str;
        }
        return result;
    }
    
    public static class ElementCountInfoComparator implements Comparator<ElementCountInfo> {
    
        @Override
        public int compare(ElementCountInfo e1, ElementCountInfo e2) {
            return e1.count - e2.count;
        }
    }
    
    public static void main(String[] args) {
        String[] strs = {"aa", "sss", "aa", "sfwer", "rwer", "aa", "sss", "gesfe", "dd", "dd", "sss"};
        int k = 2;
        String[] strings = topKTimesElements(strs, k);
        ArrayUtil.printArray(strings);
        String[] strings1 = topKTimesElementsHeap(strs, k);
        ArrayUtil.printArray(strings1);
    }
}
