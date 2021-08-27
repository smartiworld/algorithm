package com.iworld.algorithm.list;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 设计链表实现 可使用单链表或者双链表
 * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
 * 示例：
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 * https://leetcode-cn.com/problems/design-linked-list/submissions/
 * @date 2021/8/27 11:04
 */
public class MyLinkedList {
    
    /**
     * 链表中元素个数
      */
    int length = 0;
    Node head = null;
    Node tail = null;
    /** Initialize your data structure here. */
    public MyLinkedList() {
    
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        }
        int position = 0;
        Node cur = head;
        while (cur != null) {
            if (index == position) {
                break;
            }
            cur = cur.next;
            position ++;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        length ++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        length ++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > length) {
            return ;
        }
        Node node = new Node(val);
        // 链表头部
        if (index <= 0) {
            node.next = head;
            head = node;
            if (tail == null) {
                tail = node;
            }
        } else if (index == length) {
            //链表尾部
            //空链表
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        } else {
            int position = 0;
            //添加位置前置节点
            Node cur = head;
            while (++position < index) {
                cur = cur.next;
            }
            Node next = cur.next;
            cur.next = node;
            node.next = next;
        }
        length ++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= length || index < 0) {
            return ;
        }
        // 删除头节点 换头
        if (index == 0) {
            Node h = head.next;
            head.next = null;
            head = h;
            // 链表只有一个元素
            if (h == null) {
                tail = null;
            }
        } else {
            int position = 0;
            //删除位置的前置节点
            Node cur = head;
            while (++position < index) {
                cur = cur.next;
            }
            Node next = cur.next;
            // 删除的节点为尾节点 换尾
            if (next.next == null) {
                cur.next = null;
                tail = cur;
            } else {
                cur.next = next.next;
                next.next = null;
            }
            
        }
        length --;
    }
    
    static class Node {
        int val;
        Node next;
        Node() {}
        Node(int x) {
            val = x;
        }
        Node (int x, Node node) {
            val = x;
            next = node;
        }
    }
    
    /**
     * ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead",
     * "addAtHead","addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
     * [[],[2],[1],[2],[7],
     * [3],[2],[5],[5],[5],[6],[4]]
     * 5-2-3-7-2-5
     *
     * ["MyLinkedList","addAtHead","get","addAtHead","addAtHead","deleteAtIndex",
     * "addAtHead","get","get","get","addAtHead","deleteAtIndex"]
     * [[],[4],[1],[1],[5],[3],
     * [7],[3],[3],[3],[1],[4]]
     * @param args
     */
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<MyLinkedList> myLinkedListClass = MyLinkedList.class;
        MyLinkedList myLinkedList = new MyLinkedList();
        Method[] methods = myLinkedListClass.getMethods();
        Map<String, Method> methodMap = new HashMap<>();
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }
        String[] methodNames = {"addAtHead","addAtTail","addAtTail","get","get","addAtTail",
                "addAtIndex","addAtHead","addAtHead","addAtTail","addAtTail","addAtTail",
                "addAtTail","get","addAtHead","addAtHead","addAtIndex","addAtIndex",
                "addAtHead","addAtTail","deleteAtIndex","addAtHead","addAtHead","addAtIndex",
                "addAtTail","get","addAtIndex","addAtTail","addAtHead","addAtHead",
                "addAtIndex","addAtTail","addAtHead","addAtHead","get","deleteAtIndex",
                "addAtTail","addAtTail","addAtHead","addAtTail","get","deleteAtIndex",
                "addAtTail","addAtHead","addAtTail","deleteAtIndex","addAtTail","deleteAtIndex",
                "addAtIndex","deleteAtIndex","addAtTail","addAtHead","addAtIndex","addAtHead",
                "addAtHead","get","addAtHead","get","addAtHead","deleteAtIndex",
                "get","addAtHead","addAtTail","get","addAtHead","get",
                "addAtTail","get","addAtTail","addAtHead","addAtIndex","addAtIndex",
                "addAtHead","addAtHead","deleteAtIndex","get","addAtHead","addAtIndex",
                "addAtTail","get","addAtIndex","get","addAtIndex","get",
                "addAtIndex","addAtIndex","addAtHead","addAtHead","addAtTail","addAtIndex",
                "get","addAtHead","addAtTail","addAtTail","addAtHead","get",
                "addAtTail","addAtHead","addAtTail","get","addAtIndex"};
        int[][] param = {{84},{2},{39},{3},{1},{42},
                {1,80},{14},{1},{53},{98},{19},
                {12},{2},{16},{33},{4,17},{6,8},
                {37},{43},{11},{80},{31},{13,23},
                {17},{4},{10,0},{21},{73},{22},
                {24,37},{14},{97},{8},{6},{17},
                {50},{28},{76},{79},{18},{30},
                {5},{9},{83},{3},{40},{26},
                {20,90},{30},{40},{56},{15,23},{51},
                {21},{26},{83},{30},{12},{8},
                {4},{20},{45},{10},{56},{18},
                {33},{2},{70},{57},{31,24},{16,92},
                {40},{23},{26},{1},{92},{3,78},
                {42},{18},{39,9},{13},{33,17},{51},
                {18,95},{18,33},{80},{21},{7},{17,46},
                {33},{60},{26},{4},{9},{45},
                {38},{95},{78},{54},{42,86}};
        for (int i = 0; i < methodNames.length; i++) {
            if (i == 28) {
                System.out.println("aaa");
            }
            Method method = methodMap.get(methodNames[i]);
            int[] ints = param[i];
            try {
                Object invoke;
                if (ints.length == 1) {
                    
                    invoke = method.invoke(myLinkedList, ints[0]);
                } else {
                    invoke = method.invoke(myLinkedList, ints[0], ints[1]);
                }
                System.out.println(invoke);
            } catch (Exception e) {
                System.out.println("i=====" + i + ",ints=====" + ints[0] + (ints.length == 2 ? ("," + ints[1]) : "") + ",e=====" + e);
            }
            
        }
    }
}
