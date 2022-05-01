package com.iworld.algorithm.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2021/7/29 17:31
 */
public class MyBinaryTreeOperate<E> {
    
    private InnerBinaryTree<E> rootTree;
    
    private InnerBinaryTree<E> curTree;
    
    private InnerBinaryTree<E> nextTree;
    
    private Comparator<E> comparator;
    
    public MyBinaryTreeOperate() {
    }
    
    public MyBinaryTreeOperate(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    
    public static class InnerBinaryTree<E> {
        E value;
        InnerBinaryTree<E> left;
        InnerBinaryTree<E> right;
    }
    
    /**
     * 树的递归序
     * @param tree
     */
    public void itr(InnerBinaryTree tree) {
        if (tree == null) {
            return ;
        }
        itr(tree.left);
        itr(tree.right);
    }
    
    /**
     * 遍历二叉树
     * @param treePrintModel 遍历模式
     * @return
     */
    public List<E> convertTreeToList(TreePrintModel treePrintModel) {
        List<E> result = new ArrayList<>();
        itrAdd(rootTree, result, treePrintModel);
        return result;
    }
    
    /**
     * 二叉树遍历  遍历结果放入集合中
     * @param tree
     * @param result
     * @param treePrintModel 遍历模式  先序 中序还是后序
     */
    private void itrAdd(InnerBinaryTree<E> tree, List<E> result, TreePrintModel treePrintModel) {
        if (tree == null) {
            return ;
        }
        if (TreePrintModel.TREE_OF_BEFORE == treePrintModel) {
            result.add(tree.value);
        }
        itrAdd(tree.left, result, treePrintModel);
        if (TreePrintModel.TREE_OF_IN == treePrintModel) {
            result.add(tree.value);
        }
        itrAdd(tree.right, result, treePrintModel);
        if (TreePrintModel.TREE_OF_AFTER== treePrintModel) {
            result.add(tree.value);
        }
    }
    
    public void put(E e) {
        if (rootTree == null) {
            rootTree = new InnerBinaryTree();
            curTree = rootTree;
        }
        rootTree.value = e;
        return ;
    }
    
    /**
     * 先序遍历 使用栈的方式
     * @return
     */
    public List<E> preOrderIter() {
        List<E> result = null;
        if (rootTree != null) {
            result = new ArrayList<>();
            Stack<InnerBinaryTree<E>> stack = new Stack();
            stack.push(rootTree);
            while (!stack.isEmpty()) {
                InnerBinaryTree<E> pop = stack.pop();
                result.add(pop.value);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
        return result;
    }
    
    /**
     * 中序遍历
     * @param head
     * @return
     */
    public List<E> inIter(InnerBinaryTree<E> head) {
        List<E> result = new ArrayList<>();
        Stack<InnerBinaryTree<E>> stack = new Stack();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            }else {
                InnerBinaryTree<E> pop = stack.pop();
                result.add(pop.value);
                head = pop.right;
            }
        }
        return result;
    }
    
    /**
     * 后序遍历
     * 结果放入栈中
     * @param head
     * @return
     */
    public List<E> postOrderIterWithStack(InnerBinaryTree<E> head) {
        List<E> result = null;
        if (rootTree != null) {
            result = new ArrayList<>();
            Stack<E> help = new Stack<>();
            Stack<InnerBinaryTree<E>> stack = new Stack();
            stack.push(rootTree);
            while (!stack.isEmpty()) {
                InnerBinaryTree<E> pop = stack.pop();
                help.push(pop.value);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
            }
            while (!help.isEmpty()) {
                result.add(help.pop());
            }
        }
        
        return result;
    }
    
    /**
     * 后序遍历
     * 结果放入栈中
     * @param head
     * @return
     */
    public List<E> postOrderIter(InnerBinaryTree<E> head) {
        List<E> result = new ArrayList<>();
        InnerBinaryTree cur = head;
        if (head != null) {
            Stack<InnerBinaryTree<E>> stack = new Stack();
            stack.push(head);
            while (!stack.isEmpty()) {
                cur = stack.peek();
                if (cur.left != null && head != cur.left && head != cur.right) {
                    stack.push(cur.left);
                } else if (cur.right != null && head != cur.right) {
                    stack.push(cur.right);
                } else {
                    InnerBinaryTree<E> pop = stack.pop();
                    result.add(pop.value);
                    head = cur;
                }
            }
        }
        return result;
    }
    
    /**
     * 二叉树按层遍历
     * @param head
     * @return
     */
    public List<E> widthOrderIter(InnerBinaryTree<E> head) {
        List<E> result = null;
        if (head != null) {
            result = new ArrayList<>();
            Queue<InnerBinaryTree<E>> queue = new LinkedList();
            queue.offer(head);
            while (!queue.isEmpty()) {
                InnerBinaryTree<E> poll = queue.poll();
                result.add(poll.value);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return result;
    }
    
    /**
     * 二叉树s形输出 之字形打印
     * @param head
     * @return
     */
    public List<E> sModelOrderIterUseStack(InnerBinaryTree<E> head) {
        List<E> result = null;
        if (head != null) {
            result = new ArrayList<>();
            // 右到左遍历  孩子节点右到左添加到stack2
            Stack<InnerBinaryTree<E>> stack = new Stack<>();
            stack.push(head);
            // 左到右遍历  孩子节点左到右添加到stack
            Stack<InnerBinaryTree<E>> stack2 = new Stack<>();
            while (!stack.isEmpty()) {
                while (!stack.isEmpty()) {
                    InnerBinaryTree<E> pop = stack.pop();
                    result.add(pop.value);
                    if (pop.left != null) {
                        stack2.push(pop.left);
                    }
                    if (pop.right != null) {
                        stack2.push(pop.right);
                    }
                }
                while (!stack2.isEmpty()) {
                    InnerBinaryTree<E> pop = stack2.pop();
                    result.add(pop.value);
                    if (pop.right != null) {
                        stack.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack.push(pop.left);
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * 获取数的最大宽度  未实现
     * @param head
     * @return
     */
    public List<E> sModelOrderIter(InnerBinaryTree<E> head) {
        List<E> result = null;
        if (head != null) {
            result = new ArrayList<>();
            Queue<InnerBinaryTree<E>> queue = new LinkedList<>();
            queue.offer(head);
            // 当前层数节点
            InnerBinaryTree<E> curTree = head;
            // 下层最后节点 从左到右 则为最右节点 从右到左则为最左节点
            InnerBinaryTree<E> nextEndTree = null;
            // 是否转变顺序
            boolean reverse = true;
            while (!queue.isEmpty()) {
                InnerBinaryTree<E> poll = queue.poll();
                result.add(poll.value);
                if (!reverse) {
                    if (poll.left != null) {
                        queue.offer(poll.left);
                        nextEndTree = poll.left;
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                        nextEndTree = poll.right;
                    }
                } else {
                    if (poll.right != null) {
                        queue.offer(poll.right);
                        nextEndTree = poll.right;
                    }
                    if (poll.left != null) {
                        queue.offer(poll.left);
                        nextEndTree = poll.left;
                    }
                }
                // 当前节点走到当前节点最后一个节点位置，将下层最后节点赋值到当前节点，重置当前层宽度，计算当前层最大宽度
                if (curTree == poll) {
                    curTree = nextEndTree;
                    reverse = !reverse;
                }
            }
        }
        return result;
    }
    
    public List<E> convertListToLevel(InnerBinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        if (tree != null) {
            Queue<InnerBinaryTree<E>> queue = new LinkedList<>();
            //queue.offer();
        }
        return result;
    }
    
    public static void main(String[] args) {
        InnerBinaryTree<Integer> head = new InnerBinaryTree<>();
        head.value = 1;
        InnerBinaryTree<Integer> tree21 = new InnerBinaryTree<>();
        tree21.value = 2;
        head.left = tree21;
        InnerBinaryTree<Integer> tree22 = new InnerBinaryTree<>();
        tree22.value = 3;
        head.right = tree22;
        InnerBinaryTree<Integer> tree31 = new InnerBinaryTree<>();
        tree31.value = 4;
        tree21.left = tree31;
        InnerBinaryTree<Integer> tree32 = new InnerBinaryTree<>();
        tree32.value = 5;
        tree21.right = tree32;
        InnerBinaryTree<Integer> tree33 = new InnerBinaryTree<>();
        tree33.value = 6;
        tree22.left = tree33;
        InnerBinaryTree<Integer> tree34 = new InnerBinaryTree<>();
        tree34.value = 7;
        tree22.right = tree34;
        InnerBinaryTree<Integer> tree41 = new InnerBinaryTree<>();
        tree41.value = 8;
        tree31.left = tree41;
        InnerBinaryTree<Integer> tree42 = new InnerBinaryTree<>();
        tree42.value = 9;
        tree31.right = tree42;
        InnerBinaryTree<Integer> tree43 = new InnerBinaryTree<>();
        tree43.value = 10;
        tree32.left = tree43;
        InnerBinaryTree<Integer> tree44 = new InnerBinaryTree<>();
        tree44.value = 11;
        tree32.right = tree44;
        InnerBinaryTree<Integer> tree45 = new InnerBinaryTree<>();
        tree45.value = 12;
        tree33.left = tree45;
        InnerBinaryTree<Integer> tree46 = new InnerBinaryTree<>();
        tree46.value = 13;
        tree33.right = tree46;
        InnerBinaryTree<Integer> tree47 = new InnerBinaryTree<>();
        tree47.value = 14;
        tree34.left = tree47;
        InnerBinaryTree<Integer> tree48 = new InnerBinaryTree<>();
        tree48.value = 15;
        tree34.right = tree48;
        MyBinaryTreeOperate<Integer> myBinaryTreeOperate = new MyBinaryTreeOperate<>();
        myBinaryTreeOperate.rootTree = head;
        List<Integer> list = myBinaryTreeOperate.convertTreeToList(TreePrintModel.TREE_OF_BEFORE);
        //List<Integer> list = myBinaryTreeOperate.convertTreeToList(TreePrintModel.TREE_OF_IN);
        //List<Integer> list = myBinaryTreeOperate.convertTreeToList(TreePrintModel.TREE_OF_AFTER);
        //List<Integer> list = myBinaryTreeOperate.preOrderIter();
        //List<Integer> list = myBinaryTreeOperate.inIter(head);
        //List<Integer> list = myBinaryTreeOperate.postOrderIter(head);
        //List<Integer> list = myBinaryTreeOperate.widthOrderIter(head);
        System.out.println(list);
//        List<Integer> list2 = myBinaryTreeOperate.sModelOrderIter(head);
//        System.out.println(list2);
        List<Integer> list3 = myBinaryTreeOperate.sModelOrderIterUseStack(head);
        System.out.println(list3);
    }
    /**
     * 树遍历模式
     */
    public static enum TreePrintModel {
       /**
        * 先序
        */
       TREE_OF_BEFORE,
       /**
        * 中序
        */
       TREE_OF_IN,
       /**
        * 后续
        */
       TREE_OF_AFTER;
       
    }
}
