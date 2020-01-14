package com.wuwenxu.arithmetic.jzoffer;


/**
 * 二叉树的镜像
 *
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class Mirror {
    //递归实现

        public void Mirror(TreeNode root) {
            if (root == null) {
                return;
            }
            //将一个节点的左右节点交换
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
            if (root.left != null) {
                Mirror(root.left);
            }
            if (root.right != null) {
                Mirror(root.right);
            }
        }
}
