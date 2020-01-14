package com.wuwenxu.arithmetic.jzoffer;


/**
 * 二叉搜索树的后序遍历序列
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int [] sequence) {

        if(sequence == null || sequence.length == 0) {
            return false;
        }
        else if(sequence.length == 1) {
            return true;
        }

        return JudgePostOrder(sequence, 0, sequence.length - 1);

    }

    public boolean JudgePostOrder(int[] sequence, int start, int end) {

        if(start >= end) {
            return true;
        }

        int i = start;
        while (i <= end && sequence[i] < sequence[end]) {
            i++;
        }

        for (int j = i; j <= end; j++) {
            if(sequence[j] < sequence[end]) {
                return false;
            }
        }

        return JudgePostOrder(sequence, start, i - 1) && JudgePostOrder(sequence, i, end - 1);

    }
}
