package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

/**
 * 数字在排序数组中出现的次数
 *
 * 统计一个数字在排序数组中出现的次数。
 * 2.排序数组->数组元素有序->二分查找到这个数字，
 * 然后依次上前向后查找出最前一个和最后一个相同元素（因为数组是排序数组，是有序的，这个数字在该数组中连续出现）
 */
public class GetNumberOfK {
        public int GetNumberOfK(int [] array , int k) {
            if(array.length==0||array==null){
                return 0;
            }
            int low=0,high=array.length-1;
            int index=-1;
            while(low<=high){
                int mid=(low+high)>>1;
                if(array[mid]==k){
                    index=mid;
                    break;
                }else if(array[mid]>k){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
            if(index==-1){
                return 0;
            }
            low=index-1;
            high=index+1;
            while(low>=0&&array[low]==k){
                low--;
            }
            while(high<array.length&&array[high]==k){
                high++;
            }
            return high-low-1;
    }
}
