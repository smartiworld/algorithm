package com.iworld.algorithm.array;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author gq.cai
 * @version 1.0
 * @description 堆 1)完全二叉树结构 2)任何一个父节点都不小于其孩子节点(大根堆)或者不大于其孩子节点(小根堆)
 * 性质：通过父节点 左孩子2*N+1 右孩子2*N+2
 *      通过子节点 父节点(N-1)/2
 * @date 2021/7/21 11:05
 */
public class HeapOperate {

    private int[] element;
    
    private int size = 10;
    
    private int heapSize = 0;
    
    public HeapOperate() {
        element = new int[size];
    }
    
    public HeapOperate(int capacity) {
        this.size = capacity;
        element = new int[capacity];
    }
    
    /**
     * 压入一个元素 先放入数组最后一个位置 然后和父节点对比交换 直到不大于父节点（大根堆）或者不小于父节点（小根堆）
     * @param num
     */
    public void push(int num) {
        if(size == heapSize) {
            throw new RuntimeException("堆已满");
        }
        element[heapSize] = num;
        upward(element, heapSize++);
    }
    
    /**
     * 元素在堆上 上浮
     * @param arr
     * @param index
     */
    private void upward(int[] arr, int index) {
        // 父节点位置
        int parentIndex = (index - 1) / 2;
        while (arr[parentIndex] < arr[index]) {
            swap(arr, parentIndex, index);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }
    
    /**
     * 弹出数组第一个位置 堆的头部，然后交换数组第一个和最后元素的位置 交换后的头下沉
     * 对比左右孩子，交换大于自己的最大的孩子（大根堆）或者交换小于自己的最小孩子（小根堆）
     * @return
     */
    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("堆空");
        }
        int result = element[0];
        swap(element, 0, --heapSize);
        under(element, 0, heapSize);
        return result;
    }
    
    /**
     * 元素在堆上下沉 找出左右孩子和当前元素最大元素 然后交换位置
     * @param arr
     * @param index
     * @param heapSize
     */
    private void under(int[] arr, int index, int heapSize) {
        // 当前index节点左孩子位置
        int left = (2 * index) + 1;
        while (left < heapSize) {
            // 最大孩子元素的位置 如果有右孩子 并且大于左孩子 当前最大的字节点位置为 右孩子位置 否则为左孩子位置
            // left + 1 < heapSize 判断有无右孩子节点
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //最大孩子节点和父节点对比 记录最大元素位置
            largest = arr[index] >= arr[largest] ? index : largest;
            // 当前位置元素为最大元素时 结束
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = (2 * index) + 1;
        }
    }
    
    public boolean isEmpty() {
        return heapSize == 0;
    }
    
    
    private void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
    
    public void heapSort(int[] arr, int l, int r) {
        //数组从第一个位置构建堆 时间复杂度O(N*logN)
        //放入数组最后位置 向上对比
//        for (int i = l; i <= r; i++) {
//            push(arr[i]);
//        }
        // 构造堆 倒序时间复杂度收敛于O(N)
        // 数组最后一个元素倒序 判断当前节点是否大于子节点
        for (int i = r; i >= l ; i--) {
            under(arr, i, r - l + 1);
        }
        
        int heapSize = r;
        while (heapSize > 0) {
            swap(arr, 0, heapSize);
            under(arr, 0, heapSize--);
        }
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "鎿嶄綔鎴愬姛瀹屾垚銆";
        String a = new String(s.getBytes(Charset.forName("GBK")), "UTF-8");
        System.out.println("a====" + a);
        HeapOperate ho = new HeapOperate(10);
        ho.push(10);
        ho.push(15);
        ho.push(1);
        ho.push(18);
        ho.push(19);
        ho.push(25);
        ho.push(3);
        ho.push(9);
        ho.push(3);
        ho.push(22);
        while (!ho.isEmpty()) {
            System.out.print(ho.pop() + ",");
        }
        System.out.println("-----");
        int[] arr = {20, 12, 2, 30, 25, 10, 9, 8, 12, 15};
        ho.heapSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
