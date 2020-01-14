package com.wuwenxu.arithmetic.jzoffer;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 *
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 *
 * 思路，双指针法。
 根据题目内容分析可知，输出所有和为S的连续正数序列，其实际上就是求出和为S公差为1的等差数列。
 由等差数列公式可知S=n*（a1+an）/2=a1+a2+a3+...+an。故可定义两个变量a1,an,a1的数值为1，an的初值为2(若sum<=2，a1和an不存在)。
 若a1到an序列和为S,则将a1...an添加到结合中返回，若a1到an序列和小于S,则an++;若a1到an序列和大于于S，则将a1++。当a1等于(S+1)/2时停止。
 */
public class FindContinuousSequence {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<ArrayList<Integer> >();
        int a1 = 1;
        int a2 = 2;


        if(sum<=2)
            return result;

        while(a1< (sum+1)/2){

            if( (a2-a1+1)*(a1+a2)/2 < sum){
                a2++;
            }else if((a2-a1+1)*(a1+a2)/2 == sum){
                ArrayList<Integer>  tmp_result = new ArrayList<Integer>();
                for(int i =a1;i<=a2;i++){
                    tmp_result.add(i);
                }
                result.add(tmp_result);
                a1++;
                a2++;
            }else{
                a1++;
            }

        }
        return result;
    }
}
