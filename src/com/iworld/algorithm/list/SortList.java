package com.iworld.algorithm.list;

/**
 * @author gq.cai
 * @version 1.0
 * @description 148. Sort List   Medium
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Example 1:
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 * https://leetcode.com/problems/sort-list/
 * @date 2022/8/26 16:57
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        int k = 1;
        ListNode ans = head;
        while (k < length) {
            ans = mergeList(ans, k);
            k <<= 1;
        }
        return ans;
    }
    
    private ListNode[] mergeTwoList(ListNode l1, ListNode l2) {
        ListNode[] ans = new ListNode[2];
        if (l1 == null) {
            ans[0] = l2;
            return ans;
        }
        if (l2 == null) {
            ans[0] = l1;
            return ans;
        }
        ListNode result = new ListNode();
        ListNode cur = result;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        while (cur.next != null) {
            cur = cur.next;
        }
        ans[0] = result.next;
        ans[1] = cur;
        return ans;
    }
    
    private ListNode mergeList(ListNode head, int k) {
        ListNode result = new ListNode();
        ListNode ans = result;
        ListNode cur = head;
        while (cur != null) {
            ListNode[] leftListNodes = getKLisNode(cur, k);
            if (leftListNodes[1] == null || leftListNodes[1].next == null) {
                ans.next = leftListNodes[0];
                break;
            }
            ListNode nextHead = leftListNodes[1].next;
            leftListNodes[1].next = null;
            ListNode[] rightListNodes = getKLisNode(nextHead, k);
            if (rightListNodes[1] == null) {
                break;
            }
            nextHead = rightListNodes[1].next;
            rightListNodes[1].next = null;
            ListNode[] mergeListNodes = mergeTwoList(leftListNodes[0], rightListNodes[0]);
            ans.next = mergeListNodes[0];
            ans = mergeListNodes[1];
            cur = nextHead;
        }
        return result.next;
    }
    
    private ListNode[] getKLisNode(ListNode head, int k) {
        ListNode[] ans = new ListNode[2];
        if (head == null || k == 0) {
            return ans;
        }
        ans[0] = head;
        while (k > 0) {
            ans[1] = head;
            head = head.next;
            if (head == null) {
                break;
            }
            k--;
        }
        return ans;
    }
    
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        ListNode[] listNodes = process2(head, n);
        return listNodes[0];
    }
    
    private ListNode[] process2(ListNode head, int length) {
        if (head == null) {
            return null;
        }
        // 还剩一个长度时
        if (length == 1) {
            return new ListNode[]{head, head};
        }
        int randomIndex = (int) (Math.random() * length);
        ListNode randomNode = head;
        while (randomIndex != 0) {
            randomNode = randomNode.next;
            randomIndex--;
        }
        ListNodeInfo listNodeInfo = partition(head, randomNode);
        ListNode[] lessListNode = process2(listNodeInfo.lh, listNodeInfo.lLength);
        ListNode[] greatListNode = process2(listNodeInfo.gh, listNodeInfo.gLength);
        if (lessListNode != null) {
            lessListNode[1].next = listNodeInfo.eh;
        }
        if (greatListNode != null) {
            listNodeInfo.et.next = greatListNode[0];
        }
        return new ListNode[]{lessListNode != null ? lessListNode[0] : listNodeInfo.eh, greatListNode != null ? greatListNode[1] : listNodeInfo.et};
    }
    
    private ListNodeInfo partition(ListNode head, ListNode pivot) {
        ListNode lh = null;
        ListNode lt = null;
        ListNode eh = null;
        ListNode et = null;
        ListNode gh = null;
        ListNode gt = null;
        int lLength = 0;
        int gLength = 0;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = null;
            if (cur.val < pivot.val) {
                if (lh == null) {
                    lh = cur;
                } else {
                    lt.next = cur;
                }
                lLength++;
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
                gLength++;
            }
            cur = next;
        }
        return new ListNodeInfo(lh, lt, lLength, eh, et, gh, gt, gLength);
    }
    
    static class ListNodeInfo {
        ListNode lh;
        ListNode lt;
        int lLength;
        ListNode eh;
        ListNode et;
        ListNode gh;
        ListNode gt;
        int gLength;
        
        public ListNodeInfo(ListNode lh, ListNode lt, int lLength, ListNode eh, ListNode et, ListNode gh, ListNode gt, int gLength) {
            this.lh = lh;
            this.lt = lt;
            this.lLength = lLength;
            this.eh = eh;
            this.et = et;
            this.gh = gh;
            this.gt = gt;
            this.gLength = gLength;
        }
    }
    
    public ListNode sortList3(ListNode head) {
        int step = 1;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int size = getSize(head);
        while (step < size) {
            dummyHead.next = splitAndMergeAtK(dummyHead.next, step);
            step <<= 2;
        }
        return dummyHead.next;
    }
    private ListNode splitAndMergeAtK(ListNode head, int K) {
        // iterative merge for every 2 size K segment,
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (head != null) {
            ListNode[] headTail1 = getK(head, K); // Get a segment of size K
            head = headTail1[1].next;
            headTail1[1].next = null;
            if (head == null) {
                cur.next = headTail1[0];
                return dummyHead.next;
            }
            ListNode[] headTail2 = getK(head, K); // Get another segment of size K
            
            head = headTail2[1].next;
            headTail2[1].next = null;
            ListNode[] mergedHeadTail =  mergeK(headTail1[0], headTail2[0]); //merge two segments
            cur.next = mergedHeadTail[0]; // hook up
            cur = mergedHeadTail[1];
        }
        return dummyHead.next;
    }
    private ListNode[] getK(ListNode head, int K) { // get a segment of size K
        ListNode[] ans = new ListNode[2]; //head must not be null;
        ans[0] = head;
        for (int i = 0; i < K; i++) {
            ans[1] = head;
            head = head.next;
            if (head == null) {
                break;
            }
        }
        return ans;
    }
    private ListNode[] mergeK(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
            cur.next = null;
        }
        cur.next = head1 != null ? head1 : head2;
        while (cur.next != null) {
            cur = cur.next;
        }
        return new ListNode[]{dummyHead.next, cur};
    }
    private int getSize(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
    
    public static void main(String[] args) {
        //[4,19,14,5,-3,1,8,5,11,15]
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(1);
        l2.next = l3;
        ListNode l4 = new ListNode(3);
        l3.next = l4;
        ListNode l5 = new ListNode(2);
        l4.next = l5;
        SortList sortList = new SortList();
        ListNode listNode = sortList.sortList(l1);
        //ListNode listNode = sortList.sortList2(l1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    

}
