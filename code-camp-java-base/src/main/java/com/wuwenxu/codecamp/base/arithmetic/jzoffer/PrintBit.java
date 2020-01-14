package com.wuwenxu.arithmetic.jzoffer;

import java.util.ArrayList;

/**
 * 把二叉树打印成多行
 *
 *从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 */
public class PrintBit {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        travel(pRoot,res,0);
        return res;
    }
    public void travel(TreeNode cur,ArrayList<ArrayList<Integer>> res,int level){
        if(cur==null) return;
        if(res.size()<=level){
            ArrayList<Integer> newLevel = new ArrayList<Integer>();
            res.add(newLevel);
        }
        ArrayList<Integer> col = res.get(level);
        col.add(cur.val);
        travel(cur.left,res,level+1);
        travel(cur.right,res,level+1);
    }
}
