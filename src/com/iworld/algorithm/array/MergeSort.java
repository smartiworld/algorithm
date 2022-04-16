package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 归并排序
 * @date 2021/7/8 21:46
 */
public class MergeSort {
    
    
    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }
    
    // arr[L...R]范围上，请让这个范围上的数，有序！
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        // int mid = (L + R) / 2
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }
    
    /**
     * 将左右合并 将l-m m+1-r范围合并到一个数组
     * 两个范围都已经有序
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界，要么p2越界
        // 不可能出现：共同越界
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
    
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int N = arr.length;
        while (step < N) {
            int L = 0;
            while (L < N) {
                int M = 0;
                if (N - L >= step) {
                    M = L + step - 1;
                } else {
                    M = N - 1;
                }
                if (M == N - 1) {
                    break;
                }
                int R = 0;
                if (N - 1 - M >= step) {
                    R = M + step;
                } else {
                    R = N - 1;
                }
                merge(arr, L, M, R);
                if (R == N - 1) {
                    break;
                } else {
                    L = R + 1;
                }
            }
            if (step > N / 2) {
                break;
            }
            step *= 2;
        }
        
    }
    
    // 非递归方法实现
//	public static void mergeSort2(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		int N = arr.length;
//		int mergeSize = 1;
//		while (mergeSize < N) {
//			int L = 0;
//			while (L < N) {
//				if (mergeSize >= N - L) {
//					break;
//				}
//				int M = L + mergeSize - 1;
//				int R = M + Math.min(mergeSize, N - M - 1);
//				merge(arr, L, M, R);
//				L = R + 1;
//			}
//			if (mergeSize > N / 2) {
//				break;
//			}
//			mergeSize <<= 1;
//		}
//	}
    
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    
    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    
    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    
    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100000;
        int maxValue = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            long start1 = System.currentTimeMillis();
            mergeSort1(arr1);
            long end1 = System.currentTimeMillis();
            //mergeSort2(arr2);
            long start2 = System.currentTimeMillis();
            //mergeSortNoNeedSpace(arr2);
            MergeSortNoNeedSpace.mergeSort(arr2);
            long end2 = System.currentTimeMillis();
            long c2 = end2 - start2;
            long c1 = end1 - start1;
            long c3 = c2 - c1;
            if(c3 != 0) {
                System.out.println("i====" + i + "=====" + c3);
            }
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
    
    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSortNoNeedSpace(int[] arr) {
        if(arr ==null || arr.length < 2) {
            return ;
        }
        split(arr, 0, arr.length -1);
        //merge(arr, left, mid, right);
    }
    
    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     */
    public static void split(int[] arr, int left, int right) {
        if(left == right) {
            return ;
        }
        int mid = left + ((right - left) >> 1);
        split(arr, left, mid);
        split(arr, mid + 1, right);
        merge1(arr, left, mid, right);
        //mergeNeedSpace(arr, left, mid, right);
    }
    
    /**
     * 归并排序合并 不申请空间
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge1(int[] arr, int left, int mid, int right) {
        int rightStart = mid + 1;
        while(left < right) {
            if(arr[left] > arr[rightStart]) {
                //交换位置
                change(arr, left, rightStart);
                int rightFirst = rightStart;
                int rightSecond = rightStart + 1;
                while (rightSecond <= right) {
                    if(arr[rightFirst] <= arr[rightSecond]) {
                        break;
                    }
                    change(arr, rightFirst, rightSecond);
                    rightFirst ++;
                    rightSecond ++;
                }
            }
            if(left >= mid) {
                rightStart ++;
            }
            if(rightStart > right) {
                break;
            }
            left ++;
        }
    }
    
    /**
     * 交换位置
     * @param arr
     * @param left
     * @param right
     */
    public static void change(int[] arr, int left, int right) {
        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }
}
