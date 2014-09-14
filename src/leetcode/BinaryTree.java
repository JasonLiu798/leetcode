package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * P1:Binary Tree Preorder Traversal
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
	   1
	    \
	     2
	    /
	   3
 * return [1,2,3].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * @author Jason Liu
 *
 */
public class BinaryTree {

	public static void main(String[] args) {
		
	}

	private class TreeNode {
	    int val;
	    TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	}
	
	public List<Integer> L;
    public List<Integer> preorderTraversal(TreeNode root) {
        L= new ArrayList();
        preorder(root);
        return L;
    }
    
    public void preorder(TreeNode root){
        if(root!=null){
            L.add(root.val);
            if(root.left!=null){
                preorder(root.left);
            }
            if(root.right!=null){
                preorder(root.right);
            }
        }
        return;
    }
}
