package com.iworld.algorithm.array;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 可见山峰对
 * 一个数组代表一个环形山，每个位置值代表山峰的高度，山峰A和山峰B可见的条件
 * 1.如果A和B为同一个山峰 认为是不可见
 * 2.如果A和B是不同的山 两个山峰相邻则认为是可见
 * 3.如果A和B不是相同山峰 并且在山峰中不相邻 假如两个山峰最小值为min
 *  3.1如果A通过顺时针方向到B的途中没有大于min的山峰 认为A和B可以相互看见
 *  3.2如果A通过逆时针方向到B的途中没有大于min的山峰 认为A和B可以相互看见
 *  3.3两个方向有一个方向可见就认为A和B是可见
 * 给定一个无负值的数组 返回有多少个可见山峰对
 * https://github.com/algorithmzuo/trainingcamp004/blob/master/src/class07/Code03_VisibleMountains.java
 * 单调栈
 * @date 2023/3/13 14:16
 */
public class VisibleMountains {
    
    /**
     * 数组中没有重复值 时间复杂度可以O(1)
     * 避免有重复对出现 统一规定让小的山峰去找高的山峰
     * 在环形山中找出 最大值和次大值 在两个值中间的山峰 在逆时针和顺时针方向都不会超过当前两个值
     * 所以 以小找大可以找到两个，除去最大和次大两个数量为N-2 可见山峰对数量为2*(N-2)
     * 最后剩到次大值和最大值 只有次大值到最大值一对山峰对 前面山峰对+1
     * 所有山峰对数量为2*(N-2)+1=2*N-3
     * @param mountains
     * @return
     */
    public int getVisibleMountainsNumNoRepeat(int[] mountains) {
        if (mountains == null) {
            return 0;
        }
        int n = mountains.length;
        int count = 2 * n - 3;
        return Math.max(count, 0);
    }
    
