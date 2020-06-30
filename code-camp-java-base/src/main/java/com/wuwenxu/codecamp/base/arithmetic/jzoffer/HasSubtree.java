package com.wuwenxu.codecamp.base.arithmetic.jzoffer;


import com.wuwenxu.codecamp.base.arithmetic.TreeNode;

/**
 * 树的子结构
 *
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构
 */
public class HasSubtree {
    /**
     public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;

     }

     }
     */
        TreeNode root1Now=new TreeNode(0);
        TreeNode root2Root=new TreeNode(0);
        boolean newStart=true;
        boolean begin=true;
        public boolean HasSubtree(TreeNode root1,TreeNode root2) {
            if(begin==true)
            {
                if(root2==null)
                {
                    return false;
                }
                else
                {
                    begin=false;
                    return HasSubtree(root1,root2);
                }
            }
            else
            {
                if(root2==null)
                    return true;
                else
                {
                    if(root1==null)
                        return false;
                    if(root1.val==root2.val)
                    {
                        if(newStart ==true)
                        {
                            root1Now=root1;
                            root2Root=root2;
                        }
                        newStart=false;
                        if(HasSubtree(root1.right,root2.right)&&HasSubtree(root1.left,root2.left))/*子树之间要都是对应的子节点*/
                            return true;
                        else
                        {
                            newStart=true;
                            root1=root1Now;
                            root2=root2Root;
                            return (HasSubtree(root1.right,root2)||HasSubtree(root1.left,root2));
                        }
                    }
                    else
                    {
                        if(newStart==false)/*判断是否开始了一次左右子树是否子结构的判断，如果开始了是否为左右
                    子结构的判断，但是又出现了不相等的情况，则应该直接返回false，然后从之前记录的位置重新
                    开始*/
                        {
                            return false;
                        }else return (HasSubtree(root1.right,root2)||HasSubtree(root1.left,root2));
                    }
                }
            }
        }

}
