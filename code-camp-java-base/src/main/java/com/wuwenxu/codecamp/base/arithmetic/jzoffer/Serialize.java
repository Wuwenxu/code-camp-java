package com.wuwenxu.codecamp.base.arithmetic.jzoffer;

import com.wuwenxu.codecamp.base.arithmetic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树

 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。

 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

 */
public class Serialize {

    String Serialize(TreeNode root) {
        if(root == null) return "#!";
        String res = root.val + "!";
        res += Serialize(root.left);
        res += Serialize(root.right);
        return res;
    }
    TreeNode Deserialize(String str) {
        String[] values = str.split("!");
        Queue<String> queue = new LinkedList<String>();
        for(int i=0;i<values.length;i++){
            queue.offer(values[i]);
        }
        return recover(queue);
    }
    TreeNode recover(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = recover(queue);
        root.right = recover(queue);
        return root;
    }
}
