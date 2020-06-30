package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//     //根据方法返回值类型，首先可以想到头插ArrayList的方法
//            ArrayList<Integer> list = new ArrayList<Integer>();
//            while(listNode != null){
//                list.add(0,listNode.val);
//                listNode = listNode.next;
//            }
//            return list;

        //根据栈后进先出的特点实现
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        Stack<Integer> stack = new Stack<Integer>();
//        while (listNode != null) {
//            stack.push(listNode.val);
//            listNode = listNode.next;
//        }
//        while (!stack.isEmpty()) {
//            list.add(stack.pop());
//        }
//        return list;

//    //递归，每访问一个结点的时候，先递归输出它后面的结点
    ArrayList<Integer> list = new ArrayList<Integer>();
        if(listNode != null){
        list = printListFromTailToHead(listNode.next);
        list.add(listNode.val);
    }
        return list;
    }
}


