package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description 折纸条
 * 竖放一根纸条 然后哦从下往上折 对折后取开 是凹折痕 然后再把纸条从下向上对折两次 然后是下折痕 下折痕和上折痕
 * 给定一个参数N然后 从上向下依次输出纸条折痕 down down up
 * 对折多次后发现规律在上次出现折痕的地方上面是下折痕下面是上折痕
 * 构造二叉树 左子树为下折痕
 * @date 2022/4/26 13:25
 */
public class OrigamiStrips {

    public static void strips(int n) {
        if (n == 0) {
            return ;
        }
        process(1, n, true);
    }
    
    /**
     * @param i       来到了第几层
     * @param n       总层数
     * @param isDown  是否是下折痕
     */
    private static void process(int i, int n, boolean isDown) {
        if (i > n) {
            return ;
        }
        process(i + 1, n, true);
        System.out.println("i==" + i + (isDown ? " down" : " up"));
        process(i + 1, n, false);
    }
    
    public static void main(String[] args) {
        strips(3);
    }
}
