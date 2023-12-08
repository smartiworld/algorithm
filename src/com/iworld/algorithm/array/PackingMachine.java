package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.2.2 打包机器
 * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打 包机上，放到每个机器上的这些物品数量有多有少，
 * 由于物品数量不相同，需要工人 将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、 每次只能搬一个物品进行移动，
 * 为了省力，只在相邻的机器上移动。请计算在搬动最 小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，
 * 返回-1。 例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后:
 * 第一轮:1 0 5 => 1 1 4
 * 第二轮:1 1 4 => 2 1 3
 * 第三轮:2 1 3 => 2 2 2
 * 移动了3轮，每个机器上的物品相等，所以返回3
 * 例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品， 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1
 * @see SuperWashingMachines
 * @date 2023/12/7 21:36
 */
public class PackingMachine {
    
    /**
     * 1.计算出每个位置实际需要数量
     * 2.计算当前位置左边总数量和实际需要数量
     * 3.左边总数量-需要数量=实际要补/送出的数量
     * 4.计算当前位置=右边总数量和实际需要数量
     * 5.右边总数量-需要数量=实际要补/送出的数量
     * 6.1.左边数量大于0 说明左边数量要多 需要向右边送出 右边数量大于0 说明右边数量要多 需要向左边送出 此时左边右边需要同时需要给当前位置物品，
     *      左右移动最大值即为当前最少移动次数，即math.max(左送出数量, 右送出数量)
     * 6.2.左边数量大于0 说明左边数量要多 需要向右边送出 右边数量小于0 说明右边数量要少 需要左边补充 此时左边需要补充给右边 不需要关注当前位置是要补还是要送
     *      左边送出大于右边需要 i需要补 左边送出小于右边需要 i位置要送 此时左送出数量和右补充数量即为当前位置平衡需要最小移动次数 即math.max(左送出数量, 右需要数量)
     * 6.3.左边数量小于于0 说明左边数量要少 需要右边送出 右边数量小于0 说明右边数量要少 需要左边补充 此时左右都需要当前位置补充，每次只能移动一个
     *      需要将当前位置多的数量分别补充给左边和右边 即 左需要数量+右需要数量
     * 6.4.左边数量小于于0 说明左边数量要少 需要右边送出 右边数量大于0 说明右边数量要多 需要向左边送出 此时右边需要补充给左边 不需要关注当前位置是要补还是要送
     *      右边送出大于左边需要 i需要补 右边送出小于左边需要 i位置要送 此时右送出数量和左补充数量即为当前位置平衡需要最小移动次数 即math.max(右送出数量, 左需要数量)
     * @param packages
     * @return
     */
    public static int minMoveTimes(int[] packages) {
        int sum = 0;
        int len = packages.length;
        for (int i = 0; i < len; i++) {
            sum += packages[i];
        }
        if (sum % len != 0) {
            return -1;
        }
        int preSum = 0;
        int avgCount = sum / len;
        int minMoveTimes = -1;
        // 每个位置找出最大瓶颈值
        for (int i = 0; i < len; i++) {
            int leftNeedCount = i * avgCount;
            int rightNeedCount = (len - i - 1) * avgCount;
            int leftOverCount = preSum - leftNeedCount;
            int rightOverCount = sum - preSum - packages[i] - rightNeedCount;
            if (leftOverCount < 0 && rightOverCount < 0) {
                minMoveTimes = Math.max(Math.abs(leftOverCount) + Math.abs(rightOverCount), minMoveTimes);
            } else {
                minMoveTimes = Math.max(Math.max(Math.abs(leftOverCount), Math.abs(rightOverCount)), minMoveTimes);
            }
            preSum += packages[i];
        }
        return minMoveTimes;
    }
}
