package com.iworld.algorithm.array.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author gq.cai
 * @version 1.0
 * @description 407.Trapping Rain Water II      Hard
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 * Example 1:
 *
 *
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 * Example 2:
 *
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 * Constraints:
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 * https://leetcode.com/problems/trapping-rain-water-ii/
 * @date 2022/7/18 16:46
 */
public class TrappingRainWaterII {
    
    /**
     * 1.将矩阵最外四边放入小根堆  并标记已经放入堆中
     * 2.小根堆弹出堆顶元素 比较 最大元素
     * 3.将弹出点 上下 左右点和最大值 计算盛水量 max-cur >0累加 否则不进行处理
     * 4.重复上述步骤 直到堆空
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        int c = heightMap[0].length;
        int r = heightMap.length;
        if (r < 3 || c < 3) {
            return 0;
        }
        // 小根堆 存放矩阵数组
        PriorityQueue<WaterNode> priorityQueue = new PriorityQueue<>(new WaterNodeComparator());
        // 当前行列位置值是否进入小根堆
        boolean[][] isEnter = new boolean[r][c];
        // 首先将矩阵四边放入小根堆
        // 将上下两条边放入小根堆
        for (int i = 0; i < c; i++) {
            priorityQueue.add(new WaterNode(heightMap[0][i], 0, i));
            isEnter[0][i] = true;
            priorityQueue.add(new WaterNode(heightMap[r - 1][i], r - 1, i));
            isEnter[r - 1][i] = true;
        }
        // 将左右两条边放入小根堆
        for (int j = 1; j < r - 1; j++) {
            priorityQueue.add(new WaterNode(heightMap[j][0], j, 0));
            isEnter[j][0] = true;
            priorityQueue.add(new WaterNode(heightMap[j][c - 1], j, c - 1));
            isEnter[j][c -1] = true;
        }
        int max = 0;
        int sum = 0;
        while (!priorityQueue.isEmpty()) {
            WaterNode poll = priorityQueue.poll();
            max = Math.max(poll.value, max);
            int row = poll.row;
            int col = poll.col;
            // 当前点的上方点
            if (row - 1 > 0 && !isEnter[row - 1][col]) {
                isEnter[row - 1][col] = true;
                priorityQueue.add(new WaterNode(heightMap[row - 1][col], row - 1, col));
                if (max - heightMap[row - 1][col] > 0) {
                    sum += max - heightMap[row - 1][col];
                }
            }
            // 当前点的下方点
            if (row + 1 < r - 1 && !isEnter[row + 1][col]) {
                isEnter[row + 1][col] = true;
                priorityQueue.add(new WaterNode(heightMap[row + 1][col], row + 1, col));
                if (max - heightMap[row + 1][col] > 0) {
                    sum += max - heightMap[row + 1][col];
                }
            }
            // 当前点的左方点
            if (col - 1 > 0 && !isEnter[row][col - 1]) {
                isEnter[row][col - 1] = true;
                priorityQueue.add(new WaterNode(heightMap[row][col - 1], row, col - 1));
                if (max - heightMap[row][col - 1] > 0) {
                    sum += max - heightMap[row][col - 1];
                }
            }
            // 当前点的右方点
            if (col + 1 < c - 1 && !isEnter[row][col + 1]) {
                isEnter[row][col + 1] = true;
                priorityQueue.add(new WaterNode(heightMap[row][col + 1], row, col + 1));
                if (max - heightMap[row][col + 1] > 0) {
                    sum += max - heightMap[row][col + 1];
                }
            }
        }
        return sum;
    }
    
    static class WaterNode {
        int value;
        int row;
        int col;
        
        WaterNode(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }
    
    static class WaterNodeComparator implements Comparator<WaterNode> {
        
        @Override
        public int compare(WaterNode w1, WaterNode w2) {
            return w1.value - w2.value;
        }
    }
    
    public int trapRainWater2(int[][] heightMap) {
        if(heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int row = heightMap.length;
        int col = heightMap[0].length;
        int[][] volume = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                volume[i][j] = heightMap[i][j];
            }
        }
        boolean update = true;
        boolean init = true;
        while (update) {
            update = false;
            for (int i = 1; i < row - 1; i++) {
                for (int j = 1; j < col - 1; j++) {
                    //Math.min(volume[i - 1][j], volume[i][j - 1]) 影响当前位置左右位置 小的为其下限
                    int val = Math.max(heightMap[i][j], Math.min(volume[i - 1][j], volume[i][j - 1]));
                    if (init || val < volume[i][j]) {
                        volume[i][j] = val;
                        update = true;
                    }
                }
            }
            init = false;
            for (int i = row - 2; i >= 1; i--) {
                for (int j = col - 2; j >= 1; j--) {
                    int val = Math.max(heightMap[i][j], Math.min(volume[i + 1][j], volume[i][j + 1]));
                    if (val < volume[i][j]) {
                        volume[i][j] = val;
                        update = true;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (volume[i][j] - heightMap[i][j] > 0) {
                    sum += volume[i][j] - heightMap[i][j];
                }
            }
        }
        return sum;
    }
    
    public int trapRainWater3(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }
        PriorityQueue<WaterNode> queue = new PriorityQueue<>(1, new Comparator<WaterNode>(){
            
            @Override
            public int compare(WaterNode a, WaterNode b) {
                return a.value - b.value;
            }
        });
        
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        
        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new WaterNode(heights[i][0], i, 0));
            queue.offer(new WaterNode(heights[i][n - 1], i, n - 1));
        }
        
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new WaterNode(heights[0][i], 0, i));
            queue.offer(new WaterNode(heights[m - 1][i],m - 1, i));
        }
        
        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            WaterNode poll = queue.poll();
            for (int[] dir : dirs) {
                int row = poll.row + dir[0];
                int col = poll.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    
                    res += Math.max(0, poll.value - heights[row][col]);
                    queue.offer(new WaterNode(Math.max(heights[row][col], poll.value), row, col));
                }
            }
        }
        return res;
    }
    
    /**
     * 2 4 时间短
     * @param heightMap
     * @return
     */
    public int trapRainWater4(int[][] heightMap) {
        int c = heightMap[0].length;
        int r = heightMap.length;
        if (r < 3 || c < 3) {
            return 0;
        }
        int sum = 0;
        int[][] help = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                help[i][j] = heightMap[i][j];
            }
        }
        boolean update = true;
        boolean init = true;
        while (update) {
            update = false;
            for (int i = 1 ; i < r - 1; i++) {
                for (int j = 1; j < c - 1; j++) {
                    int minH = Math.min(help[i - 1][j], help[i][j - 1]);
                    int v = Math.max(minH, heightMap[i][j]);
                    if (init || v < help[i][j]) {
                        help[i][j] = v;
                        update = true;
                    }
                }
            }
            init = false;
            for (int i = r - 2 ; i >= 1; i--) {
                for (int j = c - 2; j >= 1; j--) {
                    int minH = Math.min(help[i + 1][j], help[i][j + 1]);
                    int v = Math.max(minH, heightMap[i][j]);
                    if (v < help[i][j]) {
                        help[i][j] = v;
                        update = true;
                    }
                }
            }
        }
        for (int i = 1; i < r - 1; i++) {
            for (int j = 1; j < c - 1; j++) {
                if (help[i][j] - heightMap[i][j] > 0) {
                    sum += help[i][j] - heightMap[i][j];
                }
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        //int[][] heightMap = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        int[][] heightMap = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
        TrappingRainWaterII trappingRainWaterII = new TrappingRainWaterII();
        System.out.println(trappingRainWaterII.trapRainWater(heightMap));
        System.out.println(trappingRainWaterII.trapRainWater2(heightMap));
        System.out.println(trappingRainWaterII.trapRainWater4(heightMap));
    }
}
