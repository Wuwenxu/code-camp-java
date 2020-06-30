package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


import com.wuwenxu.codecamp.base.arithmetic.TreeNode;

import java.util.ArrayList;

/**
 * 二叉树中和为某一值的路径
 *
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return res;
        }
        findPath(root, target, 0);
        return res;
    }

    public void findPath(TreeNode root, int target, int sum) {
        //因为FindPath中和 下面程序中都进行了判null操作，root绝对不可能为 null
        path.add(root.val);
        sum += root.val;
        //已经到达叶子节点，并且正好加出了target
        if (sum == target && root.left == null && root.right == null) {
            //将该路径加入res结果集中
            res.add(new ArrayList(path));
        }
        //如果左子树非空，递归左子树
        if (root.left != null) {
            findPath(root.left, target , sum);
        }
        //如果右子树非空，递归右子树
        if (root.right != null) {
            findPath(root.right, target ,sum);
        }
        //无论当前路径是否加出了target，必须去掉最后一个，然后返回父节点，去查找另一条路径，最终的path肯定为null
        path.remove(path.size() - 1);
        return;

    }

}
