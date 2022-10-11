package com.iworld.algorithm.calc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 295. Find Median from Data Stream
 * Hard
 * 8380
 * 152
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10^-5 of the actual answer will be accepted.
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Constraints:
 *
 * -10^5 <= num <= 10^5
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 *
 * Follow up:
 *
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * https://leetcode.com/problems/find-median-from-data-stream/
 * @date 2022/10/10 19:48
 */
public class FindMedianFromDataStream {
    /**
     * 两个堆  一个小根堆一个大根堆
     * 大根堆保存较小部分  小根堆保存较大部分
     * 通过大根堆筛选出当前添加的数据的最大值  如果此时两个堆的大小相差2 此时会把大根堆头部放入小根堆 也即将目前最大值放入小根堆
     * 此时小根堆 堆底即为所有添加的最大值 大根堆堆底即为此时所有添加的最小值
     * 新加入的值 如果是大于大根堆堆顶 此时放入小根堆 保证小根堆的最小值大于大根堆堆顶最大值
     * 每次添加后调整大小根堆大小 保证两个堆大小绝对值不超过2
     * 此时就可以保证大根堆和小根堆的堆顶为中间位置元素
     */
    public static class MedianFinder {
        // 保存较小部分
        PriorityQueue<Integer> max;
        // 保存比较大的部分
        PriorityQueue<Integer> min;
        public MedianFinder() {
            max = new PriorityQueue<>(new MaxComparator());
            min = new PriorityQueue<>(new MinComparator());
        }
        
        public void addNum(int num) {
            if (max.isEmpty()) {
                max.add(num);
            } else {
                if (max.peek() >= num) {
                    max.add(num);
                } else {
                    min.add(num);
                }
            }
            balance();
        }
    
        /**
         * 保证两个堆的数量差不超过2
         */
        private void balance() {
            if (max.size() == min.size() + 2) {
                min.add(max.poll());
            }
            if (max.size() == min.size() - 2) {
                max.add(min.poll());
            }
        }
        
        public double findMedian() {
            if (max.size() == min.size()) {
                return ((double) (max.peek() + min.peek())) / 2;
            } else {
                if (max.size() > min.size()) {
                    return max.peek();
                } else {
                    return min.peek();
                }
            }
        }
        
        public static class MaxComparator implements Comparator<Integer> {
            
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        }
        
        public static class MinComparator implements Comparator<Integer> {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        }
    }
    
    public static class MedianFinder2 {
        
        MinHeap minHeap;
        MaxHeap maxHeap;
        
        public MedianFinder2() {
            minHeap = new MinHeap();
            maxHeap = new MaxHeap();
        }
        
        public void addNum(int num) {
            // Min heap stores the larger nums
            // Max heap stores the smaller nums
            
            if (num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
            
            // Rebalance them
            if ((minHeap.size - maxHeap.size) > 1) {
                maxHeap.add(minHeap.remove());
            } else if ((maxHeap.size - minHeap.size) > 1) {
                minHeap.add(maxHeap.remove());
            }
        }
        
        public double findMedian() {
            if (maxHeap.size == minHeap.size) {
                return ((double) maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                return maxHeap.size > minHeap.size ? maxHeap.peek() : minHeap.peek();
            }
        }
        
        class MinHeap extends Heap {
            
            @Override
            public void heapifyUp() {
                int index = size - 1;
                
                while (hasParent(index) && parent(index) > items[index]) {
                    swap(parentIndex(index), index);
                    index = parentIndex(index);
                }
            }
            
            @Override
            public void heapifyDown() {
                int index = 0;
                
                while (hasLeftChild(index)) {
                    int smallerIndex = leftChildIndex(index);
                    
                    if (hasRightChild(index) && rightChild(index) < items[smallerIndex]) {
                        smallerIndex = rightChildIndex(index);
                    }
                    
                    if (items[smallerIndex] < items[index]) {
                        swap(index, smallerIndex);
                        index = smallerIndex;
                    } else {
                        break;
                    }
                }
            }
        }
        
        class MaxHeap extends Heap {
            
            @Override
            public void heapifyUp() {
                int index = size - 1;
                
                while (hasParent(index) && parent(index) < items[index]) {
                    swap(parentIndex(index), index);
                    index = parentIndex(index);
                }
            }
            
            @Override
            public void heapifyDown() {
                int index = 0;
                
                while (hasLeftChild(index)) {
                    int biggerIndex = leftChildIndex(index);
                    
                    if (hasRightChild(index) && rightChild(index) > items[biggerIndex]) {
                        biggerIndex = rightChildIndex(index);
                    }
                    
                    if (items[biggerIndex] > items[index]) {
                        swap(index, biggerIndex);
                        index = biggerIndex;
                    } else {
                        break;
                    }
                }
            }
        }
        
        
        
        class Heap {
            int[] items = new int[30];
            int size = 0;
            
            public void add(int num) {
                ensureExtraCapacity();
                
                items[size] = num;
                size++;
                
                heapifyUp();
            }
            
            public int remove() {
                int item = items[0];
                items[0] = items[size - 1];
                size--;
                
                heapifyDown();
                return item;
            }
            
            public boolean hasLeftChild(int index) { return leftChildIndex(index) < size; }
            public boolean hasRightChild(int index) { return rightChildIndex(index) < size; }
            public boolean hasParent(int index) { return parentIndex(index) >= 0; }
            
            public int leftChild(int index) { return items[leftChildIndex(index)]; }
            public int rightChild(int index) { return items[rightChildIndex(index)]; }
            public int parent(int index) { return items[parentIndex(index)]; }
            
            public int leftChildIndex(int index) { return (2 * index) + 1; }
            public int rightChildIndex(int index) { return (2 * index) + 2; }
            public int parentIndex(int index) { return (index - 1)/2; }
            
            public void swap(int i1, int i2) {
                int temp = items[i1];
                items[i1] = items[i2];
                items[i2] = temp;
            }
            
            public void ensureExtraCapacity() {
                if (size == items.length) {
                    items = Arrays.copyOf(items, items.length * 2);
                }
            }
            
            public void heapifyUp() {};
            public void heapifyDown() {};
            
            public int peek() {
                return items[0];
            }
        }
    }
    
}
