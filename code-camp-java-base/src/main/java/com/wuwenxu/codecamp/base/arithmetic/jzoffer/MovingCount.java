package com.wuwenxu.arithmetic.jzoffer;

/**
 * 机器人的运动范围
 *
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
 * 因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class MovingCount {
    public int movingCount(int threshold, int rows, int cols)
    {
        boolean[] visited = new boolean[rows*cols];
        int res = count(0,0,rows,cols,threshold,visited);
        return res;
    }
    public int count (int i,int j,int rows,int cols,int threshold,boolean[] visited){
        int cnt = 0;
        int index  = i*cols+j;
        if(check(threshold,i,j,rows,cols,visited) == true){
            visited[index] = true;
            cnt = 1 + count(i+1,j,rows,cols,threshold,visited)
                    + count(i-1,j,rows,cols,threshold,visited)
                    + count(i,j+1,rows,cols,threshold,visited)
                    + count(i,j-1,rows,cols,threshold,visited);
        }
        return cnt;
    }
    public boolean check(int threshold,int i,int j,int rows,int cols,boolean[] visited){
        int valRow = getVal(i);
        int valCol = getVal(j);
        if(i>=0&&i<rows&&j>=0&&j<cols&&valRow+valCol<=threshold&&visited[i*cols+j]==false){
            return true;
        }
        return false;
    }
    public int getVal(int i){
        int val = 0;
        while(i>0){
            val += i%10;
            i /= 10;
        }
        return val;
    }
}
