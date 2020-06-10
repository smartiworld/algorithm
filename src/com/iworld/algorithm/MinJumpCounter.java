package com.iworld.algorithm;

/**
 * 动态规划
 * 最小跳跃次数
 *
 * 游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。
 * 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
 * 示例 1：
 * 输入：jump = [2, 5, 1, 1, 1, 1]
 * 输出：3
 * 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
 * 限制：
 *
 * 1 <= jump.length <= 10^6
 * 1 <= jump[i] <= 10000
 *
 * @Autor iworld
 * @Date 2020-05-28 15:00
 */
public class MinJumpCounter {

    public static void main(String[] args) {
        int[] jumps = new int[]{2, 5, 1, 1, 1, 1};
        int minJump = minJump(jumps);
        System.out.println(minJump);
    }

    public static int minJump(int[] jumps){
        int[] dp = new int[jumps.length];

        dp[jumps.length-1] = 1;
        for(int i = jumps.length - 2; i >= 0; i--){
            dp[i] = jumps[i] + i >= jumps.length ? 1: dp[jumps[i] + i] + 1;
            //遍历当前位置更新后影响到的后面的位置，只需要更新到dp[j] >= dp[i]+1即可
            //如果遍历到某dp[j]<dp[i]+1就不需要向右遍历了,因为j到dp.length的值会被当前遍历到的dp[j]更新而不是dp[i]+1
            for(int j = i + 1; j < dp.length && dp[j] >= dp[i] + 1 ; j++){
                dp[j] = dp[i] + 1;
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[0];
    }
}
