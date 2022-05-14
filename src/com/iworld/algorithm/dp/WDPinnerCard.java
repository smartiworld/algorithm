package com.iworld.algorithm.dp;

/**
 * @author gq.cai
 * @version 1.0
 * @description 纸牌赢家  range model
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿
 * 但是玩家每次只能拿走最左或者最右的牌
 * 玩家A和玩家B都绝顶聪明，返回最后获胜者分数
 * @date 2022/5/8 15:20
 */
public class WDPinnerCard {
    
    public static int maxScore(int[] cards) {
        return Math.max(before(cards, 0, cards.length - 1),
                after(cards, 0, cards.length - 1));
    }
    
    /**
     * 先手选择
     * @param cards    纸牌数组
     * @param left     左边界
     * @param right    右边界
     * @return
     */
    private static int before(int[] cards, int left, int right) {
        // 还剩最后一张牌的时候
        
        if (left == right) {
            return cards[left];
        }
        int leftScore = cards[left] + after(cards, left + 1, right);
        int rightScore = cards[right] + after(cards, left, right - 1);
        return Math.max(leftScore, rightScore);
    }
    
    /**
     * 当前位置的后手
     * @param cards    纸牌数组
     * @param left     左边界
     * @param right    右边界
     * @return
     */
    private static int after(int[] cards, int left, int right) {
        // 如果还剩一个先手就没有选择下一个的机会 后手选择 剩余一个会被先手选走
        if (left == right) {
            return 0;
        }
        // 下一次先选的纸牌 先手选择了left位置 后手从left+1到right区间先手
        int leftScore = before(cards, left + 1, right);
        // 先手选择了right位置 后手从left到right-1区间先手
        int rightScore = before(cards, left, right - 1);
        return Math.min(leftScore, rightScore);
    }
    
    public static int maxScore2(int[] cards) {
        // 1.当前先手选择  先后选择左边界到右边界区间
        // 2.当前后手选择
        // 2.1后手选择 如果另一个玩家先手选了左边界 你从左边界+1到右边界的区间后手
        // 2.2后手选择 如果另一个玩家先手选了右边界 你从左边界到有边界-1的区间后手选择
        // 最大值可能在先手也可能在后手  所以选择先手和后手的最大值
        return Math.max(process(cards, 0, cards.length - 1),
                Math.max(process(cards, 1, cards.length - 1),
                        process(cards, 0, cards.length - 2)));
    }
    
    /**
     * 当前选择哪一张牌
     * @param cards    纸牌数组
     * @param left     左边界
     * @param right    右边界
     * @return
     */
    private static int process(int[] cards, int left, int right) {
        // 还剩最后一张牌的时候
        if (left == right) {
            return cards[left];
        }
        int leftAfter = 0;
        int rightAfter = 0;
        // 当前选择完后 还剩余卡牌超过一张
        // 如果是剩余一张 最后一张肯定被另一个玩家取走 结果只能是0
        if (left + 1 != right) {
            // 当前玩家选择了左left位置，
            // 另一个玩家可能选择了left+1的位置那下一次需要从left+2和right区间做选择
            // 另一个玩家可能选择了right的位置那下一次需要选择left+1和right-1区间做选择
            // 第二次选择左右取小
            leftAfter = Math.min(process(cards, left + 2, right), process(cards, left + 1, right - 1));
            // 当前玩家选择了左right位置，
            // 另一个玩家可能选择了left的位置那下一次需要选择left+1和right-1的区间做选择
            // 另一个玩家可能选择了right-1的位置那下一次需要选择left和right-2的区间做选择
            // 第二次左右选择取小
            rightAfter = Math.min(process(cards, left + 1, right - 1), process(cards, left, right - 2));
        }
        // 选择左边界当前位置分数+第二次选择分数
        int leftScore = cards[left] + leftAfter;
        // 选择右边界当前位置分数+第二次选择分数
        int rightScore = cards[right] + rightAfter;
        // 第一次选择左右取大
        return Math.max(leftScore, rightScore);
    }
    
    /**
     * 记忆化搜索
     * @param cards
     * @return
     */
    public static int maxScoreCache(int[] cards) {
        Integer[][] dp = new Integer[cards.length][cards.length];
        return Math.max(beforeCache(cards, 0, cards.length - 1, dp),
                afterCache(cards, 0, cards.length - 1, dp));
    }
    
    /**
     * 先手选择
     * @param cards    纸牌数组
     * @param left     左边界
     * @param right    右边界
     * @return
     */
    private static int beforeCache(int[] cards, int left, int right, Integer[][] dp) {
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        // 还剩最后一张牌的时候
        if (left == right) {
            dp[left][right] = cards[left];
            return dp[left][right];
        }
        int leftScore = cards[left] + afterCache(cards, left + 1, right, dp);
        int rightScore = cards[right] + afterCache(cards, left, right - 1, dp);
        dp[left][right] = Math.max(leftScore, rightScore);
        return dp[left][right];
    }
    
    /**
     * 当前位置的后手
     * @param cards    纸牌数组
     * @param left     左边界
     * @param right    右边界
     * @return
     */
    private static int afterCache(int[] cards, int left, int right, Integer[][] dp) {
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        // 如果还剩一个先手就没有选择下一个的机会 后手选择 剩余一个会被先手选走
        if (left == right) {
            dp[left][right] = 0;
            return dp[left][right];
        }
        // 下一次先选的纸牌 先手选择了left位置 后手从left+1到right区间先手
        int leftScore = beforeCache(cards, left + 1, right, dp);
        // 先手选择了right位置 后手从left到right-1区间先手
        int rightScore = beforeCache(cards, left, right - 1, dp);
        dp[left][right] = Math.min(leftScore, rightScore);
        return dp[left][right];
    }
    
