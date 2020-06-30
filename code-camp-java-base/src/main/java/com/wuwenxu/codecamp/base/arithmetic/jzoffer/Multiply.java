package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

/**
 * 构建乘机数组
 *
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法
 */
public class Multiply {
    public int[] multiply(int[] A) {
        int len = A.length;
        int[] B = new int[len];
        int lToR = 1;
        int rToL = 1;
        B[0] = 1;
        for(int i=1;i<=len-1;i++){
            lToR *= A[i-1];
            B[i] = lToR ;
        }
        for(int i=len-2;i>=0;i--){
            rToL *= A[i+1];
            B[i] = B[i]*rToL;
        }
        return B;
    }
}
