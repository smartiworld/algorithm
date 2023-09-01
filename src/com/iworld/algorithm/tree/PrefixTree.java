package com.iworld.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 前缀树 统计字符串开头数量
 * @date 2021/7/27 21:11
 */
public class PrefixTree {
    
    private Node rootNode;
    private NodeOfMap rootNodeOfMap;
    public PrefixTree() {
        rootNode = new Node();
        rootNodeOfMap = new NodeOfMap();
    }
    /**
     * 大小写字母 已知有限个数的字符
     */
    class Node {
        int pass = 0;
        int end = 0;
        Node[] next = new Node[26];
    }
    
    class NodeOfMap {
        int pass = 0;
        int end = 0;
        Map<Integer, NodeOfMap> nextNodes = new HashMap<>();
        
    }
    
    public void insert(String str) {
        if (str == null) {
            return;
        }
        char[] chars = str.toCharArray();
        Node node = rootNode;
        rootNode.pass ++;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.next[index] == null) {
                node.next[index] = new Node();
            }
            node = node.next[index];
            node.pass ++;
        }
        node.end ++;
    }
    
    public void insertOfMap(String str) {
        if (str == null) {
            return;
        }
        char[] chars = str.toCharArray();
        NodeOfMap node = rootNodeOfMap;
        node.pass ++;
        for (int i = 0; i < chars.length; i++) {
            int key = chars[i];
            if (node.nextNodes.get(key) == null) {
                node.nextNodes.put(key, new NodeOfMap());
            }
            node = node.nextNodes.get(key);
            node.pass ++;
        }
        node.end ++;
    }
    
    /**
     * 查找字符串出现次数
     * @param str
     * @return
     */
    public int search(String str) {
        Node endNode = findEndNode(str);
        if (endNode == null) {
            return 0;
        }
        return endNode.end;
    }
    
    /**
     * 查找字符串出现次数
     * @param str
     * @return
     */
    public int searchOfMap(String str) {
        NodeOfMap endNode = findEndNodeOfMap(str);
        if (endNode == null) {
            return 0;
        }
        return endNode.end;
    }
    
    private Node findEndNode(String str) {
        if (str == null) {
            return null;
        }
        Node node = rootNode;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            // 树上没有此字符
            node = node.next[index];
            if (node == null) {
                return null;
            }
        }
        return node;
    }
    
    private NodeOfMap findEndNodeOfMap(String str) {
        if (str == null) {
            return null;
        }
        NodeOfMap node = rootNodeOfMap;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int key = chars[i];
            node = node.nextNodes.get(key);
            // 树上没有此字符
            if (node == null) {
                return null;
            }
        }
        return node;
    }
    
    /**
     * 查找以当前字符开头的字符串个数
     * @param str
     * @return
     */
    public int searchPrefix(String str) {
        Node endNode = findEndNode(str);
        if (endNode == null) {
            return 0;
        }
        return endNode.pass;
    }
    
    /**
     * 查找以当前字符开头的字符串个数
     * @param str
     * @return
     */
    public int searchPrefixOfMap(String str) {
        NodeOfMap endNode = findEndNodeOfMap(str);
        if (endNode == null) {
            return 0;
        }
        return endNode.pass;
    }
    
    /**
     * 删除字符串
     * @param str
     */
    public void delete(String str) {
        if (str == null || search(str) == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        Node node = rootNode;
        node.pass --;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if ( --node.next[index].pass == 0) {
                node.next[index] = null;
                return ;
            }
            node = node.next[index];
        }
        node.end --;
    }
    
    /**
     * 删除字符串
     * @param str
     */
    public void deleteOfMap(String str) {
        if (str == null || searchOfMap(str) == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        NodeOfMap node = rootNodeOfMap;
        node.pass --;
        for (int i = 0; i < chars.length; i++) {
            int key = chars[i];
            if ( --node.nextNodes.get(key).pass == 0) {
                node.nextNodes.put(key, null);
                return ;
            }
            node = node.nextNodes.get(key);
        }
        node.end --;
    }
    
    public List<String> getAll() {
        List<String> result = new ArrayList<>();
        Node[] next = rootNode.next;
        return result;
    }
    boolean e;
    char c;
    static boolean ee;
    static char cc;
    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();
//        prefixTree.insert("abc");
//        prefixTree.insert("abc");
//        prefixTree.insert("abd");
//        System.out.println(prefixTree.search("abc"));
//        System.out.println(prefixTree.search("d"));
//        System.out.println(prefixTree.searchPrefix("a"));
//        System.out.println(prefixTree.searchPrefix("d"));
        System.out.println(ee);
        System.out.println(cc);
        System.out.println(prefixTree.e);
        System.out.println(prefixTree.c);
    }
}
