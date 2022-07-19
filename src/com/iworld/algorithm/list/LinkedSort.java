package com.iworld.algorithm.list;


/**
 * @author gq.cai
 * @version 1.0
 * @description 链表排序
 * https://leetcode-cn.com/problems/7WHec2/
 * @date 2021/8/16 20:32
 */
public class LinkedSort {
    
    /**
     * 题目：对单条单链表排序
     * 实现 1.将链表转成数组
     * 2.对数组进行快排
     * 3.对快排后的数组转换成链表
     * https://leetcode-cn.com/problems/7WHec2/
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while (cur !=null) {
            length ++;
            cur = cur.next;
        }
        int[] arr = new int[length];
        int position = 0;
        cur = head;
        while (cur !=null) {
            arr[position++] = cur.val;
            cur = cur.next;
        }
        split(arr, 0, arr.length - 1);
        cur = new ListNode(arr[0]);
        ListNode h = cur;
        for (int i = 1; i < arr.length; i++) {
            ListNode listNode = new ListNode(arr[i]);
            cur.next = listNode;
            cur = listNode;
        }
        return h;
    }
    
    /**
     * 链表快排
     * https://leetcode-cn.com/problems/7WHec2/
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length ++;
            cur = cur.next;
        }
        HeadTailNode headTailNode = process(head, length);
        return headTailNode.head;
    }
    
    /**
     * 链表快排分区 小于区 等于区 大于区
     * @param listNode
     * @param pivot
     * @return
     */
    public ListNodePart partition(ListNode listNode, ListNode pivot) {
        ListNode lh = null;
        ListNode lt = null;
        int ll = 0;
        ListNode eh = null;
        ListNode et = null;
        ListNode gh = null;
        ListNode gt = null;
        int gl = 0;
        ListNode cur = listNode;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = null;
            if (cur.val < pivot.val) {
                if (lh == null) {
                    lh = cur;
                } else {
                    lt.next = cur;
                }
                lt = cur;
                ll ++;
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
                gl ++;
            }
            cur = next;
        }
        return new ListNodePart(lh, lt, ll, eh, et, gh, gt, gl);
    }
    
    /**
     * 链表快排
     * @param l
     * @param length
     * @return
     */
    public HeadTailNode process(ListNode l, int length) {
        if (l == null) {
            return null;
        }
        if (length == 1) {
            return new HeadTailNode(l, l);
        }
        int randomIndex = (int) (Math.random() * length);
        ListNode randomNode = l;
        while (randomIndex -- != 0) {
            randomNode = randomNode.next;
        }
        ListNodePart partition = partition(l, randomNode);
        HeadTailNode lht = process(partition.lHead, partition.lLength);
        HeadTailNode ght = process(partition.gHead, partition.gLength);
        if (lht != null) {
            lht.tail.next = partition.eHead;
        }
        if (ght != null) {
            partition.eTail.next = ght.head;
        }
        return new HeadTailNode(lht != null ? lht.head : partition.eHead, ght != null ? ght.tail : partition.eTail);
    }
    
    public void split(int[] arr, int l, int r) {
        if (l >= r) {
            return ;
        }
        int num = arr[l + (int) (Math.random() * (r - l + 1))];
        int less = l - 1;
        int big = r + 1;
        int pos = l;
        while (pos < big) {
            if (arr[pos] < num) {
                swap(arr, ++less, pos++);
            } else if (arr[pos] > num) {
                swap(arr, --big, pos);
            } else {
                pos ++;
            }
        }
        split(arr, l, less);
        split(arr, big, r);
    }
    
    public void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
    
    /**
     * 链表归并排序
     * https://leetcode-cn.com/problems/7WHec2/
     * @param head
     * @return
     */
    public ListNode listNodeMergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode slowNext = slow.next;
        slow.next = null;
        ListNode h1 = listNodeMergeSort(head);
        ListNode h2 = listNodeMergeSort(slowNext);
        return mergeNode(h1, h2);
    }
    
    public ListNode mergeNode(ListNode h1, ListNode h2) {
        ListNode res = null;
        ListNode cur = null;
        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                if (cur == null) {
                    res = h1;
                } else {
                    cur.next = h1;
                }
                cur = h1;
                h1 = h1.next;
            } else {
                if (cur == null) {
                    res = h2;
                } else {
                    cur.next = h2;
                }
                cur = h2;
                h2 = h2.next;
            }
        }
        if (h1 != null) {
            if (cur == null) {
                res = h1;
            } else {
                cur.next = h1;
            }
        }
        if (h2 != null) {
            if (cur == null) {
                res = h2;
            } else {
                cur.next = h2;
            }
        }
        return res;
    }
    
    /**
     * 合并
     * @param h1
     * @param h2
     * @return
     */
    public ListNode mergeNodeOptimization(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        ListNode res = new ListNode();
        ListNode cur = res;
        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                cur.next = h1;
                cur = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                cur = h2;
                h2 = h2.next;
            }
        }
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }
        return res.next;
    }
    
    /**
     * 链表插入排序
     * https://leetcode-cn.com/problems/insertion-sort-list/
     * @param head
     * @return
     */
    public ListNode insertSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode resultTail = head;
        ListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            ListNode curNext = cur.next;
            if (cur.val >= resultTail.val) {
                resultTail.next = cur;
                resultTail = cur;
                cur.next = null;
            } else {
                ListNode pre = null;
                ListNode resultCur = head;
                while (resultCur != null) {
                    if (cur.val < resultCur.val) {
                        if (pre == null) {
                            cur.next = head;
                            head = cur;
                        } else {
                            ListNode next = pre.next;
                            pre.next = cur;
                            cur.next = next;
                        }
                        break;
                    }
                    pre = resultCur;
                    resultCur = resultCur.next;
                }
            }
            cur = curNext;
        }
        return head;
    }
    
    /**
     * 链表插入排序优化
     * 使用虚拟节点优化
     * 在原链表操作
     * resultTail 始终为排序后链表尾部 下次遍历节点的开始位置
     * https://leetcode-cn.com/problems/insertion-sort-list/
     * @param head
     * @return
     */
    public ListNode insertSortOptimization(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = new ListNode();
        result.next = head;
        ListNode resultTail = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val >= resultTail.val) {
                resultTail = cur;
            } else {
                ListNode pre = result;
                while (pre.next.val < cur.val) {
                    pre = pre.next;
                }
                resultTail.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = resultTail.next;
        }
        return result.next;
    }
    
    public static void main(String[] args) {
        ListNode head1 = new ListNode(5);
        ListNode head2 = new ListNode(15);
        head1.next = head2;
        ListNode head3 = new ListNode(2);
        head2.next = head3;
        ListNode head4 = new ListNode(23);
        head3.next = head4;
        ListNode head5 = new ListNode(6);
        head4.next = head5;
        ListNode head6 = new ListNode(9);
        head5.next = head6;
        ListNode head7 = new ListNode(12);
        head6.next = head7;
        ListNode head8 = new ListNode(1);
        head7.next = head8;
        ListNode head9 = new ListNode(9);
        head8.next = head9;
        LinkedSort linkedSort = new LinkedSort();
//        ListNode head1 = new ListNode(1);
//        ListNode head2 = new ListNode(1);
//        head1.next = head2;
        ListNode listNode = linkedSort.insertSortOptimization(head1);
        ListNode cur = listNode;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
        System.out.println();
    }
    
    static class HeadTailNode {
        ListNode head;
        ListNode tail;
        
        HeadTailNode(ListNode h, ListNode t) {
            head = h;
            tail = t;
        }
    }
    
    static class ListNodePart {
        ListNode lHead;
        ListNode lTail;
        int lLength = 0;
        ListNode eHead;
        ListNode eTail;
        ListNode gHead;
        ListNode gTail;
        int gLength = 0;
        
        ListNodePart(ListNode lh, ListNode lt, int ll, ListNode eh, ListNode et, ListNode gh, ListNode gt, int gl) {
            lHead = lh;
            lTail = lt;
            lLength = ll;
            eHead = eh;
            eTail = et;
            gHead = gh;
            gTail = gt;
            gLength = gl;
        }
    }
    
    
    static class ListNode {
        int val;
        ListNode next;
    
        ListNode() {
        }
    
        ListNode(int val) {
            this.val = val;
        }
    
        ListNode(int val, ListNode next) {
            this.val = val;
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
