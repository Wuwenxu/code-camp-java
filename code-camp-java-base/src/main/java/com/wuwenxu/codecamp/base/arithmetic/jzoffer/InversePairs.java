package com.wuwenxu.arithmetic.jzoffer;


/**
 * 数组中的逆向对
 *
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，
 * 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * 题目保证输入的数组中没有的相同的数字

 数据范围：

 对于%50的数据,size<=10^4

 对于%75的数据,size<=10^5

 对于%100的数据,size<=2*10^5
 */
public class InversePairs {
    public int times = 0;

    public int InversePairs(int [] array) {
        if(array != null && array.length > 0) {
            mergeSortUp2Down(array, 0, array.length - 1);
        }
        return times;

    }

    public void mergeSortUp2Down(int[] elem, int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = (start + end) / 2;

        mergeSortUp2Down(elem, start, mid);
        mergeSortUp2Down(elem, mid + 1, end);

        merge(elem, start, mid, end);
    }

    public void merge(int[] elem, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while(i <= mid && j <= end) {
            if(elem[i] <= elem[j]) {
                temp[k++] = elem[i++];
            }
            else {
                temp[k++] = elem[j++];
                times += mid - i + 1; // core code, calculate InversePairs
                times %= 1000000007;
            }
        }

        while(i <= mid) {
            temp[k++] = elem[i++];
        }

        while(j <= end) {
            temp[k++] = elem[j++];
        }

        for (i = 0; i < k; i++) {
            elem[start + i] = temp[i];
        }
        temp = null;
    }
}