    public static int maxScore2Cache(int[] cards) {
        Integer[][] dp = new Integer[cards.length][cards.length];
        // 1.当前先手选择  先后选择左边界到右边界区间
        // 2.当前后手选择
        // 2.1后手选择 如果另一个玩家先手选了左边界 你从左边界+1到右边界的区间后手
        // 2.2后手选择 如果另一个玩家先手选了右边界 你从左边界到有边界-1的区间后手选择
        // 最大值可能在先手也可能在后手  所以选择先手和后手的最大值
        return Math.max(processCache(cards, 0, cards.length - 1, dp),
                Math.max(processCache(cards, 1, cards.length - 1, dp),
                        processCache(cards, 0, cards.length - 2, dp)));
    }
    
    /**
     * 当前选择哪一张牌
     * @param cards    纸牌数组
     * @param left     左边界
     * @param right    右边界
     * @return
     */
    private static int processCache(int[] cards, int left, int right, Integer[][] dp) {
        if (dp[left][right] != null) {
            return dp[left][right];
        }
        // 还剩最后一张牌的时候
        if (left == right) {
            dp[left][right] = cards[left];
            return dp[left][right];
        }
        int leftAfter = 0;
        int rightAfter = 0;
        // 当前选择完后 还剩余卡牌超过一张
        // 如果是剩余一张 最后一张肯定被另一个玩家取走 结果只能是0
        if (left + 1 != right) {
            // 当前玩家选择了左left位置，
            // 另一个玩家可能选择了left+1的位置那下一次需要从left+2和right区间做选择
            // 另一个玩家可能选择了right的位置那下一次需要选择left+1和right-1区间做选择
            // 第二次选择左右取小
            leftAfter = Math.min(processCache(cards, left + 2, right, dp), processCache(cards, left + 1, right - 1, dp));
            // 当前玩家选择了左right位置，
            // 另一个玩家可能选择了left的位置那下一次需要选择left+1和right-1的区间做选择
            // 另一个玩家可能选择了right-1的位置那下一次需要选择left和right-2的区间做选择
            // 第二次左右选择取小
            rightAfter = Math.min(processCache(cards, left + 1, right - 1, dp), processCache(cards, left, right - 2, dp));
        }
        // 选择左边界当前位置分数+第二次选择分数
        int leftScore = cards[left] + leftAfter;
        // 选择右边界当前位置分数+第二次选择分数
        int rightScore = cards[right] + rightAfter;
        // 第一次选择左右取大
        dp[left][right] = Math.max(leftScore, rightScore);
        return dp[left][right];
    }
    
    /**
     * 记忆化搜索
     * @param cards
     * @return
     */
    public static int maxScoreDp(int[] cards) {
        int n = cards.length;
        int[][] before = new int[n][n];
        int[][] after = new int[n][n];
        for (int i = 0; i < before.length; i++) {
            before[i][i] = cards[i];;
        }
        for (int k = 1; k < before.length; k++) {
            int i = 0;
            for (int j = k; j < before.length; j++) {
                before[i][j] = Math.max(cards[i] + after[i + 1][j], cards[j] + after[i][j - 1]);
                after[i][j] = Math.min(before[i + 1][j], before[i][j - 1]);
                i++;
            }
        }
        return Math.max(before[0][cards.length - 1], after[0][cards.length - 1]);
    }
    
    public static int maxScore2Dp(int[] cards) {
        int[][] dp = new int[cards.length][cards.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = cards[i];
        }
        for (int k = 1; k < dp.length; k++) {
            int i = 0;
            for (int j = k; j < dp.length; j++) {
                int leftAfter = 0;
                int rightAfter = 0;
                // 当前选择完后 还剩余卡牌超过一张
                // 如果是剩余一张 最后一张肯定被另一个玩家取走 结果只能是0
                if (i + 2 < dp.length && j >= 2) {
                    // 当前玩家选择了左left位置，
                    // 另一个玩家可能选择了left+1的位置那下一次需要从left+2和right区间做选择
                    // 另一个玩家可能选择了right的位置那下一次需要选择left+1和right-1区间做选择
                    // 第二次选择左右取小
                    leftAfter = Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    // 当前玩家选择了左right位置，
                    // 另一个玩家可能选择了left的位置那下一次需要选择left+1和right-1的区间做选择
                    // 另一个玩家可能选择了right-1的位置那下一次需要选择left和right-2的区间做选择
                    // 第二次左右选择取小
                    rightAfter = Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                }
                // 选择左边界当前位置分数+第二次选择分数
                int leftScore = cards[i] + leftAfter;
                // 选择右边界当前位置分数+第二次选择分数
                int rightScore = cards[j] + rightAfter;
                // 第一次选择左右取大
                dp[i][j] = Math.max(leftScore, rightScore);
                i ++;
            }
        }
        // 1.当前先手选择  先后选择左边界到右边界区间
        // 2.当前后手选择
        // 2.1后手选择 如果另一个玩家先手选了左边界 你从左边界+1到右边界的区间后手
        // 2.2后手选择 如果另一个玩家先手选了右边界 你从左边界到有边界-1的区间后手选择
        // 最大值可能在先手也可能在后手  所以选择先手和后手的最大值
        // 还剩最后一张牌的时候
        return Math.max(dp[0][cards.length - 1],
                Math.max(dp[1][cards.length - 1],
                        dp[0][cards.length - 2]));
    }
    
    public static void main(String[] args) {
        int[] cards = {4, 7, 9, 5};
        System.out.println(maxScore(cards));
        System.out.println(maxScore2(cards));
        System.out.println(maxScoreCache(cards));
        System.out.println(maxScore2Cache(cards));
        System.out.println(maxScoreDp(cards));
        System.out.println(maxScore2Dp(cards));
    }
}
