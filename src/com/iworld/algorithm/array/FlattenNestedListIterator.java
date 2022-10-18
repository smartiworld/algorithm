package com.iworld.algorithm.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description 341. Flatten Nested List Iterator
 * Medium
 * 3968
 * 1381
 * You are given a nested list of integers nestedList. Each element is either an integer
 * or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 *
 * Constraints:
 *
 * 1 <= nestedList.length <= 500
 * The values of the integers in the nested list is in the range [-10^6, 10^6].
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * @date 2022/10/18 17:05
 */
public class FlattenNestedListIterator {
    
    public interface NestedInteger {
        
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        //@return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    
    public static class NestedIntegerImpl implements NestedInteger {
        private Integer val;
        private boolean isInteger;
        private List<NestedInteger> nestedIntegers;
        public NestedIntegerImpl(Integer val) {
            this.val = val;
            this.isInteger = true;
        }
    
        public NestedIntegerImpl(List<NestedInteger> nestedIntegers) {
            this.nestedIntegers = nestedIntegers;
            this.isInteger = false;
        }
    
        @Override
        public boolean isInteger() {
            return isInteger;
        }
    
        @Override
        public Integer getInteger() {
            return val;
        }
    
        @Override
        public List<NestedInteger> getList() {
            return nestedIntegers;
        }
    }
    
    public static class NestedIterator implements Iterator<Integer> {
        
        private List<NestedInteger> nestedList;
        // 深度遍历 记录遍历嵌套list中index 从顶层到底层
        private Stack<Integer> stack;
        private boolean isUsed;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            stack = new Stack<>();
            stack.push(-1);
            isUsed = true;
            hasNext();
        }
        
        @Override
        public Integer next() {
            if (!isUsed) {
                int index = stack.pop();
                Integer ans = getNext(nestedList.get(index), stack);
                isUsed = true;
                stack.push(index);
                return ans;
            }
            return null;
        }
        
        private Integer getNext(NestedInteger nestedInteger, Stack<Integer> stack) {
            while (!stack.isEmpty()) {
                int index = stack.pop();
                Integer next = getNext(nestedInteger.getList().get(index), stack);
                stack.push(index);
                return next;
            }
            return nestedInteger.getInteger();
        }
        
        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            if (!isUsed) {
                return true;
            }
            isUsed = !findNext(nestedList, stack);
            return !isUsed;
        }
        
        private boolean findNext(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            // 如果栈不为空 此时还有会有list
            if (!stack.isEmpty() && findNext(nestedList.get(index).getList(), stack)) {
                stack.push(index);
                return true;
            }
            // 栈空时 查找当前pop位置集合集合
            for (int i = index + 1; i < nestedList.size(); i++) {
                // 查找当前位置 inner
                if (findInnerIndex(nestedList.get(i), stack)) {
                    stack.push(i);
                    return true;
                }
            }
            return false;
        }
        
        private boolean findInnerIndex(NestedInteger nestedInteger, Stack<Integer> stack) {
            // 当前为单个整数
            if (nestedInteger.isInteger()) {
                return true;
            } else {
                List<NestedInteger> nestedIntegers = nestedInteger.getList();
                for (int i = 0; i < nestedIntegers.size(); i++) {
                    if (findInnerIndex(nestedIntegers.get(i), stack)) {
                        stack.push(i);
                        return true;
                    }
                }
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        List<NestedInteger> nestedIntegers = new ArrayList<>();
        
        List<NestedInteger> inner1 = new ArrayList<>();
        NestedInteger in1 = new NestedIntegerImpl(1);
        inner1.add(in1);
        NestedInteger in2 = new NestedIntegerImpl(1);
        inner1.add(in2);
        NestedInteger n1 = new NestedIntegerImpl(inner1);
        nestedIntegers.add(n1);
        
        NestedInteger n2 = new NestedIntegerImpl(2);
        nestedIntegers.add(n2);
    
        List<NestedInteger> inner3 = new ArrayList<>();
        NestedInteger in31 = new NestedIntegerImpl(1);
        inner3.add(in31);
        NestedInteger in32 = new NestedIntegerImpl(1);
        inner3.add(in32);
        NestedInteger n3 = new NestedIntegerImpl(inner3);
        nestedIntegers.add(n3);
    
//        NestedInteger n4 = new NestedIntegerImpl(5);
//        nestedIntegers.add(n4);
        NestedIterator nestedIterator = new NestedIterator(nestedIntegers);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }
    
    public class NestedIterator2 implements Iterator<Integer> {
        
        private List<NestedInteger> list;
        private Stack<Integer> stack;
        private boolean used;
        
        public NestedIterator2(List<NestedInteger> nestedList) {
            list = nestedList;
            stack = new Stack<>();
            stack.push(-1);
            used = true;
            hasNext();
        }
        
        @Override
        public Integer next() {
            Integer ans = null;
            if (!used) {
                ans = get(list, stack);
                used = true;
                hasNext();
            }
            return ans;
        }
        
        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            if (!used) {
                return true;
            }
            if (findNext(list, stack)) {
                used = false;
            }
            return !used;
        }
        
        private Integer get(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            Integer ans = null;
            if (!stack.isEmpty()) {
                ans = get(nestedList.get(index).getList(), stack);
            } else {
                ans = nestedList.get(index).getInteger();
            }
            stack.push(index);
            return ans;
        }
        
        private boolean findNext(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            if (!stack.isEmpty() && findNext(nestedList.get(index).getList(), stack)) {
                stack.push(index);
                return true;
            }
            for (int i = index + 1; i < nestedList.size(); i++) {
                if (pickFirst(nestedList.get(i), i, stack)) {
                    return true;
                }
            }
            return false;
        }
        
        private boolean pickFirst(NestedInteger nested, int position, Stack<Integer> stack) {
            if (nested.isInteger()) {
                stack.add(position);
                return true;
            } else {
                List<NestedInteger> actualList = nested.getList();
                for (int i = 0; i < actualList.size(); i++) {
                    if (pickFirst(actualList.get(i), i, stack)) {
                        stack.add(position);
                        return true;
                    }
                }
            }
            return false;
        }
        
    }
}
