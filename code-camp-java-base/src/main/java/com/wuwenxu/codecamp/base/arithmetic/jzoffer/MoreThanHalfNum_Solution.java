package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


/**
 * 数组中出现次数超过一半的数字
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0
 */
public class MoreThanHalfNum_Solution {
        public int MoreThanHalfNum_Solution(int [] array) {
            int target = array[array.length/2];
            int n = 0;
            for(int i=0;i<array.length;i++){
                if(array[i]==target)
                    n++;
            }
            if(n>array.length/2)
                return target;
            return 0;
        }
}
