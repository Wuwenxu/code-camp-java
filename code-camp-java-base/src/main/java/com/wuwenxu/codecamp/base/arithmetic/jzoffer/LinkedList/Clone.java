package com.wuwenxu.arithmetic.jzoffer.LinkedList;


/**
 * 复杂链表的复制
 *
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Clone {

    private class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        //拷贝并且插入结点
        RandomListNode p1 = pHead;
        while (p1 != null) {
            RandomListNode temp = new RandomListNode(p1.label);
            //插入
            temp.next = p1.next;
            p1.next = temp;
            p1 = temp.next;
        }
        //置新链表的random域
        p1 = pHead;
        while (p1 != null) {
            if (p1.random != null)
                p1.next.random = p1.random.next;//这个易错，看分析图
            p1 = p1.next.next;

        }
        RandomListNode head = pHead.next;
        RandomListNode temp1 = head;
        p1 = pHead;
        while (p1.next != null) {
            temp1 = p1.next;
            p1.next = temp1.next;
            p1 = temp1;
        }
        return head;
    }
}

