package com.wuwenxu.arithmetic.jzoffer;

import java.util.*;

/**
 * 最小的k个数
 *
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastNumbers_Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            ArrayList<Integer>res=new ArrayList<>();
            if(input==null||k>input.length)
                return res;
            int low=0;
            int high=input.length-1;
            while(low<high){
                int t=partition(low,high,input);
                if(t>k){
                    high=t-1;
                }else if(t<k){
                    low=t+1;
                }else{
                    break;
                }
            }
            for(int i=0;i<k;i++){
                res.add(input[i]);
            }
            return res;
        }
        public int partition(int low ,int high,int []input){
            int key=input[low];
            while(low<high){
                while(low<high&&input[high]>=key){
                    high--;
                }
                input[low]=input[high];
                while(low<high&&input[low]<=key){
                    low++;
                }
                input[high]=input[low];
            }
            input[low]=key;
            return low;
        }
}
