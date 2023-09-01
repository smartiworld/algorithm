package com.iworld.algorithm.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gq.cai
 * @version 1.0
 * @description 链表数据结构
 * @date 2020/12/31 10:24
 */
public class LinkedListOperate {
    
    /**
     * 链表荷兰国旗
     * 实现： 1.创建6各变量 分别为小于区域的头 小于区域的尾 等于区域头 等于区域尾 大于区域头 大于区域尾
     * 遍历链表 小于对比链表放小于区域每次放链表尾部 等于放等于区域链表
     * 最后将三个区域链表连接返回
     * @param head
     * @return
     */
    public static ListNode netherlands(ListNode head, ListNode pivot) {
        ListNode lh = null;
        ListNode lt = null;
        ListNode eh = null;
        ListNode et = null;
        ListNode gh = null;
        ListNode gt = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < pivot.val) {
                if (lh == null) {
                    lh = cur;
                } else {
                    lt.next = cur;
                }
                lt = cur;
            } else if (cur.val == pivot.val) {
                if (eh == null) {
                    eh = cur;
                } else {
                    et.next = cur;
                }
                et = cur;
            } else {
                if (gh == null) {
                    gh = cur;
                } else {
                    gt.next = cur;
                }
                gt = cur;
            }
            cur = cur.next;
        }
        // 小于区链表不为空
        if (lt != null) {
            lt.next = eh;
            // et 不为空
            et = et == null ? lt : et;
        }
        if (et != null) {
            et.next = gh;
        }
        return lh != null ? lh : (eh != null ? eh : gh);
    }
    
//    public static void main(String[] args) {
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        l1.next = l2;
//        ListNode l3 = new ListNode(3);
//        l2.next = l3;
//        ListNode l4 = new ListNode(4);
//        l3.next = l4;
//        ListNode l5 = new ListNode(5);
//        l4.next = l5;
//        ListNode l6 = new ListNode(6);
//        l5.next = l6;
//        ListNode l7 = new ListNode(7);
//        l6.next = l7;
//        ListNode l8 = new ListNode(8);
//        l7.next = l8;
//        ListNode l9 = new ListNode(9);
//        l8.next = l9;
//        ListNode l10 = new ListNode(10);
//        l9.next = l10;
//        ListNode l11 = new ListNode(11);
//        l10.next = l11;
//        l11.next = l6;
////        ListNode listNode = revertLinkedList(l1);
////        while(listNode != null) {
////            System.out.println(listNode.val);
////            listNode = listNode.next;
////        }
//
//    }
    /**
     * 反转链表
     * 两个变量标记 一个标记链表当前节点 一个标记链表前一个节点 遍历
     * 变量记住当前节点下一个 将当前节点的下一个节点重新指向前一个节点
     * 然后将当前节点设置为前一个节点 当前节点下一个节点设置为当前节点
     * https://leetcode-cn.com/problems/UHnkqh/
     * @return
     */
    public static ListNode revertLinkedList(ListNode listNode) {
        if(listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode pre = null;
        ListNode cur = null;
        while(listNode != null) {
            cur = listNode;
            listNode = listNode.next;
            cur.next = pre;
            pre = cur;
        }
        return cur;
    }
    
    public static ListNode revertLinkedList2(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return  listNode;
        }
        ListNode pre = null;
        ListNode cur = listNode;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return cur;
    }
    
    /***
     * 返回链表中间节点 如果是奇数个节点的链表返回中间节点 如果是偶数节点链表 返回上中点。
     * @param head
     * @return
     */
    public ListNode midOrUpMidNode(ListNode head) {
        //链表有两个节点
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //链表至少三个节点
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
//        if (head == null) {
//            return null;
//        }
//        ListNode slow = head;
//        ListNode fast = head.next;
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
    }
    
    /**
     * 奇数节点链表返回中点前一个节点  偶数节点链表返回上中点前一个
     * @param head
     * @return
     */
    public ListNode preMidOrPreUpMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    /**
     * 奇数节点链表返回中点前一个节点  偶数节点链表返回上中点
     * @param head
     * @return
     */
    public ListNode preMidOrUpMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode slow = head;
