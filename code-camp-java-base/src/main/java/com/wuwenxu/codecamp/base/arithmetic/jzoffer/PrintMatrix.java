package com.wuwenxu.arithmetic.jzoffer;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PrintMatrix {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int left=0,right=matrix[0].length-1,top=0,boom= matrix.length-1;//记录四个角的位置
        ArrayList<Integer> list = new ArrayList<Integer>();

        while((right>left)&&(boom>top)){
            for(int i=left;i<=right;i++){//从左到右
                list.add(matrix[top][i]);
            }
            for(int i=top+1;i<=boom;i++){//上到下
                list.add(matrix[i][right]);
            }
            for(int i = right-1;i>=left;i--){//右到左
                list.add(matrix[boom][i]);
            }
            for(int i = boom-1;i>top;i--){//下到上
                list.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            boom--;
        }

        if((boom==top)&&(left<right)){//单独剩下一行的情况
            for(int i=left;i<=right;i++){
                list.add(matrix[top][i]);
            }
        }
        if((left==right)&&(boom>top)){//单独剩下一列的情况
            for(int i =top;i<= boom;i++){
                list.add(matrix[i][left]);
            }
        }
        if((boom==top)&&(right==left)){//单独剩下一个元素的情况
            list.add(matrix[left][boom]);
        }
        return list;
    }
}
