package com.wuwenxu.arithmetic.jzoffer;

/**
 * 左旋转字符串
 *
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 *
 *
 *  * 基本思路：
 * 把前三个字符移到后面，我们就把前三个字符分到第一部分，把后面的所有字符都分到第二部分，先翻转这两部分，
 * 再翻转整个字符串，即可得到结果。
 *
 * ps：要注意考虑边界条件：1、考虑输入空字符串
 *                                      2、考虑输入的翻转n为0的情况下
 *

 */
public class LeftRotateString {
    public String LeftRotateString(String str,int n) {

        char[] chars = str.toCharArray();

        /*
        * 考虑如果传入的是空字符串的处理
        * */
        if(str.isEmpty()){
            return "";
        }

        /*
        * 考虑如果输入为n的情况
        * */
        if (n==0){
            return str;
        }


        Reverse(chars,0,n-1);
        Reverse(chars,n,chars.length-1);
        Reverse(chars,0,chars.length-1);
        String strChange = String.valueOf(chars);

        return strChange;
    }

    //start 的参数为数组开头，end的参数为数组的结尾;
    //数组作为输入，一般情况下需要至少两个参数，一个是数组，一个是数组长度
    /**
     * 如果是字符串是偶数个数（字符数组首字符下标是0），那么翻转的middle值为 (start+end)/2+1;
     *      为什么加一，如果字符数组下标是012345，则必须为middle为3，因为for循环中i<middle注意体会这个小于号，
     *      而不是小于等于号，小于号(0+5)/2=2,加一，则为
     *      */
    public void Reverse(char[] chars,int start,int end){

        char  temp= ' ';

        int isZero = (start+end)%2;
        int middle = 0;
        if (isZero == 0){
            middle = (start+end)/2;
        }else {
            middle = (start+end)/2+1;
        }
        int j=0;
        for (int i =start;i<middle;i++){
            temp = chars[start+j];
            chars [start+j] = chars[end-j];
            chars[end-j] = temp;
            j++;
        }
    }

}
