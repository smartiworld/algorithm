package com.iworld.algorithm.array;

import com.iworld.algorithm.util.ArrayUtil;

/**
 * @description
 * @author gq.cai
 * @version 1.0  快速排序
 * @date 2021/7/16 19:53
 */
public class QuickSort {
    
    /**
     * 将数组分两块小于num的在数组左边 大于num的在数组右边 左右不排序
     * 实现：一个数字记录小于mun的右边界， 遍历当前数组
     * 如果当前位置的数小于等于num 将当前位置数和小于边界下一个位置数交换位置
     * 最后返回最小位置右边界
     * 边界点 i遍历整个数组
     * @param arr
     */
    public static int partition(int[] arr, int num) {
        // 将数组中数字按照num分为两个部分less左为小于num 右边为大于num
        // less记录的是小于num数字的最后的位置
        // 如果当前数字小于num将less后一位置和当前位置数字交换
        int less = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] <= num) {
                swap(arr, ++less, i);
            }
        }
        return less;
    }
    
    /**
     * 数组交换
     * arr[l] = arr[l] ^ arr[r];
     * arr[r] = arr[l] ^ arr[r];
     * arr[l] = arr[l] ^ arr[r];
     * 适用非同一地址数据位置交换
     * @param arr
     * @param l
     * @param r
     */
    public static void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
    
    /**
     * 荷兰国旗 将数组按照某一个数分三块 小于当前数的在左边等于当前数的在中间 大于当前数的在右边
     * 实现：使用两个位置指针 分别记录小于当前数的右边界 和大于当前数的左边界
     * 小于当前数的和小指针边界下一个位置交换 遍历指针移到下一个位置
     * 大于当前数和大指针前一个位置数交换 遍历指针位置不变
     * 等于当前数 不做处理 遍历指针移到下一个位置
     * 边界 遍历指针小于大指针
     * 小于|等于|大于
     * @param arr
     * @param l     要划分数组的左边界
     * @param r     要划分数组的右边界
     * @param num   基准对比数
     * @return
     */
    private static int[] netherlands(int[] arr, int l, int r, int num) {
        if(l > r) {
            return new int[] {-1, -1};
        }
        if(l == r) {
            return new int[] {l, r};
        }
        int less = l -1;
        int big = r;
        int i = l;
        while(i < big) {
            if(arr[i] < num) {
                swap(arr, ++less, i++);
            } else if(arr[i] > num) {
                // 当前位置大于基准时候 交换当前位置到big区域，此时从big区域交换过来的数字还没有比较
                // 需要重新比较划分区域 所以i不变
                swap(arr, i, --big);
            } else {
                i ++;
            }
        }
        return new int[] {less, big};
    }
    
    /**
     * 以数组最后一个元素为分界比对点
     * 荷兰国旗 将数组按照某一个数分三块 小于当前数的在左边等于当前数的在中间 大于当前数的在右边
     * 实现：使用两个位置指针 分别记录小于当前数的右边界 和大于当前数的左边界
     * 小于当前数的和小指针边界下一个位置交换 遍历指针移到下一个位置
     * 大于当前数和大指针前一个位置数交换 遍历指针位置不变
     * 等于当前数 不做处理 遍历指针移到下一个位置
     * 边界 遍历指针小于大指针
     * 以数组最后位置作为对比点 此时big指针直接指向当前数组最后位置 此位置等全部处理完后再交换位置
     * 此时 r就为数组最后元素位置
     * 返回等于区域左边元素位置 右边界为等于最右元素位置  等于区域开始位置和结束位置
     * @param arr
     * @return
     */
    public static int[] netherlands(int[] arr, int l, int r) {
        if(l > r) {
            return new int[] {-1, -1};
        }
        if(l == r) {
            return new int[] {l, r};
        }
        int less = l -1;
        int big = r;
        int i = l;
        while(i < big) {
            if(arr[i] < arr[r]) {
                swap(arr, ++less, i++);
            } else if(arr[i] > arr[r]) {
                swap(arr, i, --big);
            } else {
                i ++;
            }
        }
        // 此前 less记录的是小于最后位置 big记录的是大于开始位置
        // 将最后位置换到big区域开始 此时big区域指向的是等于最后位置
        swap(arr, big, r);
        // 返回位置 开始为等于开始位置 结束为等于end位置
        return new int[] {++less, big};
    }
    
    /**
     * 快速排序
     * 实现： 先把数组分成三组 小于某个数的在左边 等于在中间  大于放右边
     * 每次中间是有序 再分别排序左边和右边 递归调用。
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return ;
        }
        split(arr, 0, arr.length);
    }
    
    /**
     * 方法结束代表将数组分为三块  中间一块是已经有序数组
     * 1.随机选择数组中一个位置作为基准点
     * 2.less指向-1 big执想数组最后一个元素的下一个位置 即数组长度
     * 3.小于基准值放入左侧 less走一个位置 less指向小于区域最后一位置
     * 4.大于基准放右侧 big前走一位置，i不变 big指向大于区域的开始位置
     * 5.执行完成后less指向小于区域的结束 big指向大于区域开始 less-big已经有序
     * 6.重复l-less big-r  排序
     * @param arr
     * @param l    数组元素的左边界
     * @param r    r为数组元素的边界下一个位置
     */
    private static void split(int[] arr, int l, int r) {
        // 左边界已经到达数组右边界
        if(l >= r - 1) {
            return;
        }
        int num = arr[l + (int) (Math.random() * (r - l))];
        int less = l - 1;
        int big = r;
        int i = l;
        while(i < big) {
            if(arr[i] < num) {
                swap(arr, ++less, i++);
            } else if(arr[i] > num) {
                swap(arr, --big, i);
            } else {
                i ++;
            }
        }
        split(arr, l, less + 1);
        split(arr, big, r);
    }
    // 递归
    public static void quickSort2(int[] arr) {
        if(arr == null || arr.length < 2) {
            return ;
        }
        split2(arr, 0, arr.length - 1);
    }
    
    private static void split2(int[] arr, int l, int r) {
        if(l >= r) {
            return;
        }
        // 交换随机位置到数组尾部
        swap(arr, (l + (int) Math.random() * (r - l + 1)), r);
        int[] netherlands = netherlands(arr, l, r);
        split2(arr, l, netherlands[0] - 1);
        split2(arr, netherlands[1] + 1, r);
    }
    
    public static void quickSort3(int[] arr) {
        if(arr == null || arr.length < 2) {
            return ;
        }
        split3(arr, 0, arr.length - 1);
    }
    
    /**
     * l和r分别为数组左右边界 big需要指向有边界下一个位置
     *
     * @param arr
     * @param l      数组左边界
     * @param r      数组右边界
     */
    private static void split3(int[] arr, int l, int r) {
        // 左边界已经到达数组右边界
        if(l >= r - 1) {
            return;
        }
        int num = arr[l + (int) (Math.random() * (r - l + 1))];
        int less = l - 1;
        int big = r + 1;
        int i = l;
        while(i < big) {
            if(arr[i] < num) {
                swap(arr, ++less, i++);
            } else if(arr[i] > num) {
                swap(arr, --big, i);
            } else {
                i ++;
            }
        }
        // 执行完毕后less执向小于区域的最后元素位置 big指向大于区域开始位置
        split3(arr, l, less);
        split3(arr, big, r);
    }
    
//    public static void main(String[] args) {
//        int[] ints = {11,18,6,10,16,11,10,2,7,11};
////        int partition = partition(ints, 10);
////        System.out.println(partition);
////        for (int anInt : ints) {
////            System.out.print(anInt + ",");
////        }
////        System.out.println("-----");
////        int[] netherlands = netherlands(ints, 0, ints.length - 1);
////        for (int anInt : netherlands) {
////            System.out.print(anInt + ",");
////        }
//        quickSort3(ints);
//        for (int i = 0; i < ints.length; i++) {
//            System.out.print(ints[i] + ",");
//        }
//    }
    
    public static void main(String[] args) {
        int[] ints = ArrayUtil.generateIntArray(100000, 10000);
        int[] copyInts = new int[ints.length];
        System.arraycopy(ints, 0, copyInts, 0, copyInts.length);
        quickSort3(ints);
        quickSort2(copyInts);
        System.out.println("-----");
        for (int i = 0; i < ints.length; i++) {
            if(ints[i] != copyInts[i]) {
                System.out.println("排序出错===" + i);
            }
        }
    }
    
    
}
