package com.wuwenxu.arithmetic.jzoffer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 滑动窗口的最大值
 *
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class MaxInWindows
{
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(num == null || size<=0){
            return res;
        }
        int n = num.length;
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for(int i=0;i<num.length;i++){
            while(!queue.isEmpty() && queue.peek()<i-size+1){
                queue.poll();
            }
            while(!queue.isEmpty() && num[queue.peekLast()]<num[i]){
                queue.pollLast();
            }
            queue.offer(i);
            if(i>=size-1){
                res.add(num[queue.peek()]);
            }
        }
        return res;
    }
}
