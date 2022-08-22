package com.iworld.algorithm.calc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gq.cai
 * @version 1.0
 * @description 118. Pascal's Triangle   Easy
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= numRows <= 30
 * https://leetcode.com/problems/pascals-triangle/
 * @date 2022/8/22 16:40
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> result = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    result.add(1);
                } else {
                    List<Integer> tmp = ans.get(i - 1);
                    int sum = tmp.get(j - 1) + tmp.get(j);
                    result.add(sum);
                }
            }
            ans.add(result);
        }
        return ans;
    }
    
    public static void main(String[] args) {
        PascalsTriangle pascalsTriangle = new PascalsTriangle();
        int numRows = 5;
        System.out.println(pascalsTriangle.generate(numRows));
    }
}
