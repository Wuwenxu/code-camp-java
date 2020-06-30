package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


/**
 * 变态跳台阶
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class JumpFloorII {

/*
f(n-1) = f(n-2) + f(n-3) + ... + f(0)
f(n) = f(n-1) + f(n-2) + ... + f(0)
f(n) - f(n-1) = f(n-1)
f(n) = 2*f(n-1)
*/

    public int JumpFloorII(int target) {
        return (int)Math.pow(2,target-1);
    }

}
