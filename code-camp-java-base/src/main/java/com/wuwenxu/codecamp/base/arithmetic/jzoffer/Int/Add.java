package com.wuwenxu.codecamp.base.arithmetic.jzoffer.Int;

/**
 * 不用加减乘除做加法
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Add {

    /**
     * 位运算^,&,<<,>>,<<<,>>>> 总结
     *
     * 1.^(亦或运算) ，针对二进制，相同的为0，不同的为1
     * ^异或运算符顾名思义，异就是不同，其运算规则为1^0 = 1 , 1^1 = 0 , 0^1 = 1 , 0^0 = 0
     * 5的二进制位是0000 0101 ， 9的二进制位是0000 1001，也就是0101 ^ 1001,结果为1100 , 00001100的十进制位是12
     *
     * 2.&（与运算） 针对二进制，只要有一个为0，就为0
     * &按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，运算规则即(两个为真才为真)1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     * 3的二进制位是0000 0011 ， 5的二进制位是0000 0101 ， 那么就是011 & 101，由按位与运算规则得知，001 & 101等于0000 0001，最终值为1
     * 7的二进制位是0000 0111，那就是111 & 101等于101，也就是0000 0101，故值为5
     *
     * 3.<<(向左位移) 针对二进制，转换成二进制后向左移动3位，后面用0补齐
     * 5<<2的意思为5的二进制位往左挪两位，右边补0，5的二进制位是0000 0101 ， 就是把有效值101往左挪两位就是0001 0100 ，正数左边第一位补0，负数补1，等于乘于2的n次方，十进制位是20
     *
     * 4.>>(向右位移) 针对二进制，转换成二进制后向右移动3位
     * 凡位运算符都是把值先转换成二进制再进行后续的处理，5的二进制位是0000 0101，右移两位就是把101左移后为0000 0001，正数左边第一位补0，负数补1，等于除于2的n次方，结果为1
     *
     * 5.>>>(无符号右移)  无符号右移，忽略符号位，空位都以0补齐
     * 无符号右移运算符和右移运算符的主要区别在于负数的计算，因为无符号右移是高位补0，移多少位补多少个0。
     * 15的二进制位是0000 1111 ， 右移2位0000 0011，结果为3
     *
     * 6.~(取反运算法)  取反就是1为0,0为1,5的二进制位是0000 0101，取反后为1111 1010，值为-6
     *
     */

//    a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
//    递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止

    public int Add(int num1, int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }
//    public int Add(int num1,int num2) {
//        判断是否第二值为0，0跳出循环
//        while (num2!=0) {
//              0001 0010 ---> 0011 ---> 3
//            int temp = num1^num2;
//             0001 0010 ---> 0000 ---> 0
//            num2 = (num1&num2)<<1;
//            num1 = temp;
//        }
//        return num1;
//    }


    public static void main(String[] args) {
        System.out.println("222222"+(0000<<1));
        System.out.println("异或运算符的结果是 :"+(5^9));  //--->12
        System.out.println("与运算符的结果是:"+(5&7));  //--->5   运算符两边的条件值会先换算成二进制，然后再比对计算
        System.out.println("左移运算符的结果是:"+(5<<2)); //--->20
        System.out.println("右移运算符的结果是:"+(5>>2)); //--->1
        System.out.println("无符号右移运算符的结果是:"+(15>>>2)); //--->3
        System.out.println("取反运算符的结果是:"+(~5)); //--->-6

//        Add add=new Add();
//        System.out.println( add.Add(3, 5));

    }
}
