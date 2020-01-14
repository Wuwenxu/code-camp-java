package com.wuwenxu.arithmetic.jzoffer;


/**
 * 字符流中第一个不重复的字符
 *
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class FirstAppearingOnce {
    private int[] arr =new int[256];
    private int cnt = 1;

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        int index = (int)ch - ((int)'0' - 48);
        System.out.println(index);
        if(arr[index] == 0){
            arr[index] = cnt;
        }else{
            arr[index] = -1;
        }
        cnt++;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char ch = '#';
        int minIndex = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i] != -1 && arr[i] !=0){
                if(minIndex > arr[i]){
                    minIndex = arr[i];
                    ch = (char)(i + '0' - 48 );
                }
            }
        }
        return ch;
    }
}
