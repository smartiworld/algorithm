package com.iworld.algorithm.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author gq.cai
 * @version 1.0
 * @description 给定一个job数组表示 工作可以提供的报酬和工作难度 选定工作在不超过难度下 选择最高报酬岗位 给定一个数组表示 所有人的能力
 * 返回int类型数组 表示每个人按照标准选择选择工作所获得最高报酬
 * @date 2022/12/28 18:02
 */
public class ChooseWork {
    
    /**
     * 1.对job数组进行排序 先按照难度进行排序 难度相同 按照报酬排序
     * 2.相同难度挑选出收益高的工
     * 3.找出小于等于能力的报酬 返回
     * @param jobs
     * @param ability
     * @return
     */
    public int[] getMoneys(Job[] jobs, int[] ability) {
        // 对job数组排序 hard升序 money降序
        Arrays.sort(jobs, new JobComparator());
        TreeMap<Integer, Integer> jobMap = new TreeMap<>();
        jobMap.put(jobs[0].hard, jobs[0].money);
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].hard != jobs[i - 1].hard && jobs[i].money > jobs[i - 1].money) {
                jobMap.put(jobs[i].hard, jobs[i].money);
            }
        }
        int[] ans = new int[jobs.length];
        for (int i = 0; i < ability.length; i++) {
            // 找出等于小于当前能力的工作
            Integer hard = jobMap.floorKey(ability[i]);
            ans[i] = hard == null ? 0 : jobMap.get(hard);
        }
        return ans;
    }
    
    public static class JobComparator implements Comparator<Job> {
        
        @Override
        public int compare(Job j1, Job j2) {
            return j1.hard == j2.hard ? j2.money - j1.money : j1.hard - j2.hard;
        }
    }
    
    public static class Job {
        private int money;
        private int hard;
    }
    
    public static void main(String[] args) {
        long orderId = 55633275;
        String version = "t_order_price_version_shard_";
        String price = "t_order_price_shard_";
        String record = "t_order_price_calc_record_shard_";
        System.out.println(version + (orderId % 4));
        System.out.println(record + (orderId % 4));
        System.out.println(price + (orderId % 16));
        String aa = "12345678956";
        String s = aa.replaceAll("\\d{4}", "****");
        System.out.println(s);
        StringBuilder sb = new StringBuilder(aa);
        sb.replace(3, 7, "****");
        System.out.println(sb.toString());
    }
}
