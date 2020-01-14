package com.wuwenxu.arithmetic.jzoffer;

/**
 * 数组中只出现一次的数字
 *
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * //num1,num2分别为长度为1的数组。传出参数
 //将num1[0],num2[0]设置为返回结果

 思路
 先考虑一个数组里只有一个数出现一次，其他两个数都出现两次的情况：一个数跟自己异或后为0，
 一个数组里只有一个数出现一次其他两次，挨个异或后最后得到的结果就是只出现一次的那个数。
 我们把这个数组分为两部分，每部分只有一个数只出现一次：我们分的时候，把所有数都异或后，
 得到的结果肯定不为0，其实是那两个只出现一次的不同的数的异或，我们从低位到高危找到第一个不为0的那位，
 异或后这两个数的这位上肯定是一个为1一个为0，所以我们根据这位将整个数组分为两部分，这位上为1的和这位上为0的，
 这样就保证这两个只出现一次的数会被分到两个部分中，然后每部分按只有一个只出现一次的方法解决即可。
 */
public class FindNumsAppearOnce {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || array.length == 0) {
            return;
        }
        if (array.length < 2) {
            num1[0] = 0;
            num2[0] = 0;
            return;
        }
        int temp1 = array[0];
        for (int i = 1; i < array.length; i++) {
            temp1 = temp1 ^ array[i];
        }
        int firstBit = 0;
        while (((temp1 & 1) == 0) && (firstBit < 32)) {
            firstBit++;
            temp1 = temp1 >> 1;
        }
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if (isBit(array[i], firstBit)) {
                num1[0] = num1[0] ^ array[i];
            } else {
                num2[0] = num2[0] ^ array[i];
            }
        }
        return;
    }
    public boolean isBit(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }
}
