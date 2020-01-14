package com.wuwenxu.arithmetic.jzoffer;

/**
 * 求1+2+3+4。。。+n
 *
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Sum_Solution {

//    一旦不需要if，就要利用&&的特点，第一个条件语句为 false 的情况下不会去执行第二个条件语句

    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
}
