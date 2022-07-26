package com.iworld.algorithm.tree.segment;

/**
 * @author gq.cai
 * @version 1.0
 * @description 线段树
 * 构造一颗满二叉树 如果数组较小不足以构造满二叉树 默认填充0 构造满二叉树
 * sums二叉树叶子节点为当前数组真实数值  非叶子记录当前节点的子节点的和
 * @date 2022/7/22 12:50
 */
public class SegmentTree {
    
    private int[] nums;
    /**
     * 二叉树节点和
      */
    private int[] sums;
    
    private boolean[] updates;
    /**
     * 更新
     */
    private int[] changes;
    /**
     * 二叉树加操作懒缓存 和sums同节点树
     */
    private int[] lazy;
    
    private int size;
    
    public SegmentTree(int[] arr) {
        size = arr.length + 1;
        nums = new int[size];
        for (int i = 1; i < size; i++) {
            nums[i] = arr[i - 1];
        }
        // 四倍的原数组肯定可以构建出一颗完全二叉树  累加和树
        sums = new int[size << 2];
        updates = new boolean[size << 2];
        // 更新树
        changes = new int[size << 2];
        lazy = new int[size << 2];
        // 构造和二叉树
        build(1, 1, size - 1);
    }
    
    /**
     *
     * @param curIndex  要去树上的实际位置
     * @param left      原数组左边界
     * @param right     原数组右边界
     */
    public void build(int curIndex, int left, int right) {
        if (left == right) {
            sums[curIndex] = nums[left];
            return;
        }
        int mid = left + ((right - left) >> 1);
        build(curIndex >> 1, left, mid);
        build(curIndex >> 1 | 1, mid + 1, right);
        curTreeSum(curIndex);
    }
    
    /**
     * 当前位置数值为左右子树和
     * @param curIndex
     */
    private void curTreeSum(int curIndex) {
        sums[curIndex] = sums[curIndex >> 1] + sums[curIndex >> 1 | 1];
    }
    
    /**
     * 在给定范围内增加一个数
     * @param left      任务的左边界
     * @param right     任务的右边界
     * @param l         实际左边界
     * @param r         实际右边界
     * @param value     当前范围要操作的数  增加
     * @param index     需要使用的位置
     */
    public void add(int left, int right, int l, int r, int value, int index) {
        // 此时已经包含当前范围
        if (left <= l && r <= right) {
            // 在当前位置 包含的范围上所有数加value 和之前sum和累加
            sums[index] += value * (r - l + 1);
            // 累加之前累加值
            lazy[index] += value;
            return ;
        }
        // 没有覆盖住 把任务分发到子树 范围小的地方
        int mid = l + ((r - l) >> 1);
        pushValueDown(index, mid - l + 1, r - mid);
        // 左孩子是否要接收任务
        if (left <= mid) {
            add(left, right, l, mid, value, index << 1);
        }
        // 右孩子是否要接收任务
        if (right > mid) {
            add(left, right, mid + 1, r, value, index << 1 | 1);
        }
        // 更新当前位置sums树和
        curTreeSum(index);
    }
    
    /**
     * 给定的范围上 修改所有值为value
     * @param left    任务左边界
     * @param right   任务右边界
     * @param l       实际左边界
     * @param r       实际右边界
     * @param value   要操作的值  修改
     * @param index   需要使用的位置
     */
    public void update(int left, int right, int l, int r, int value, int index) {
        // 此时已经包含当前范围
        if (left <= l && r <= right) {
            // 标记当前位置发生过更新操作
            updates[index] = true;
            // 记录当前位置要更新的值
            changes[index] = value;
            // 重新计算当前位置sum 结果即为当前范围个数乘更新值 之前值不再需要
            sums[index] = (r - l + 1) * value;
            // 当前位置之前缓存增加的数全部失效
            lazy[index] = 0;
            return ;
        }
        int mid = l + (r - l) >> 1;
        // 将当前位置缓存 下发
        pushValueDown(index, mid - l + 1, r - mid);
        if (left <= mid) {
            update(left, right, l, mid, value, index << 1);
        }
        if (right > mid) {
            update(left, right, mid + 1, r, value, index << 1 | 1);
        }
        curTreeSum(index);
    }
    
    /**
     * 查询给定范围的和
     * @param left     任务左边界
     * @param right    任务右边界
     * @param l        实际左边界
     * @param r        实际右边界
     * @param index    需要使用位置
     * @return
     */
    public long query(int left, int right, int l, int r, int index) {
        if (left <= l && r <= right) {
            return sums[index];
        }
        int mid = l + ((r - l) >> 1);
        long ans = 0;
        if (left <= mid) {
            ans += query(left, right, l, mid, index << 1);
        }
        if (right > mid) {
            ans += query(left, right, mid + 1, r, index << 1 | 1);
        }
        return ans;
    }
    
    /**
     * 当前位置将操作值下发到孩子节点
     * 懒增加 懒更新从父节点下发
     * 如果当前节点既有更新又有增加  先操作更新操作  再操作增加
     * @param parent   当前节点索引下标
     * @param ln       左孩子节点数
     * @param rn       右孩子节点数
     */
    private void pushValueDown(int parent, int ln, int rn) {
        if (updates[parent]) {
            // 如果父节点发生了更新 下面子节点也必须会发生更新
            updates[parent << 1] = true;
            updates[parent << 1 | 1] = true;
            // 左右孩子节点发生更新的值即为父更新值
            changes[parent << 1] = changes[parent];
            changes[parent << 1 | 1] = changes[parent];
            // 左右孩子之前缓存懒增加全部无效
            lazy[parent << 1] = 0;
            lazy[parent << 1 | 1] = 0;
            // 当前父更新已分发完毕 清空标识 设置为false
            updates[parent] = false;
        }
        if (lazy[parent] != 0) {
            // 表示当前位置有需要下发的值
            // parent位置懒缓存下发到左孩子懒缓存
            lazy[parent << 1] += lazy[parent];
            // 左孩子 sums增加当前懒的值
            sums[parent << 1] += lazy[parent] * ln;
            // parent位置懒缓存下发到右孩子懒缓存
            lazy[parent << 1 | 1] += lazy[parent];
            // 右孩子 sums增加当前懒的值
            sums[parent << 1 | 1] += lazy[parent] * rn;
            // 当前位置懒数据清零
            lazy[parent] = 0;
        }
    }
    
}
