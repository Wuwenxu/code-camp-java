package com.wuwenxu.arithmetic.jzoffer;

/**
 * 对称的二叉树
 *
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的
 */
public class IsSymmetrical {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null) return true;
        boolean res = Judge(pRoot.left,pRoot.right);
        return res;
    }
    public boolean Judge(TreeNode left,TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        return (left.val == right.val) &&
                Judge(left.left,right.right) &&
                Judge(left.right,right.left);
    }
}
