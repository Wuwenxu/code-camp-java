package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class TwoStackofQueue {
    Stack<Integer> stack1 = new Stack<Integer>();  //当做主队列
    Stack<Integer> stack2 = new Stack<Integer>();  //当做辅助

    //入栈函数  
    public void push(int num) {
        stack1.push(num);//直接用栈的push方法       
    }
    //出栈函数  
    public int pop() {
        Integer re = null;
        if (!stack2.empty()) {// 如果栈2不是空的，那么把最上面那个取出来  
            re = stack2.pop();
        } else {
//如果栈2是空的，就把栈1里的数一个个取出来，放到栈2里  
            while (!stack1.empty()) {
                re = stack1.pop();
                stack2.push(re);
            }
//栈2里有数之后，再次把里面的数取出来  
            if (!stack2.empty()) {
                re = stack2.pop();
            }
        }
        return re;
    }
}