    /**
     * 可能存在重复值山峰数组
     * 1.使用单调栈 栈底为最高山峰
     * 2.遍历数组找到数组中最大值
     * 3.将最大值放入栈中 从当前位置开始遍历数组 如果放入值小于等于当前栈顶元素 直接入栈
     * 4.如果当前元素大于栈顶元素 需要清算栈顶元素 此时产生栈顶元素和两边山峰可见山峰对数量k*2，内部山峰对C(2, k)
     * 5.走完一圈后 清算栈中山峰对
     *  5.1栈中元素数量大于2 山峰对数量外部为k*2，内部山峰对C(2, k)
     *  5.2如果栈中元素为2 山峰对数量外部为k*(栈底高度山峰数量>1?2:1) 内部山峰对C(2, k)
     *  5.3如果栈中元素为1 外部没有山峰对数量 内部山峰对C(2, k)
     * 结果为每次清算山峰对总和
     * @param mountains
     * @return
     */
    public int getVisibleMountainsNum(int[] mountains) {
        if (mountains == null || mountains.length < 2) {
            return 0;
        }
        Stack<MountainRecord> stack = new Stack<>();
        int n = mountains.length;
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (mountains[i] > mountains[maxIndex]) {
                maxIndex = i;
            }
        }
        int i = nextIndex(maxIndex, n);
        stack.push(new MountainRecord(mountains[maxIndex]));
        int ans = 0;
        while (i != maxIndex) {
            while (mountains[i] > stack.peek().value) {
                MountainRecord pop = stack.pop();
                // 外部山峰对
                ans += pop.count * 2;
                // 内部山峰对
                ans += permutation(2, pop.count);
            }
            if (mountains[i] == stack.peek().value) {
                stack.peek().count++;
            } else {
                stack.push(new MountainRecord(mountains[i]));
            }
            i = nextIndexs(i, n);
        }
        while (!stack.isEmpty()) {
            int size = stack.size();
            MountainRecord pop = stack.pop();
            ans += permutation(2, pop.count);
            if (size > 2) {
                ans += pop.count * 2;
            } else if (size == 2) {
                ans += pop.count * (stack.peek().count >= 2 ? 2 : 1);
            }
        }
        return ans;
    }
    
    /**
     * 排列组合计算
     * 在m个数中拿出n个
     * @param n   拿出
     * @param m   总量
     * @return
     */
    public int permutation(int n, int m) {
        if (n > m) {
            return 0;
        }
        n = Math.min(m - n, n);
        int pre = 1;
        int next = 1;
        while (n > 0) {
            pre *= m--;
            next *= n--;
        }
        return pre / next;
    }
    
    private int nextIndexs(int i, int n) {
        return i < n - 1 ? i + 1 : 0;
    }
    
    // 栈中放的记录，
    // value就是指，times是收集的个数
    public static class Record {
        public int value;
        public int times;
        
        public Record(int value) {
            this.value = value;
            this.times = 1;
        }
    }
    
    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int maxIndex = 0;
        // 先在环中找到其中一个最大值的位置，哪一个都行
        for (int i = 0; i < N; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        Stack<Record> stack = new Stack<Record>();
        // 先把(最大值,1)这个记录放入stack中
        stack.push(new Record(arr[maxIndex]));
        // 从最大值位置的下一个位置开始沿next方向遍历
        int index = nextIndex(maxIndex, N);
        // 用“小找大”的方式统计所有可见山峰对
        int res = 0;
        // 遍历阶段开始，当index再次回到maxIndex的时候，说明转了一圈，遍历阶段就结束
        while (index != maxIndex) {
            // 当前数要进入栈，判断会不会破坏第一维的数字从顶到底依次变大
            // 如果破坏了，就依次弹出栈顶记录，并计算山峰对数量
            while (stack.peek().value < arr[index]) {
                int k = stack.pop().times;
                // 弹出记录为(X,K)，如果K==1，产生2对; 如果K>1，产生2*K + C(2,K)对。
                res += getInternalSum(k) + 2 * k;
            }
            // 当前数字arr[index]要进入栈了，如果和当前栈顶数字一样就合并
            // 不一样就把记录(arr[index],1)放入栈中
            if (stack.peek().value == arr[index]) {
                stack.peek().times++;
            } else { // >
                stack.push(new Record(arr[index]));
            }
            index = nextIndex(index, N);
        }
        // 清算阶段开始了
        // 清算阶段的第1小阶段
        while (stack.size() > 2) {
            int times = stack.pop().times;
            res += getInternalSum(times) + 2 * times;
        }
        // 清算阶段的第2小阶段
        if (stack.size() == 2) {
            int times = stack.pop().times;
            res += getInternalSum(times)
                    + (stack.peek().times == 1 ? times : 2 * times);
        }
        // 清算阶段的第3小阶段
        res += getInternalSum(stack.pop().times);
        return res;
    }
    
    // 如果k==1返回0，如果k>1返回C(2,k)
    public static int getInternalSum(int k) {
        return k == 1 ? 0 : (k * (k - 1) / 2);
    }
    
    // 环形数组中当前位置为i，数组长度为size，返回i的下一个位置
    public static int nextIndex(int i, int size) {
        return i < (size - 1) ? (i + 1) : 0;
    }
    
    // 环形数组中当前位置为i，数组长度为size，返回i的上一个位置
    public static int lastIndex(int i, int size) {
        return i > 0 ? (i - 1) : (size - 1);
    }
    
    // for test, O(N^2)的解法，绝对正确
    public static int rightWay(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        HashSet<String> equalCounted = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            // 枚举从每一个位置出发，根据“小找大”原则能找到多少对儿，并且保证不重复找
            res += getVisibleNumFromIndex(arr, i, equalCounted);
        }
        return res;
    }
    
    // for test
    // 根据“小找大”的原则返回从index出发能找到多少对
    // 相等情况下，比如arr[1]==3，arr[5]==3
    // 之前如果从位置1找过位置5，那么等到从位置5出发时就不再找位置1（去重）
    // 之前找过的、所有相等情况的山峰对，都保存在了equalCounted中
    public static int getVisibleNumFromIndex(int[] arr, int index,
                                             HashSet<String> equalCounted) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) { // 不找自己
                if (arr[i] == arr[index]) {
                    String key = Math.min(index, i) + "_" + Math.max(index, i);
                    // 相等情况下，确保之前没找过这一对
                    if (equalCounted.add(key) && isVisible(arr, index, i)) {
                        res++;
                    }
                } else if (isVisible(arr, index, i)) { // 不相等的情况下直接找
                    res++;
                }
            }
        }
        return res;
    }
    
    // for test
    // 调用该函数的前提是，lowIndex和highIndex一定不是同一个位置
    // 在“小找大”的策略下，从lowIndex位置能不能看到highIndex位置
    // next方向或者last方向有一个能走通，就返回true，否则返回false
    public static boolean isVisible(int[] arr, int lowIndex, int highIndex) {
        if (arr[lowIndex] > arr[highIndex]) { // “大找小”的情况直接返回false
            return false;
        }
        int size = arr.length;
        boolean walkNext = true;
        int mid = nextIndex(lowIndex, size);
        // lowIndex通过next方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkNext = false;// next方向失败
                break;
            }
            mid = nextIndex(mid, size);
        }
        boolean walkLast = true;
        mid = lastIndex(lowIndex, size);
        // lowIndex通过last方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkLast = false; // last方向失败
                break;
            }
            mid = lastIndex(mid, size);
        }
        return walkNext || walkLast; // 有一个成功就是能相互看见
    }
    
    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }
    
    // for test
    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        VisibleMountains visibleMountains = new VisibleMountains();
        int size = 10;
        int max = 10;
        int testTimes = 3000000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(size, max);
            if (rightWay(arr) != getVisibleNum(arr)) {
                printArray(arr);
                System.out.println(rightWay(arr));
                System.out.println(getVisibleNum(arr));
                break;
            }
            if (visibleMountains.getVisibleMountainsNum(arr) != getVisibleNum(arr)) {
                printArray(arr);
                System.out.println(visibleMountains.getVisibleMountainsNum(arr));
                System.out.println(getVisibleNum(arr));
                break;
            }
        }
        System.out.println("test end!");
    }
    
    public static class MountainRecord {
        // 当前山峰高度值
        public int value;
        // 当前高度山峰的数量
        public int count;
        
        public MountainRecord(int value) {
            this.value = value;
            this.count = 1;
        }
    }
}
