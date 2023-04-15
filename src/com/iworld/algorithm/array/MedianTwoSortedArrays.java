package com.iworld.algorithm.array;

/**
 * @author gq.cai
 * @version 1.0
 * @description 4. Median of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 *
 * Constraints:
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * @date 2022/6/22 14:43
 */
public class MedianTwoSortedArrays {
    /**
     * 合并说
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        boolean isEven = n % 2 == 0;
        int mind = n >> 1;
        int[] newArr = new int[mind + 1];
        int l = 0;
        int r = 0;
        int i = 0;
        while (l < nums1.length && r < nums2.length) {
            newArr[i] = nums1[l] <= nums2[r] ? nums1[l++] : nums2[r++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (newArr[i] + newArr[i - 1]) / 2;
                }
                return newArr[i];
            }
            i ++;
        }
        while (l < nums1.length) {
            newArr[i] = nums1[l++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (newArr[i] + newArr[i - 1]) / 2;
                }
                return newArr[i];
            }
            i ++;
        }
        while (r < nums2.length) {
            newArr[i] = nums2[r++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (newArr[i] + newArr[i - 1]) / 2;
                }
                return newArr[i];
            }
            i ++;
        }
        return 0;
    }
    
    /**
     * 假合并
     * 1.计算两个数组长度  计算出合并后数组是偶数还是奇数 计算下中位数位置
     * 2.遍历到当前位置的时候 如果是偶数需要 计算当前遍历的数和前位置数和 /2 如果是奇数则直接返回当前位置数
     * 2.1当前中位数 两个数组都没有走完
     * 2.2数组1走完 在数组2中找中位数
     * 2.3数组2走完 在数组1中找中位数
     * 2.2和2.3只能存在一个条件
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysOpt(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        boolean isEven = n % 2 == 0;
        int mind = n >> 1;
        int l = 0;
        int r = 0;
        int i = 0;
        // 记录前一位置 当是偶数的时候 需要记录前一字符数字
        int pre = 0;
        // 当前位置数字
        int cur = 0;
        while (l < nums1.length && r < nums2.length) {
            cur = nums1[l] <= nums2[r] ? nums1[l++] : nums2[r++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (cur + pre) / 2;
                }
                return cur;
            }
            pre = cur;
            i ++;
        }
        while (l < nums1.length) {
            cur = nums1[l++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (cur + pre) / 2;
                }
                return cur;
            }
            pre = cur;
            i ++;
        }
        while (r < nums2.length) {
            cur = nums2[r++];
            if (i == mind) {
                if (isEven) {
                    //return BigDecimal.valueOf(newArr[i] + newArr[i - 1]).divide(BigDecimal.valueOf(2)).doubleValue();
                    return (double) (cur + pre) / 2;
                }
                return cur;
            }
            pre = cur;
            i ++;
        }
        return 0;
    }
    
    /**
     *
     * 从两个数组上分别取出两个切点cut1，cut2
     * 切点左侧l1 l2切点右侧r1 r2
     * cut1+cut2=n1+n2
     *  L1 = A1[(C1-1)/2]; R1 = A1[C1/2];
     *  L2 = A2[(C2-1)/2]; R2 = A2[C2/2];
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 < n2) {
            // 保证nums2长度小于nums1
            return findMedianSortedArrays2(nums2, nums1);
        }
        int lo = 0, hi = n2 * 2;
        while (lo <= hi) {
            // Try Cut 2
            int mid2 = (lo + hi) / 2;
            // Calculate Cut 1 accordingly
            int mid1 = n1 + n2 - mid2;
            // Get L1, R1, L2, R2 respectively
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = (mid1 == n1 * 2) ? Integer.MAX_VALUE : nums1[(mid1) / 2];
            double R2 = (mid2 == n2 * 2) ? Integer.MAX_VALUE : nums2[(mid2) / 2];
        
            if (L1 > R2) {
                // A1's lower half is too big; need to move C1 left (C2 right)
                lo = mid2 + 1;
            }
            else if (L2 > R1) {
                // A2's lower half too big; need to move C2 left.
                hi = mid2 - 1;
            } else {
                // Otherwise, that's the right cut.
                return (Math.max(L1, L2) + Math.max(R1, R2)) / 2;
            }
        }
        return -1;
    }
    
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int allSize = nums1.length + nums2.length;
        if (nums1.length > 0 && nums2.length > 0) {
            // 奇数长度
            if ((allSize & 1) == 1) {
                // 奇数中位数 下标allSize >> 1 数组下标从0开始 中位数则为第allSize >> 1 + 1小数
                return findKthNumInTwoSortArray(nums1, nums2, (allSize >> 1) + 1);
            }
            // 偶数中位数 下标allSize >> 1 - 1 和allSize >> 1 数组下标从0开始 寻找第几大数则需要加1 中位数则为第（（allSize >> 1） + （allSize >> 1 + 1））/2
            return (findKthNumInTwoSortArray(nums1, nums2, allSize >> 1) + findKthNumInTwoSortArray(nums1, nums2, (allSize >> 1) + 1)) / 2D;
        } else if (nums1.length > 0) {
            if ((allSize & 1) == 1) {
                // 奇数中位数 下标allSize >> 1 数组下标从0开始 中位数则为第allSize >> 1 + 1小数
                return nums1[allSize >> 1];
            }
            return (nums1[allSize >> 1] + nums1[(allSize >> 1) - 1]) / 2D;
        } else if (nums2.length > 0) {
            if ((allSize & 1) == 1) {
                // 奇数中位数 下标allSize >> 1 数组下标从0开始 中位数则为第allSize >> 1 + 1小数
                return nums2[allSize >> 1];
            }
            return (nums2[allSize >> 1] + nums2[(allSize >> 1) - 1]) / 2D;
        }
        return 0;
    }
    
    /**
     * 在两个排序数组中查找出第k位置的数
     * arr1={0,1,2,3,4}
     * arr2={0,1,2,3,4,5,6,7,8,9}
     * 1. 0<k<=短数组长度
     *   此时可以直接取两个数组k长度 调用getUpMedian
     * 2.短数组长度<k<=长数组长度
     *   此时在长数组中可能存在k位置长度要大于短数组的长度
     *   例k=9 两数组合并后arr1可能出现第9位置的长度为5 0,1,2,3,4位置  arr2可能出现9位置长度为6 3,4,5,6,7,8
     *   此时需要对3位置单独做一次比较如果arr2 3位置元素大于arr1 4位置元素直接返回arr2 3位置元素
     *   否则 使用getUpMedian 取arr1 0,1,2,3,4  arr2 4,5,6,7,8
     * 3.长数组长度<k<=短数组长度+长数组长度
     *   当前场景下两个数组可能是第k个位置元素个数相同 不可能部分加k能部分中位数 计算出为第k-1
     *   所以需要两个数组分别向后移动一位
     * 例：数字表示下标 数组内部已经排序
     * sarr=[0,1,2,3]   slen=4
     * larr=[0,1,2,3,4,5,6,7,8] llen=9
     * 1. k=2 小于sarr.length  larr 2~8位置不可能 sarr 2~3不可能 sarr 0~1(0~k-1)和larr 0~1(0~k-1) 找出中位数
     * 2. k=6 大于sarr.length小于larr.length larr 6~8位置和0位置不可能 0最大只能是第5大 sarr全部可用
     *  此时larr可能的长度为5大于sarr可能长度，需要单独处理larr 1(k-slen-1)位置数字 如果larr[1(k-slen-1)]>=sarr[slen-1] 此时larr 1位置为两个数字第6大数字
     *  如果larr1<sarr[slen-1] 此时在sarr 0~4和larr 2~5(k-slen~k-1)位置找出中位数
     * 3. k=12 大于larr.length 小于larr.length+sarr.length larr中0~6不可能sarr中0~1不可能
     * 此时larr可能长度和sarr可能的长度一致 此时在 sarr 2~3和larr 7~8长度找中位数 此时中位数为第11大
     * 需要单独处理sarr 2(k-llen-1)位置和larr 7(k-slen-1)位置
     * 剩余长度在两个数组中找出中位数sarr 3~3(k-llen~slen-1)和larr 8~8(k-slen~llen-1)
     *
     * @param arr1
     * @param arr2
     * @param k
     * @return
     */
    public int findKthNumInTwoSortArray(int[] arr1, int[] arr2, int k) {
        // 长度较短数组
        int[] sArr = arr1.length < arr2.length ? arr1 : arr2;
        // 长度较长数组
        int[] lArr = sArr == arr1 ? arr2 : arr1;
        int s = sArr.length;
        int l = lArr.length;
        if (k < 0 || k > s + l) {
            return 0;
        }
        // 1场景
        if (k <= s) {
            return getUpMedian(arr1, 0, k - 1, arr2, 0, k - 1);
        }
        // 3场景
        if (k > l) {
            // 检查短数组第一个可能位置和长数组最后一个位置比较 如果满足条件 当前位置就是第k位置
            if (sArr[k - l - 1] >= lArr[l - 1]) {
                return sArr[k - l - 1];
            }
            // 检查长数组第一个可能位置和短数组最后一个位置比较 如果满足条件 当前位置就是第k位置
            if (lArr[k - s - 1] >= sArr[s - 1]) {
                return lArr[k - s - 1];
            }
            // 上面两个条件没有命中 则长短数组都移到下一个位置
            return getUpMedian(sArr, k - l, s - 1, lArr, k - s, l - 1);
        }
        // 2场景
        if (lArr[k - s - 1] >= sArr[s - 1]) {
            return lArr[k - s - 1];
        }
        // 上面2场景条件没有名命中 则长数组都移到下一个位置
        return getUpMedian(sArr, 0, s - 1, lArr, k - s, k - 1);
    }
    
