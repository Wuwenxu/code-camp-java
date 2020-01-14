package com.wuwenxu.arithmetic.jzoffer;

/**
 * 数组中重复的数字
 *
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是第一个重复的数字2。
 *
 *  // Parameters:
 //    numbers:     an array of integers
 //    length:      the length of array numbers
 //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
 //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
 //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
 // Return value:       true if the input is valid, and there are some duplications in the array number
 //                     otherwise false


 思路一:最好的解法

 此大法利用了哈希的特性，但不需要额外的存储空间。 因此时间复杂度为O(n)，不需要额外空间！

 因为列表总共有n个元素，所有元素可能取到的元素有0~n-1，共n种。如果不存在重复的数字，那么排序后数字i将会出现在下标为i的位置。现在让我们重新扫描数组，

 当扫描到下标为i的数字时，首先比较这个数字（记为m）与i是否相等：
 如果是，继续扫描下一个元素，
 如果不是，则再拿它与第m个数字比较：
 如果它和第m个数字相同，就找到了一个重复的元素；
 如果不同，就将m与第m个数字互换。接下来继续重头开始，重复换这个比较。
 */
public class duplicate {

        public boolean duplicate(int numbers[],int length,int [] duplication) {
            //考虑特殊情况,并判断数组是否合法（每个数都在0~n-1之间）
            if(length<=0||numbers==null) return false;
            for(int i=0;i<length;i++){
                if(numbers[i]<=0||numbers[i]>length-1)
                    return false;
            }
            //关键代码来了
            for(int i=0;i<length;i++){
                while(numbers[i]!=i){
                    if(numbers[i]==numbers[numbers[i]]){
                        duplication[0] = numbers[i];
                        return true;
                    }
                    //交换numbers[i]和numbers[numbers[i]]
                    int temp = numbers[i];
                    numbers[i] = numbers[temp];
                    numbers[temp] = temp;
                }
            }
            return false;
        }
}
