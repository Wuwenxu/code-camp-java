package com.wuwenxu.arithmetic.jzoffer;

/**
 * 剪绳子
 *
 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 输入描述:
 输入一个数n，意义见题面。（2 <= n <= 60）
 输出描述:
 输出答案。
 示例1
 输入
 复制
 8
 输出
 复制
 18
 */
public class CutRope {

    public static int cutRope(int len){
        if(len<2)
            return 0;
        if(len==2)
            return 1;
        if(len==3)
            return 2;
        //啥也不管,先尽可能减去长度为3的段
        int timeOfThree=len/3;

        //判断还剩下多少，再进行处理
        if(len-timeOfThree*3==1)
            timeOfThree-=1;
        int timeOfTwo=(len-timeOfThree*3)/2;

        return (int) ((Math.pow(3, timeOfThree))*(Math.pow(2, timeOfTwo)));
    }
}
