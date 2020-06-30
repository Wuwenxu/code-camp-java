package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
 */
public class Fibonacci {
        public int Fibonacci(int n) {
            int[] fib = new int[40];
            fib[0] = 0;
            fib[1] = 1;
            for(int i = 2; i < 40; i++)
                fib[i] = fib[i-1] + fib[i-2];
            return fib[n];
        }
}
