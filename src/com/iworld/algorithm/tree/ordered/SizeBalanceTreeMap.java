package com.iworld.algorithm.tree.ordered;

/**
 * @author gq.cai
 * @version 1.0
 * @description SB平衡树
 * @date 2022/7/14 11:52
 */
public class SizeBalanceTreeMap<K extends Comparable<K>, V> {

    private SizeBalanceTreeNode<K, V> root;
    
    static class SizeBalanceTreeNode<K extends Comparable<K>, V> {
        K key;
        V value;
        SizeBalanceTreeNode<K, V> left;
        SizeBalanceTreeNode<K, V> right;
        // 不同的key的数量
        int size;
        
        SizeBalanceTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }
    
    public void put(K key, V value) {
        if (key == null) {
            return ;
        }
        if (root == null) {
            root = new SizeBalanceTreeNode<>(key, value);
            return;
        }
        SizeBalanceTreeNode<K, V> recentNode = findRecentNode(key);
        // 更新
        if (recentNode != null && recentNode.key.compareTo(key) == 0) {
            recentNode.value = value;
        } else {
            // 新增
            root = add(root, key, value);
        }
    }
    
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        SizeBalanceTreeNode<K, V> recentNode = findRecentNode(key);
        return recentNode != null && recentNode.key.compareTo(key) == 0;
    }
    
    /**
     * remove key value
     * @param key
     */
    public void remove(K key) {
        if (key == null) {
            return ;
        }
        if (containsKey(key)) {
            root = delete(root, key);
        }
    }
    
    /**
     * 以node节点开始 增加key  value
     * 增加完成后会对node节点检查 是否需要做平衡调整
     * 返回调整后当前树的头
     * @param node  开始位置
     * @param key   要增加key
     * @param value 要增加value
     * @return
     */
    private SizeBalanceTreeNode<K, V> add(SizeBalanceTreeNode<K, V> node, K key, V value) {
        if (node == null) {
             return new SizeBalanceTreeNode<>(key, value);
        } else {
            node.size ++;
            if (key.compareTo(node.key) < 0) {
                node.left = add(node.left, key, value);
            } else if (key.compareTo(node.key) > 0) {
                node.right = add(node.right, key, value);
            }
            return balance(node);
        }
        
    }
    
    
    /**
     * 对当前节点进行平衡性调整
     * @param cur
     * @return
     */
    private SizeBalanceTreeNode<K, V> balance(SizeBalanceTreeNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        // LL 左子树的左孩子子节点数量大于右子树数量
        if (cur.left != null && cur.left.left != null && cur.right != null && cur.left.left.size > cur.right.size) {
            // 返回当前节点的左子树 为新的树头部
            cur = rightRotate(cur);
            // 新头节点的右节点 子树数量发生变化
            cur.right = balance(cur.right);
            // 新头部字节点数量发生变化
            cur = balance(cur);
        } else if (cur.left != null && cur.left.right != null && cur.right != null && cur.left.right.size > cur.right.size) {
            // LR 左子树的右孩子子节点数量大于右子树数量
            // 先将当前节点的左子树进行左旋
            cur.left = leftRotate(cur.left);
            // 将当前节点进行右旋
            cur = rightRotate(cur);
            // 新头部左节点子树数量发生变化
            cur.left = balance(cur.left);
            // 新头部右节点子树数量发生变化
            cur.right = balance(cur.right);
            // 新头部子树节点数量发生变化
            cur = balance(cur);
        } else if (cur.right != null && cur.right.right != null && cur.left != null && cur.right.right.size > cur.left.size) {
            // RR 右子树的右孩子子节点数量大于左子树数量
            // 返回当前节点的右子树 为新的树头部
            cur = leftRotate(cur);
            // 新头节点的左节点 子树数量发生变化
            cur.left = balance(cur.left);
            // 新头部字节点数量发生变化
            cur = balance(cur);
        } else if (cur.right != null && cur.right.left != null && cur.left != null && cur.right.left.size > cur.left.size) {
            // RL 右子树的左孩子子节点数量大于左子树数量
            cur.right = rightRotate(cur.right);
            // 将当前节点进行左旋
            cur = leftRotate(cur);
            // 新头部左节点子树数量发生变化
            cur.left = balance(cur.left);
            // 新头部右节点子树数量发生变化
            cur.right = balance(cur.right);
            // 新头部字节点数量发生变化
            cur = balance(cur);
        }
        return cur;
    }
    
    private SizeBalanceTreeNode<K, V> delete(SizeBalanceTreeNode<K, V> cur, K key) {
        cur.size --;
        if (key.compareTo(cur.key) < 0) {
            cur.left = delete(cur.left, key);
        } else if (key.compareTo(cur.key) > 0) {
            cur.right = delete(cur.right, key);
        } else {
            //匹配到当前节点node
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left == null) {
                cur = cur.right;
            } else if (cur.right == null) {
                cur = cur.left;
            } else {
                // 遍历找到接替当前节点的节点（后继节点）
                // 接替节点的父节点
                SizeBalanceTreeNode<K, V> pre = null;
                // 要接替当前删除节点的节点
                SizeBalanceTreeNode<K, V> des = cur.right;
                des.size --;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                    des.size --;
                }
                if (pre != null) {
                    pre.left = des.right;
                    des.right = cur.right;
                }
                des.left = cur.left;
                des.size = des.left.size + (des.right == null ? 0 : des.right.size) + 1;
                cur = des;
            }
        }
        return cur;
    }
    /**
     * 对当前节点进行右旋
     * 返回新头部
     * @param cur
     * @return
     */
    private SizeBalanceTreeNode<K, V> rightRotate(SizeBalanceTreeNode<K, V> cur) {
        // 记录当前节点的左子树 将当前子树设置为头节点
        SizeBalanceTreeNode<K, V> cLeft = cur.left;
        // 将左树的右树设置为当前节点的左树
        cur.left = cLeft.right;
        // 左树的右树设置为当前节点
        cLeft.right = cur;
        // 左树大小为当前节点大小  即当前子树总节点数不变
        cLeft.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return cLeft;
    }
    
    /**
     * 对当前节点进行左旋
     * 返回新头部
     * @param cur
     * @return
     */
    private SizeBalanceTreeNode<K, V> leftRotate(SizeBalanceTreeNode<K, V> cur) {
        // 记录当前节点的右子树 将当前子树设置为头节点
        SizeBalanceTreeNode<K, V> cRight = cur.right;
        // 将右树的左树设置为当前节点的右树
        cur.right = cRight.left;
        // 右树的左树设置为当前节点
        cRight.left = cur;
        // 右树大小为当前节点大小  即当前子树总节点数不变
        cRight.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return cRight;
    }
    
    /**
     * 查找离当前key最近节点
     * @param key
     * @return
     */
    private SizeBalanceTreeNode<K, V> findRecentNode(K key) {
        SizeBalanceTreeNode<K, V> cur = root;
        SizeBalanceTreeNode<K, V> pre = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) == 0) {
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return pre;
    }

}
