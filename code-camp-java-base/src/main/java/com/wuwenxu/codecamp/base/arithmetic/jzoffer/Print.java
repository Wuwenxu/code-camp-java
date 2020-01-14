package com.wuwenxu.arithmetic.jzoffer;

import java.util.ArrayList;

/**
 * 按之字顺序打印二叉树
 *
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class Print {
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
        if(level%2==0){
            col.add(cur.val);
        }else{
            col.add(0,cur.val);
        }
        travel(cur.left,res,level+1);
        travel(cur.right,res,level+1);
    }
}
