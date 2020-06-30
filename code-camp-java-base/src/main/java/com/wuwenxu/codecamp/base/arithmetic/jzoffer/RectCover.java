package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


/**
 * 矩形覆盖
 *
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class RectCover {
        public int RectCover(int n) {
            int x = 1, y = 2;
            if(n <= 2) return n;
            for(int i = 3; i <= n; i++){
                y += x;
                x = y - x;
            }
            return y;
        }
}