//        ListNode fast = head.next.next;
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
    }
    
    /**
     * 奇数节点链表返回中间节点  偶数节点返回下中点
     * @param head
     * @return
     */
    public ListNode midOrDownMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public static void main(String[] args) {
        ListNode head1 = new ListNode(5);
        ListNode head2 = new ListNode(5);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(3);
        ListNode head5 = new ListNode(4);
        //ListNode head5 = new ListNode(1);
//        ListNode head4 = new ListNode(4);
//        ListNode head5 = new ListNode(5);
//        ListNode head6 = new ListNode(6);
//        ListNode head7 = new ListNode(7);
//        ListNode head8 = new ListNode(8);
//        ListNode head9 = new ListNode(9);
//        ListNode head10 = new ListNode(10);
//        ListNode head11 = new ListNode(11);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        //head3.next = head1;
        //head4.next = head5;
//        head3.next = head4;
//        head4.next = head5;
//        head5.next = head6;
//        head6.next = head7;
//        head7.next = head8;
//        head8.next = head9;
//        head9.next = head10;
//        head10.next = head11;
        LinkedListOperate linkedListOperate = new LinkedListOperate();
        //linkedListOperate.reorderList2(head1);
//        int numComponents = linkedListOperate.numComponents2(head1, new int[]{4});
//        System.out.println(numComponents);
//        ListNode listNode = linkedListOperate.cycleSortNode(head1, 1);
//
//        System.out.println(listNode.val);
//        ListNode cur = listNode.next;
//        while (cur != listNode) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }
        ListNode listNode = linkedListOperate.deleteNode(head1, 3);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    
    /**
     * 是否是回文链表 不使用额外空间
     * 使用快慢指针找出 中节点或者 中上节点
     * 将中节点后链表反转。将中间节点下一个节点设置为null
     * 找出反转后的头节点，开始头节点和反转链表头节点 同时遍历 有不相等跳出 返回false
     * https://leetcode-cn.com/problems/aMhZSa/
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 快慢指针找出中间节点或者中上节点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 记录中间节点下一个节点 后面反转
        ListNode slowNext = slow.next;
        // 然后设置中间节点下一个节点为null
        slow.next = null;
        // 反转链表 反转链表后的头
        ListNode pre = slow;
        while (slowNext != null) {
            ListNode next = slowNext.next;
            slowNext.next = pre;
            pre = slowNext;
            slowNext = next;
        }
        ListNode cur = pre;
        while (head != null && cur != null) {
            if (cur.val != head.val) {
                return false;
            }
            cur = cur.next;
            head = head.next;
        }
        // 如有需要可以将反转后的链表再反转回原链表
        return true;
    }
    
    /**
     * 链表删除一个节点
     * 注意点 可能存在删除链表头部 换头问题
     * 如果是链表头部 则删除链表头部将链表头下一个节点赋值链表头
     * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur = head;
        ListNode resultHead = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == val) {
                if (cur == resultHead) {
                    resultHead = next;
                    cur.next = null;
                } else {
                    pre.next = cur.next;
                    cur.next = null;
                }
            } else {
                pre = cur;
            }
            cur = next;
        }
        return resultHead;
    }
    
    /**
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     *
     * L0→ L1→ … → Ln-1→ Ln
     * 请将其重新排列后变为：
     *
     * L0→Ln→L1→Ln-1→L2→Ln-2→ …
     *
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * https://leetcode-cn.com/problems/LGjMqU
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 1.找出中间节点或者上中间节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2.中间节点后面链表反转
        ListNode slowNext = slow.next;
        slow.next = null;
        ListNode pre = slow;
        while (slowNext != null) {
            ListNode next = slowNext.next;
            slowNext.next = pre;
            pre = slowNext;
            slowNext = next;
        }
        // 3.两条链条同时遍历 重组链表
        ListNode cur = head;
        while (cur != null && pre != null) {
            ListNode curNext = cur.next;
            ListNode preNext = pre.next;
            cur.next = pre;
            pre.next = curNext;
            cur = curNext;
            pre = preNext;
        }
    }
    public void reorderList2(ListNode head) {
        dfs(head, head.next);
    }
    
    /**
     * 利用方法栈压栈记录慢指针
     * @param slow
     * @param fast
     * @return
     */
    ListNode dfs(ListNode slow,ListNode fast){
        //中间节点判断，数量是奇数 返回后半段开始
        if(fast == null){
            ListNode res=slow.next;
            slow.next=null;
            return res;
        }
        //中间节点判断，数量是偶数 返回后半段开始下一个位置
        if(fast.next == null){
            ListNode res = slow.next.next;
            slow.next.next = null;
            return res;
        }
        //不是中间节点，递归获取对应的节点
        ListNode tail = dfs(slow.next,fast.next.next);
        ListNode res = tail.next;
        tail.next = slow.next;
        slow.next = tail;
        return res;
    }
    
    /**
     * 给定链表头结点head，该链表上的每个结点都有一个 唯一的整型值 。
     *
     * 同时给定列表G，该列表是上述链表中整型值的一个子集。
     *
     * 返回列表G中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表G中）构成的集合。
     *
     * https://leetcode-cn.com/problems/linked-list-components
     *
     * @param head
     * @param nums
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        if (head == null) {
            return 0;
        }
        Set<Integer> setNums = new HashSet<>();
        for (int num : nums) {
            setNums.add(num);
        }
        int sum = 1;
        ListNode cur = head;
        ListNode curHead = head;
        while (cur != null) {
            // 目标不包含链表值的时候需要分割 当前不为头组件+1
            if (!setNums.contains(cur.val)) {
                ListNode curNext = cur.next;
                if (cur == curHead) {
                    cur = curNext;
                } else {
                    cur = curNext;
                    sum ++;
                }
                curHead = cur;
                continue;
            }
            cur = cur.next;
        }
        if (curHead == null) {
            sum --;
        }
        return sum;
    }
    
    public int numComponents2(ListNode head, int[] nums) {
        Set<Integer> setNums = new HashSet<>();
        for (int num : nums) {
            setNums.add(num);
        }
        int sum = 0;
        boolean isComponents = false;
        ListNode cur = head;
        while (cur != null) {
            if (setNums.contains(cur.val)) {
                isComponents = true;
            } else if (isComponents) {
                // 不匹配时匹配数量变化
                sum ++;
                // 重置匹配状态
                isComponents = false;
            }
            cur = cur.next;
        }
        // 尾部节点也是要求子链表内容
        if (isComponents) {
            sum ++;
        }
        return sum;
    }
    
    /**
     * 给定循环升序列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
     *
     * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
     *
     * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
     *
     * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
     *
     * 例子1：
     * 输入：head = [3,4,1], insertVal = 2
     * 输出：[3,4,1,2]
     * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
     *
     * 例子2：
     * 输入：head = [], insertVal = 1
     * 输出：[1]
     * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
     *
     * 例子3：
     * 输入：head = [1], insertVal = 0
     * 输出：[1,0]
     *
     * 链接：https://leetcode-cn.com/problems/4ueAj6
     * @param head
     * @param insertVal
     * @return
     */
    public ListNode cycleSortNode(ListNode head, int insertVal) {
        ListNode insertNode = new ListNode(insertVal);
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }
        ListNode cur = head.next;
        ListNode pre = head;
        // 最小值的前一个节点 也代表最大值节点 如果为空则代表head节点为最小节点
        ListNode minPre = null;
        // 记录链表最小节点
        int minVal = head.val;
        // 当前节点是否插入到当前链表中
        boolean isInsert = false;
        while (cur != head) {
            if (cur.val < minVal) {
                minPre = pre;
                minVal = cur.val;
            }
            // 当前遍历顺序 插入位置前节点小于等于当前值  后节点大于等于当前值
            if (pre.val <= insertVal && insertVal <= cur.val) {
                pre.next = insertNode;
                insertNode.next = cur;
                isInsert = true;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        // 节点未插入当前链表
        if (!isInsert) {
            // 最小节点前置节点不为空代表 head节点不是最小节点 插入节点为最大值或者最小值
            if (minPre != null && (insertVal < minVal || minPre.val < insertVal)) {
                pre = minPre;
            }
            ListNode next = pre.next;
            pre.next = insertNode;
            insertNode.next = next;
        }
        return head;
    }
    
    private ListNode head;
    private ListNode tail;
    
    public void add(ListNode listNode) {
        if(head == null) {
            head = listNode;
        } else {
            head.next = listNode;
        }
        tail = listNode;
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
    
    private static class DoubleListNode {
        int val;
    
        DoubleListNode pre;
        DoubleListNode next;
        DoubleListNode(DoubleListNode pre, DoubleListNode next) {
            this.next = next;
            this.pre = pre;
        }
    }
}
