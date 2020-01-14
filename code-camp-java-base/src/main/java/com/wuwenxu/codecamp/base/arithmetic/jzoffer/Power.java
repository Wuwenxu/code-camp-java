package com.wuwenxu.arithmetic.jzoffer;

/**
 * 数值的整数次方
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

 保证base和exponent不同时为0
 */
public class Power {
        public double Power(double base, int exponent) {
            boolean isNegtive = false;
            if(exponent == 0)
                return 1;
            if(exponent == 1)
                return base;
            if(exponent < 0){
                isNegtive = true;
                exponent *= -1;
            }
            double pow = Power(base*base, exponent>>1);
            if((exponent & 0x1)== 1){
                pow *= base;
            }
            return isNegtive ? 1/pow : pow;
        }
}
