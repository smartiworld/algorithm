package com.iworld.algorithm.recursion;

/**
 * @author gq.cai
 * @version 1.0
 * @description 汉诺塔
 * 三条可以放盘子的柱子  开始n个飞盘全部在一个柱子(大在下 小在上)
 * 需要把所有飞盘转移到另一个柱子,并且转移的过程中只能小压大不可以大压小,每次操作只能转移一个飞盘
 * 返回n层飞盘 需要转移多少次 打印转移次数
 * 1.将n-1个盘子移动到无关的柱子
 * 2.将第n个盘子移动到目标柱子
 * 3.将剩余n-1个盘子移动到目标柱子
 * @date 2022/4/25 15:06
 */
public class HanoiTower {
    
    /**
     * 开始圆盘都在左边
     * @param n
     */
    public static void hanoiTower(int n) {
        if (n <= 0) {
            return ;
        }
        leftToRight(n);
    }
    
    /**
     * 从左边移动到中间
     * @param n
     */
    public static void leftToCenter(int n) {
        if (n == 1) {
            System.out.println("Move :" + 1 + "from left to center");
            return ;
        }
        leftToRight(n - 1);
        System.out.println("Move :" + n + "from left to center");
        rightToCenter(n - 1);
    }
    
    /**
     * 从左边移动到右边
     * @param n
     */
    public static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move :" + 1 + "from left to right");
            return ;
        }
        // 将n-1层 从左移动到右边
        leftToCenter(n - 1);
        // 第n层实际操作步骤
        System.out.println("Move :" + n + "from left to right");
        // 然后将中间剩下的n-1层圆盘移动到右边  结束
        centerToRight(n - 1);
    }
    
    /**
     * 中间移动到左边
     * @param n
     */
    public static void centerToLeft(int n) {
        if (n == 1) {
            System.out.println("Move :" + 1 + "from center to left");
            return ;
        }
        centerToRight(n - 1);
        System.out.println("Move :" + n + "from center to left");
        rightToLeft(n - 1);
    }
    
    /**
     * 从中间移动到右边
     * @param n
     */
    public static void centerToRight(int n) {
        if (n == 1) {
            System.out.println("Move :" + 1 + "from center to right");
            return ;
        }
        centerToLeft(n - 1);
        System.out.println("Move :" + n + "from center to right");
        leftToRight(n - 1);
    }
    
    public static void rightToCenter(int n) {
        if (n == 1) {
            System.out.println("Move :" + 1 + "from right to center");
            return ;
        }
        rightToLeft(n - 1);
        System.out.println("Move :" + 1 + "from right to center");
        leftToCenter(n - 1);
    }
    
    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move :" + 1 + "from right to left ");
            return ;
        }
        rightToCenter(n - 1);
        System.out.println("Move :" + 1 + "from right to left ");
        centerToLeft(n - 1);
    }
    
    public static void main(String[] args) {
        hanoiTower(3);
        process(3, "left", "right", "center");
    }
    
    public static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.err.println("move :" + 1 + "from " +  from + " to " + to);
            return ;
        }
        process(n - 1, from, other, to);
        System.err.println("move :" + n + "from " +  from + "to " + to);
        process(n - 1, other, to, from);
    }
}
