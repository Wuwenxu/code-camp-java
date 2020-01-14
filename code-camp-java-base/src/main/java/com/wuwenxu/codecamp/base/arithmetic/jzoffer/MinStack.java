package com.wuwenxu.arithmetic.jzoffer;

import java.util.Stack;

/**
 * 包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class MinStack {




        private Stack<Integer> data = new Stack<Integer>();
        private Stack<Integer> min = new Stack<Integer>();
        public void push(int node) {
            data.push(node);
            if(min.isEmpty() || node <= min.peek())//压入最小值结点
            {
                min.push(node);
            }
        }

        public void pop() {
            if(data.peek() != min.peek())//出栈时两个元素不一样，则栈data出栈，min栈不出栈。
            {
                data.pop();
            }
            else
            {
                data.pop();
                min.pop();
            }
        }
        public int top() {
            return data.peek();
        }
        public int min() {
            return min.peek();
        }
}