    /**
     * 使用此函数一定保证r1-l1=r2-l2 返回两个排序数组合并后上中位数
     * 返回两个排序数组 合并后的上中位数 保证两个数组操作的长度相同 r1-l1=r2-l2
     * 1.分别找出两个数组的上中位数 如果两个上中位数相等 直接返回
     * 2.如果数组长度为奇数长度 去处理可能存在上中位数长度较长的数组
     *   2.1使用可能存在中位数长度较长的数组的中位数x和另一数组中位数y前位置比较 如果x大于y等则直接x
     * 3.如果数组长度为偶数数组
     * @param arr1  排序数组1
     * @param l1    数组1要处理的左边界
     * @param r1    数组2要处理的右边界
     * @param arr2  排序数组2
     * @param l2    数组2要处理的左边界
     * @param r2    数组2要处理的右边界
     * @return
     */
    private int getUpMedian(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2) {
        if (l1 >= r1) {
            return Math.min(arr1[l1], arr2[l2]);
        }
        // 数组
        int mid1 = l1 + ((r1 - l1) >> 1);
        int mid2 = l2 + ((r2 - l2) >> 1);
        if (arr1[mid1] == arr2[mid2]) {
            return arr1[mid1];
        }
        // 奇数长度
        // 奇数长度最后一位为1
        if (((r1 - l1 + 1) & 1) == 1) {
            // 第一个数组中位数大于第二数组中位数
            // 1数组可能存在两个位置在合并中位数的位置 2数组可能存在三个位置在合并后数组中位数的位置
            // 此时需要再次比较下可能出现中位数长度较长的数组
            if (arr1[mid1] > arr2[mid2]) {
                // 2数组中位数和1中位数前一个数字比较 如果2数组中位数大于等于1数组中位数前位置 2数组中位数一定时合并后上中位数
                if (arr2[mid2] >= arr1[mid1 - 1]) {
                    // arr1 = [2,3,6,7,9] arr2=[1,3,4,5,8]
                    // 排序后[1,2,3,3,4,5,6,7,8,9] 上中位数为arr2[mid]=4
                    return arr2[mid2];
                }
                // arr1 = [2,5,6,7,9] arr2=[1,3,4,5,8]
                // 排序后[1,2,3,4,5,5,6,7,8,9] arr1[mid1~r1]不可能存在中位数 arr2[l2~mid2]是不可能存在中位数
                // 需要重新处理arr1前半部分arr1[l1~mid1-1]和arr2后半部分arr2[mid2+1~r2]比较处理
                return getUpMedian(arr1, l1, mid1 - 1, arr2, mid2 + 1, r2);
            } else {
                // A[mid1] < B[mid2]
                // 与上述case反过来
                if (arr1[mid1] >= arr2[mid2 - 1]) {
                    return arr1[mid1];
                }
                return getUpMedian(arr1, mid1 + 1, r1, arr2, l2, mid2 - 1);
            }
        } else {
            // 偶数长度
            if (arr1[mid1] > arr2[mid2]) {
                // arr1 = [2,4,6,7] arr2=[1,3,4,8] 排序后[1,2,3,4,4,6,7,8]
                // 此时arr1[mid1+1~r1]不可能存在中位数 arr2[l2~mid2] 此时需要arr1[l1~mid1]和arr2[mid2+1~r2]再次挑出上中位数
                return getUpMedian(arr1, l1, mid1, arr2, mid2 + 1, r2);
            } else {
                return getUpMedian(arr1, mid1 + 1, r1, arr2, l2, mid2);
            }
        }
    }
    
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }
    
    /**
     * 在两个排序数组中查找出第k位置的数
     * arr1={0,1,2,3,4}
     * arr2={0,1,2,3,4,5,6,7,8,9}
     * 1. 0<k<=短数组长度
     *   此时可以直接取两个数组k长度 调用getUpMedian
     * 2.短数组长度<k<=长数组长度
     *   此时在长数组中可能存在k位置长度要大于短数组的长度
     *   例k=9 两数组合并后arr1可能出现第9位置的长度为5 0,1,2,3,4位置  arr2可能出现9位置长度为6 3,4,5,6,7,8
     *   此时需要对3位置单独做一次比较如果arr2 3位置元素大于arr1 4位置元素直接返回arr2 3位置元素
     *   否则 使用getUpMedian 取arr1 0,1,2,3,4  arr2 4,5,6,7,8
     * 3.长数组长度<k<=短数组长度+长数组长度
     *   当前场景下两个数组可能是第k个位置元素个数相同 不可能部分加k能部分中位数 计算出为第k-1
     *   所以需要两个数组分别向后移动一位
     * @param arr1
     * @param arr2
     * @param kth
     * @return
     */
    public int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) {
            return getUpMedian2(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) {
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian2(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // 第2段
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian2(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }
    
    public int getUpMedian2(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else { // A[mid1] < B[mid2]
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            } else { // 偶数长度
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }
    
    
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        MedianTwoSortedArrays medianTwoSortedArrays = new MedianTwoSortedArrays();
        System.out.println(medianTwoSortedArrays.findMedianSortedArrays(nums1, nums2));
        System.out.println(medianTwoSortedArrays.findMedianSortedArraysOpt(nums1, nums2));
        //System.out.println(medianTwoSortedArrays.findMedianSortedArrays2(nums1, nums2));
        System.out.println(medianTwoSortedArrays.findMedianSortedArrays3(nums1, nums2));
        System.out.println(medianTwoSortedArrays.findMedianSortedArrays4(nums1, nums2));
    }
}
