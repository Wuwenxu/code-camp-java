package com.wuwenxu.arithmetic.jzoffer.LinkedList;


/**
 * 链表中倒数第K个节点
 * <p>
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindKthToTail {
    private static class ListNode {
        int value;
        ListNode next = null;

        ListNode(int val) {
            this.value = val;
        }
    }

    /**
     * 解题思路
     * 先根据K，循环一次，确定时候有这个节点存在，然后再循环取出这个节点的值
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode head, int k) {
        ListNode p1 = head, p2 = head;
        while (p1 != null && k-- > 0) {
            p1 = p1.next;
        }
        //当K值大于链表中真实的节点时，K>0出错
        if (k > 0) {
            return null;
        }
        //p1这里等于p1.next，第多少个就是next->n
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
//        System.out.println(FindKthToTail.FindKthToTail(head, 1).value); // 倒数第一个
//        System.out.println(FindKthToTail.FindKthToTail(head, 5).value); // 中间的一个
        System.out.println(FindKthToTail.FindKthToTail(head, 10).value); // 倒数最后一个就是顺数第一个
    }
}

