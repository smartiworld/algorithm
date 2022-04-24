package com.iworld.algorithm.array;

import com.iworld.algorithm.util.ArrayUtil;

/**
 * @author gq.cai
 * @version 1.0
 * @description 桶排序 非比较排序 计数排序 基数排序
 * @date 2021/7/29 18:04
 */
public class BucketSort {
    
    
    /**
     * 根据有限数据集 创建数组 数组下标代表 数组中元素 数组的值表示当前位置的值出现了多少次
     * 然后弹出数组值个数组位置的数
     * @param arr
     */
    public static void countSort(int[] arr) {
        int[] age = new int[201];
        for (int i = 0; i < arr.length; i++) {
            age[arr[i]] ++;
        }
        int x = 0;
        for (int i = 0; i < age.length; i++) {
            for (int j = 0; j < age[i]; j++) {
                arr[x++] = i;
            }
        }
    }
    
    
    public static void main(String[] args) {
        int[] ints = ArrayUtil.generateIntArray(10, 1000);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println("---------");
        //countSort(ints);
        baseCountSort(ints, 0, ints.length - 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
    }
    
    /**
     * 基数排序  count数组 计数 下标为排序个位 十位数字 count大小为排序数组数字进制
     *  count数组数值 为当前位数小于等于当前下标的数字个数
     *  help数组大小等于原数组
     * 获取排序数组最高位数， 遍历数组获取数组每位数数值，然后在count对应下标上加1 然后处理count数组 将count数组当前位置加前一位置数
     * 倒序遍历原数组 获取当前位置数在count数组上数值 将当前数放在help【count【i-】】位置上 然后count数组对应位置数减1。
     * 将help数组中数重新写回原数组
     * 时间复杂度 O(N*loga(max)) 近似O(N)
     * 空间复杂度 O(N)
     * 1.需要获取数组中最高位数数字 需要根据位数进行排序 每次遍历排序一个位数的数组
     * 2.统计当前位数数字在数组中数字出现的频率 记录到count数组
     * 3.然后将count当前位置和前位置数相加 表示小于等于当前数的个数 以便向help数组回放，count值即为当前数需要放的位置，
     *   放后位置-1，表示数组中还剩多少小于当前位数的数
     * 4.将help刷回原数组
     * 5.知道所有位数跑完
     * @param arr   排序数组
     * @param left  左边界
     * @param right 右边界
     */
    public static void baseCountSort(int[] arr, int left, int right) {
        // 数组中数字最高位数
        int maxDigit = getMaxDigit(arr, left, right);
        // 每次刷新数据回原数组
        int[] help = new int[arr.length];
        // 根据位数遍历
        // 每次遍历对当前位数进行排序 位数遍历好后 所有都已排序
        for (int i = 1; i <= maxDigit; i++) {
            // count下标为进制数 元素内个数记录当前数出现了多少次
            int[] count = new int[10];
            // 遍历数组开始结束位置
            for (int j = left; j <= right; j++) {
                // 获取当前数组数字的当前位数的数字
                int digitNum = getDigitNum(arr[j], i);
                // 当前位数数字为count数组下标
                // 当前count数组记录整个数组 当前位数 数字出现的个数
                count[digitNum] ++;
            }
            // 将count数组 重新赋值 当前位置数修改为当前位置数加当前位置前一位置数
            // count中记录的为当前下标的值出现的次数 需要根据下标放回help数组 所以后面值需要加前面值出现次数
            // count中值 表示小于等于当前下标的个数
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }
            // 从尾遍历原数组
            // 因为原算法中时从左向右遍历  相同位数的数字 右边的后出 放到help数组后面 前面的放help前
            // 所需此时需要从右向左 可以保证相同位数数字可以放help后面
            for (int j = arr.length - 1; j >= 0; j--) {
                // 获取数组当前位置数字当前位数 数字
                int digitNum = getDigitNum(arr[j], i);
                // 将当前位置数字放到help count[i-i] 的位置上 然后count 位置减1
                help[count[digitNum] = (count[digitNum] - 1)] = arr[j];
            }
            // 将help数组重新刷回原数组
            // 此时位数已经有序 刷回原数组 接着下次对更高一位进行排序
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }
        }
    }
    
    /**
     * 倒序 （未实现）
     * @param arr   排序数组
     * @param left  左边界
     * @param right 右边界
     */
    public static void baseCountSortDesc(int[] arr, int left, int right) {
        int maxDigit = getMaxDigit(arr, left, right);
        // 每次刷新数据回原数组
        int[] help = new int[arr.length];
        for (int i = 1; i <= maxDigit; i++) {
            int[] count = new int[10];
            for (int j = left; j <= right; j++) {
                int digitNum = getDigitNum(arr[j], i);
                count[digitNum] ++;
            }
            for (int j = count.length - 2; j >= 0; j--) {
                count[j] = count[j] + count[j + 1];
            }
            for (int j = 0; j < help.length; j++) {
                int digitNum = getDigitNum(arr[j], i);
                help[count[digitNum] = (count[digitNum] - 1)] = arr[j];
            }
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }
        }
    }
    
    /**
     * 当前数字获取当前位置数字
     *
     * @param num   当前数
     * @param digit 第几位
     * @return
     */
    public static int getDigitNum(int num, int digit) {
       return  (num / (int)Math.pow(10, (digit - 1))) % 10;
    }
    
    /**
     * 获取数组上最大值的位数
     * @param arr
     * @return
     */
    public static int getMaxDigit(int[] arr, int left, int right) {
        int digit = 0;
        int max = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        while (max != 0) {
            digit ++;
            max = max / 10;
        }
        return digit;
    }
    
}
