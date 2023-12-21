package com.iworld.algorithm.array.heap;

import com.iworld.algorithm.util.MapUtils;
import com.iworld.algorithm.util.SmartStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.3.6 给一些字符串 返回top k
 * 请实现如下结构：
 * TopRecord{
 * public TopRecord(int K)  :  构造时事先指定好K的大小，构造后就固定不变了
 * public  void add(String str)  :   向该结构中加入一个字符串，可以重复加入
 * public  List<String> top() : 返回之前加入的所有字符串中，词频最大的K个
 * }
 * 要求：
 * add方法，复杂度O(log K);
 * top方法，复杂度O(K)
 * @date 2023/12/20 14:29
 */
public class TopRecord {
    /**
     * 字符串出现次数
     */
    private Map<String, Integer> timesMap;
    /**
     * 字符串在数组位置
     */
    private Map<String, Integer> indexMap;
    /**
     * 实际top k 词频数组 小根堆
     */
    private TopRecordCountInfo[] topRecordCountInfos;
    
    private int size = 0;
    
    private int k;
    
    public TopRecord(int k) {
        topRecordCountInfos = new TopRecordCountInfo[k];
        timesMap = new HashMap<>();
        indexMap = new HashMap<>();
        this.k = k;
    }
    
    public void add(String str) {
        int index = -1;
        int times = 1;
        if (indexMap.get(str) == null) {
            indexMap.put(str, index);
            timesMap.put(str, times);
        } else {
            index = indexMap.get(str);
            times = timesMap.get(str) + 1;
            timesMap.put(str, times);
        }
        if (index == -1) {
            if (size < k) {
                topRecordCountInfos[size] = new TopRecordCountInfo(str, times);
                indexMap.put(str, size);
                upward(size++);
            } else if (topRecordCountInfos[0].count < times) {
                indexMap.put(topRecordCountInfos[0].str, -1);
                topRecordCountInfos[0] = new TopRecordCountInfo(str, times);
                indexMap.put(str, 0);
                down(0);
            }
        } else {
            topRecordCountInfos[index].count++;
            down(index);
        }
    }
    
    private void upward(int index) {
        int cur = index;
        while (cur > 0) {
            int parent = (cur - 1) >> 1;
            if (topRecordCountInfos[cur].count >= topRecordCountInfos[parent].count) {
                break;
            }
            swap(parent, cur);
            cur = parent;
        }
    }
    
    /**
     * 小根堆 大值下沉
     * @param index
     */
    private void down(int index) {
        int parent = index;
        int left = (parent << 1) + 1;
        while (left < size) {
            TopRecordCountInfo parentInfo = topRecordCountInfos[parent];
            int right = (parent << 1) + 2;
            int less = right >= size || topRecordCountInfos[right] == null || topRecordCountInfos[left].count < topRecordCountInfos[right].count ? left : right;
            if (parentInfo.count <= topRecordCountInfos[less].count) {
                break;
            }
            swap(parent, less);
            parent = less;
            left = (parent << 1) + 1;
        }
    }
    
    public List<String> top() {
        List<String> result = new ArrayList<>();
        for (TopRecordCountInfo topRecordCountInfo : topRecordCountInfos) {
            if (topRecordCountInfo == null) {
                break;
            }
            result.add(topRecordCountInfo.str);
        }
        return result;
    }
    
    private void swap(int parent, int child) {
        TopRecordCountInfo topRecordCountInfo = topRecordCountInfos[parent];
        topRecordCountInfos[parent] = topRecordCountInfos[child];
        topRecordCountInfos[child] = topRecordCountInfo;
        indexMap.put(topRecordCountInfos[parent].str, parent);
        indexMap.put(topRecordCountInfos[child].str, child);
    }
    
    public static class TopRecordCountInfo {
        public String str;
        
        public int count;
        
        public int index;
        
        public TopRecordCountInfo(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
    
    public static void main(String[] args) {
        int k = 5;
        TopRecord topRecord = new TopRecord(k);
        int times = 10000;
        for (int i = 0; i< times; i++) {
            String s = SmartStringUtils.generatorLowerCaseLetters(1);
            topRecord.add(s);
            MapUtils.printMap(topRecord.timesMap);
            System.out.println(topRecord.top());
        }
        int min = topRecord.topRecordCountInfos[0].count;
        Set<String> set = new HashSet<>();
        for (TopRecordCountInfo topRecordCountInfo : topRecord.topRecordCountInfos) {
            if (topRecordCountInfo.count < min) {
                System.out.println("error");
                break;
            }
            set.add(topRecordCountInfo.str);
        }
        for (Map.Entry<String, Integer> entry : topRecord.timesMap.entrySet()) {
            if (entry.getValue() > min && !set.contains(entry.getKey())) {
                System.out.println("map error");
                break;
            }
        }
//        String ss = "xy:1,hj:1,hk:1,ty:1,hm:1,pu:1,ls:1,ho:1,dk:1,dl:1,lw:1,ua:1,hx:1,yo:1,id:1,mi:1,yx:1,qq:1,ij:1,ef:1,qs:2,eg:1,mq:1,in:1,af:1,mt:1,ai:1,ir:1,ak:1,ep:1,iu:1,my:1,vc:2,ao:1,ix:1,vi:1,au:1,nb:1,vj:1,vk:1,nd:1,zp:1,zv:1,vt:1,nm:1,bb:1,no:1,jm:1,vz:1,jn:1,fj:1,rx:1,jq:2,bi:1,nv:1,bl:1,nx:1,ju:1,fr:1,fu:1,wg:1,wi:1,oa:1,sg:1,oe:1,of:1,oi:1,so:1,om:1,ww:1,cc:2,st:1,kl:1,gk:1,ko:1,gl:1,gm:1,cj:1,sz:1,xa:1,xc:1,xe:1,cp:1,xf:1,gx:1,gz:1,cx:1,tj:1,xo:1,pi:1,tn:1,lh:1,he:1,tq:1,db:1,hg:1";
//        String[] split = ss.split(",");
//        for (String s : split) {
//            System.out.println(s);
//            if (Integer.parseInt(s.split(":")[1]) == 2) {
//                System.out.println();
//            }
//            for (int j = 0; j < Integer.parseInt(s.split(":")[1]); j++) {
//                if (s.split(":")[0].equals("cc")) {
//                    System.out.println();
//                }
//                System.out.println(s.split(":")[0]);
//                topRecord.add(s.split(":")[0]);
//                MapUtils.printMap(topRecord.timesMap);
//                System.out.println(topRecord.top());
//            }
//
//        }
//        topRecord.add("cc");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
//        topRecord.add("xy");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
//        topRecord.add("xy");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
//        topRecord.add("qs");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
//        topRecord.add("vc");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
//        topRecord.add("hj");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
//        topRecord.add("hj");
//        MapUtils.printMap(topRecord.timesMap);
//        System.out.println(topRecord.top());
    }
}
