package com.wuwenxu.arithmetic.jzoffer;

/**
 * 二叉树的深度
 *
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 递归的方式
 */
public class TreeDepth {


    public int TreeDepth(TreeNode root) {
        if(root==null)
            return 0;
        int leftnum=TreeDepth(root.left);
        int rightnum = TreeDepth(root.right);
        return Math.max(leftnum,rightnum)+1;

    }
}
