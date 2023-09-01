package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 链表环路检测
 * @date 2021/7/7 14:55
 */
public class LoopListNode {
    
    /**
     * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。 如果没有环则返回null
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 示例 1：
     *
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 链表环检测
     * 思想：同时遍历链表一个连续遍历（走一步），另一个隔一个遍历（走两步）
     * https://leetcode-cn.com/problems/linked-list-cycle-lcci/
     * @param listNode
     * @return
     */
    public static ListNode checkLoop(ListNode listNode) {
        if(listNode == null || listNode.next == null || listNode.next.next == null) {
            return null;
        }
        ListNode slow = listNode.next;
        ListNode fast = listNode.next.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = listNode;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null){
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next.next;
            }else{
                return null;
            }
            if(slow == fast){
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
    
    /**
     * 两条链表检测有无相交，有相交返回相交节点
     * 1.两条链表都没有环 相交，返回交叉点
     * 2.一条有环一条没有环 不可能相交
     * 3.两条链表都有环 1.都有环不相交 2.相交 有相同的入环点 3.相交
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode detectCross(ListNode head1, ListNode head2) {
        ListNode loop1 = checkLoop(head1);
        ListNode loop2 = checkLoop(head2);
        //两个链表无环
        if(loop1 == null && loop2 == null) {
            return noLoopCross(head1, head2);
        }
        //两个链表都有环
        if(loop1 != null && loop2 != null) {
            return bothLoopCross(head1, loop1, head2, loop2);
        }
        //一个链表有环 另一个无环 一定不会存在相交节点
        return null;
    }
    
    /**
     * 检测两个无环链表有无相交点 有返回相交点  无返回null
     * 1.两个链表分别走到最后一个节点 如果最后一个节点相同 则有相交点 不同则无相交点返回空
     * 2.两个链表尾节点相同  取两个链表的长度差值 长链表遍历 直到剩余节点数和短链表节点数相同的时候
     *   两个链表同时遍历 第一个相同节点为第一个相交节点。
     * @param head1 链表1
     * @param head2 链表2
     * @return
     */
    public static ListNode noLoopCross(ListNode head1, ListNode head2) {
        if(head1 == null || head2 == null) {
            return null;
        }
        int nodeCount = 0;
        ListNode cur1 = head1;
        while (cur1.next != null) {
            nodeCount++;
            cur1 = cur1.next;
        }
        ListNode cur2 = head2;
        while (cur2.next != null) {
            nodeCount--;
            cur2 = cur2.next;
        }
        //如果链表1和链表2的尾节点不是同一个节点 表是当前两个链表没有相交
        if (cur1 != cur2) {
            return null;
        }
        // 无环链表相交 相交相交长租一定相同 不相交长度差值为nodeCount 长的链表先走nodeCount
        // 接下来就会同时走到相交点
        //cur1 设置长链表
        cur1 = nodeCount > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        nodeCount = Math.abs(nodeCount);
        //长链表遍历 节点剩余数量等于短链表长度
        while (nodeCount != 0) {
            cur1 = cur1.next;
            nodeCount--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
    
    /**
     * 检测两个有环链表有无相交 如果相交 返回相交点 无相交返回null
     * 1. 两个链表有相同的入环点 入环节点前找出相交节点 同无环两个链表找相交节点
     *    计算出两个链表差值 长链表先遍历到差值节点位置初 然后两个链表同时遍历找出第一个相同节点
     * 2. 两个有环链表入环节点不同，从任一链表入环点遍历，如果另一链表入环点在此环上 则两个有环链表有相遇节点
     *    否则 两个有环链表无相交
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static ListNode bothLoopCross(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        if(head1 == null || head2 == null || loop1 == null || loop2 == null) {
            return null;
        }
        //head1 head2两个有环链表入环节点为一个 两个链表一定相交
        if(loop1 == loop2) {
            int nodeCount = 0;
            ListNode cur1 = head1;
            while(cur1.next != loop1) {
                nodeCount ++;
                cur1 = cur1.next;
            }
            ListNode cur2 = head2;
            while (cur2.next != loop2) {
                nodeCount --;
                cur2 = cur2.next;
            }
            //长链表赋值给cur1
            cur1 = nodeCount > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            nodeCount = Math.abs(nodeCount);
            while (nodeCount != 0) {
                cur1 = cur1.next;
                nodeCount --;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 尝试 两个链表是否是同一个环
            ListNode loopNext = loop1.next;
            while (loopNext != loop1) {
                if(loopNext == loop2) {
                    return loop1;
                }
                loopNext = loopNext.next;
            }
            return null;
        }
        
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        ListNode l6 = new ListNode(6);
        l5.next = l6;
        ListNode l7 = new ListNode(7);
        l6.next = l7;
        ListNode l8 = new ListNode(8);
        l7.next = l8;
        ListNode l9 = new ListNode(9);
        l8.next = l9;
        ListNode l10 = new ListNode(10);
        l9.next = l10;
        ListNode l11 = new ListNode(11);
        l10.next = l11;
        l11.next = l6;
        ListNode listNode = checkLoop(l1);
        System.out.println(listNode);
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
        
        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }
    
}
