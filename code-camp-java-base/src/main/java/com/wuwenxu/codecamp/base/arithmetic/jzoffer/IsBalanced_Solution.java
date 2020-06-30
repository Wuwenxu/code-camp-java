package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

import com.wuwenxu.codecamp.base.arithmetic.TreeNode;

/**
 * 平衡二叉树
 *
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class IsBalanced_Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) return true;
        int leftLen = TreeDepth(root.left);
        int rightLen = TreeDepth(root.right);
        int lenDif = leftLen - rightLen;
        if(lenDif < -1 || lenDif > 1) return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    private int TreeDepth(TreeNode root) {
        if(root == null) return 0;
        int leftLen = TreeDepth(root.left);
        int rightLen = TreeDepth(root.right);
        return leftLen > rightLen ? leftLen + 1 : rightLen + 1;
    }
}
