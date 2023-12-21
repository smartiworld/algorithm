package com.iworld.algorithm.array.string;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author gq.cai
 * @version 1.0
 * @description 3.4.1 输出树形目录
 * 给你一个字符串类型的数组arr，譬如:
 * String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
 * 把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
 * a
 *   b
 *     c
 *   d
 *     e
 * b
 *   st
 * d
 * 同一级的需要按字母顺序排列不能乱。
 * @date 2023/12/21 16:38
 */
public class FolderTree {
    
    public static void printFolderTree(String[] folders) {
        FolderPrefixTree root = new FolderPrefixTree(null);
        for (String s : folders) {
            String[] singleFolders = s.split("\\\\");
            FolderPrefixTree parent = root;
            for (String folder : singleFolders) {
                FolderPrefixTree folderPrefixTree = parent.children.get(folder);
                if (folderPrefixTree == null) {
                    folderPrefixTree = new FolderPrefixTree(folder);
                    parent.children.put(folder, folderPrefixTree);
                }
                parent = folderPrefixTree;
            }
        }
        printFolderTree(root, -1);
    }
    
    private static void printFolderTree(FolderPrefixTree folderPrefixTree, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        if (folderPrefixTree.str != null) {
            System.out.println(folderPrefixTree.str);
        }
        level++;
        for (Map.Entry<String, FolderPrefixTree> entry : folderPrefixTree.children.entrySet()) {
            printFolderTree(entry.getValue(), level);
        }
    }
    
    public static class FolderPrefixTree {
        
        private String str;
        
        private TreeMap<String, FolderPrefixTree> children;
        
        public FolderPrefixTree (String str) {
            this.str = str;
            children = new TreeMap<>();
        }
    }
    
    public static void main(String[] args) {
        String[] folders = { "b\\st", "d\\", "a\\d\\e", "a\\b\\c" };
        printFolderTree(folders);
    }
}
