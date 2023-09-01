package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 打表技巧 292.Nim游戏
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 *
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合， 你作为先手 。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：false
 * 解释：以下是可能的结果:
 * 1. 移除1颗石头。你的朋友移走了3块石头，包括最后一块。你的朋友赢了。
 * 2. 移除2个石子。你的朋友移走2块石头，包括最后一块。你的朋友赢了。
 * 3.你移走3颗石子。你的朋友移走了最后一块石头。你的朋友赢了。
 * 在所有结果中，你的朋友是赢家。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：true
 * 示例 3：
 *
 * 输入：n = 2
 * 输出：true
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/nim-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/5/23 16:00
 */
public class NimGame {
    
    public boolean canWinNimDp(int n) {
        if (n <= 3) {
            return true;
        }
        // 下标表示石头数量-1 true表示赢 当前数组表示先手输赢
        boolean[] f = new boolean[n];
        // 到后手时 自己输赢
        boolean[] s = new boolean[n];
        f[0] = true;
        f[1] = true;
        f[2] = true;
        s[0] = false;
        s[1] = false;
        s[2] = false;
        for (int i = 3; i < n; i++) {
            // 每人可以拿1-3 所以当前先手为i时依赖后手分别取一个，两个，三个 自己在后手赢
            f[i] = s[i - 1] || s[i - 2] || s[i - 3];
            // 如果先手赢 后手则输
            s[i] = !f[i];
        }
        return f[n - 1];
    }
    
    public boolean canWinNim(int n) {
        return f(n);
    }
    
    /**
     * 先手函数
     * @param n
     * @return
     */
    private boolean f(int n) {
        if (n <= 3) {
            return true;
        }
        return s(n - 1) || s(n - 2) || s(n - 3);
    }
    
    /**
     * 如果后手小于3了先手失败
     * @param n
     * @return
     */
    private boolean s(int n) {
        if (n <= 3) {
            return false;
        }
        return !f(n);
    }
    
    public boolean canWinNimOpt(int n) {
        return process(n);
    }
    
    /**
     * 先手依赖后手操作
     * f(i) 依赖s(i-1) s(i-2) s(i-3)
     * @param n
     * @return
     */
    private boolean process(int n) {
        if (n <= 3) {
            return true;
        }
        return !process(n - 1) || !process(n - 2) || !process(n - 3);
    }
    
    public boolean canWinNimOptDp(int n) {
        if (n <= 3) {
            return true;
        }
        boolean[] f = new boolean[n];
        f[0] = true;
        f[1] = true;
        f[2] = true;
        for (int i = 3; i < n; i++) {
            f[i] = !(f[i - 1] && f[i - 2] && f[i - 3]);
        }
        return f[n - 1];
    }
    
    /**
     * canWinNimOptDp
     * f[i] = !(f[i - 1] && f[i - 2] && f[i - 3]);
     * 当前位置和前三位置有关 并且是只要有一个为false当前位置就为true 只有在前面三个都为true的时候 当前位置才为false
     * 推出公式 x%(y+1)!=0  x表示一共多少石子  y表示一次最多可以拿几个
     * @param n
     * @return
     */
    public boolean canWinNimFinish(int n) {
        return n % 4 != 0;
    }
    
    public static void main(String[] args) {
        NimGame nimGame = new NimGame();
        int num = 40;
        long start = System.currentTimeMillis();
        System.out.println(nimGame.canWinNim(num));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(nimGame.canWinNimDp(num));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(nimGame.canWinNimOpt(num));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(nimGame.canWinNimOptDp(num));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(nimGame.canWinNimFinish(num));
        System.out.println(System.currentTimeMillis() - start);
    }
}
