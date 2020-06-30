package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


import com.wuwenxu.codecamp.base.arithmetic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上向下打印二叉树
 *
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer>list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null)return list;
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode tmp=queue.poll();
            if(tmp.left!=null)queue.offer(tmp.left);
            if(tmp.right!=null)queue.offer(tmp.right);
            list.add(tmp.val);
        }
        return list;
    }

}
