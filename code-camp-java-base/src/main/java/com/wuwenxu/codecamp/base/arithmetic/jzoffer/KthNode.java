package com.wuwenxu.arithmetic.jzoffer;

import java.util.ArrayList;

/**
 * 二叉搜索树的第K个结点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode {
    public ArrayList<TreeNode> res = new ArrayList<TreeNode>();
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot==null || k==0) return null;
        inorderTra(pRoot);
        if(k<=0 || k>res.size()) return null;
        return res.get(k-1);
    }
    public void inorderTra(TreeNode root){
        if(root == null) return ;
        inorderTra(root.left);
        res.add(root);
        inorderTra(root.right);
    }
}
