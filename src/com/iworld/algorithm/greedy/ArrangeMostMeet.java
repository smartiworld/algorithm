package com.iworld.algorithm.greedy;

import java.util.Arrays;

/**
 * @author gq.cai
 * @version 1.0
 * @description 会议室安排，有会议开始时间和会议结束时间 怎么安排使安排会议数量最大
 * @date 2022/4/5 14:57
 */
public class ArrangeMostMeet {
    
    /**
     * 暴力解  全排列所有可以安排一块的会议
     * @param meets
     * @return
     */
    public int arrangeMeeting(Meet[] meets) {
        if (meets == null || meets.length == 0) {
            return 0;
        }
        return process(meets, 0, 0);
    }
    
    /**
     * 全排列
     * @param meets    剩余未安排的会议
     * @param done     已经安排了多少场会议
     * @param timeEnd  会议结束时间来到了什么时候
     * @return
     */
    private int process(Meet[] meets, int done, int timeEnd) {
        if (meets.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < meets.length; i++) {
            // 不会重复条件
            if (meets[i].start > timeEnd) {
                max = Math.max(max, process(copyOtherMeet(meets, i), done + 1, meets[i].end));
            }
        }
        return max;
    }
    
    /**
     * 对会议排序按照会议结束时间升序
     * 安排结束时间早的会议
     * @param meets
     * @return
     */
    public int arrangeMeetingUseGreedy(Meet[] meets) {
        int result = 0;
        Arrays.sort(meets, ((o1, o2) -> o1.end - o2.end));
        int timeEnd = 0;
        for (int i = 0; i < meets.length; i++) {
            if (meets[i].start > timeEnd) {
                timeEnd = meets[i].end;
                result ++;
            }
        }
        return result;
    }
    
    /**
     * 复制meets数组除去i位置
     * @param meets
     * @param i
     * @return
     */
    private Meet[] copyOtherMeet(Meet[] meets, int i) {
        Meet[] newMeets = new Meet[meets.length - 1];
        int index = 0;
        for (int j = 0; j < meets.length; j++) {
            if (i != j) {
                newMeets[index++] = meets[j];
            }
        }
        return newMeets;
    }
    
    public static void main(String[] args) {
        Meet[] meets = {new Meet(2, 4), new Meet(1, 4), new Meet(3, 4), new Meet(4, 5), new Meet(2, 5), new Meet(4, 6), new Meet(5, 6), new Meet(5, 7), new Meet(7, 9)};
        ArrangeMostMeet arrangeMostMeet = new ArrangeMostMeet();
        int i = arrangeMostMeet.arrangeMeeting(meets);
        System.out.println(i);
        int i1 = arrangeMostMeet.arrangeMeetingUseGreedy(meets);
        System.out.println(i1);
    }
    
    
    public static class Meet {
        // 会议开始时间
        public int start;
        // 会议结束时间
        public int end;
        
        public Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
