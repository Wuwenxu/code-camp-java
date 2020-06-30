package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

/**
 * 丑数
 *
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class GetUglyNumber_Solution {

        public int GetUglyNumber_Solution(int index) {
            if(index <= 0)
                return 0;
            int[] uglyNumber = new int[index];
            uglyNumber[0] = 1;
            int index2 = 0;
            int index3 = 0;
            int index5 = 0;
            int curr = 1;
            while(curr < index) {
                int min = getMin(uglyNumber[index2] * 2, uglyNumber[index3] * 3, uglyNumber[index5] * 5);
                uglyNumber[curr] = min;
                while(uglyNumber[index2]*2 <= uglyNumber[curr])
                    index2++;
                while(uglyNumber[index3]*3 <= uglyNumber[curr])
                    index3++;
                while(uglyNumber[index5]*5 <= uglyNumber[curr])
                    index5++;
                curr++;
            }
            int ugly = uglyNumber[curr - 1];

            return ugly;
        }
        public int getMin(int i, int j, int k) {
            int min = (i < j) ? i : j;
            min = (min < k) ? min : k;
            return min;
        }
}
