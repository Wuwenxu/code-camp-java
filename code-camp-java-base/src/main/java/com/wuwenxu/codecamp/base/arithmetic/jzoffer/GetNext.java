package com.wuwenxu.arithmetic.jzoffer;

/**
 * 二叉树的下一个节点
 *
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext {
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null) return null;
        TreeLinkNode next = null;
        if(pNode.right != null){
            TreeLinkNode tmp = pNode.right;
            while(tmp.left != null){
                tmp = tmp.left;
            }
            next = tmp;
        }else if(pNode.next != null){
            TreeLinkNode cur = pNode;
            TreeLinkNode parent = pNode.next;
            while(parent != null && cur == parent.right){
                cur = parent;
                parent = parent.next;
            }
            next = parent;
        }
        return next;
    }
}
