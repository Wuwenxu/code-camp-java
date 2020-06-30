package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


/**
 * 反转链表
 *
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList {
    //迭代法
    /*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
        public ListNode ReverseList(ListNode head) {
            if(head == null)
                return null;
            ListNode pre = null;
            ListNode next = null;

            while(head != null){
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }
}
