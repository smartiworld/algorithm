package com.iworld.algorithm.tree;

/**
 * @author gq.cai
 * @version 1.0
 * @description
 * @date 2022/3/12 12:25
 */
public class CommonBinaryTree<E> {
    
    public E value;
    public CommonBinaryTree<E> left;
    public CommonBinaryTree<E> right;
    
    public CommonBinaryTree(){
    
    }
    
    public CommonBinaryTree(E value) {
        this.value = value;
    }
    
    public CommonBinaryTree(E value, CommonBinaryTree<E> left, CommonBinaryTree<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    public E getValue() {
        return value;
    }
    
    public void setValue(E value) {
        this.value = value;
    }
    
    public CommonBinaryTree<E> getLeft() {
        return left;
    }
    
    public void setLeft(CommonBinaryTree<E> left) {
        this.left = left;
    }
    
    public CommonBinaryTree<E> getRight() {
        return right;
    }
    
    public void setRight(CommonBinaryTree<E> right) {
        this.right = right;
    }
    
    @Override
    public String toString() {
        return "CommonBinaryTree{" +
                "value=" + value +
                '}';
    }
}
