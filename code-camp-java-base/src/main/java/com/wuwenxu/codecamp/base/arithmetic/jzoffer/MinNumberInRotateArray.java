package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array)
    {
        // 先对数组array的情况进行判断
        if (array == null || array.length == 0)
        {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;
        int mid = left; // 数组中第一个数字就是最小的数字

        while (array[left] >= array[right])
        {
            // 当left指针和right指针相邻时
            if (right - left == 1)
            {
                mid = right;
                break;
            }

            mid = (left + right) / 2;

            // 如果下标为left、right和mid指向的三个数字相等，
            // 则只能顺序查找
            if (array[left] == array[right] && array[mid] == array[left])
                return minInOrder(array, left, right);

            if (array[mid] >= array[left])
            {
                left = mid;
            }
            else if (array[mid] <= array[right])
            {
                right = mid;
            }
        }
        return array[mid];
    }

    int minInOrder(int[] numbers, int left, int right)
    {
        int result = numbers[left];
        for (int i=left+1; i<=right; i++)
        {
            if (result > numbers[i])
                result = numbers[i];
        }
        return result;
    }
}
