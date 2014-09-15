package leetcode;

import java.util.ArrayList;
import java.util.List;


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
    
    
    
    
    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
     * For example:
     * Given binary tree {1,#,2,3},
		   1
		    \
		     2
		    /
		   3
	 * return [3,2,1].
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        L= new ArrayList();
        postorder(root);
        return L;
    }
    
    public void postorder(TreeNode root){
        if(root!=null){
            
            if(root.left!=null){
                postorder(root.left);
            }
            if(root.right!=null){
                postorder(root.right);
            }
            L.add(root.val);
        }
        return;
    }
    
    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * For example.Given binary tree {1,#,2,3},
		   1
		    \
		     2
		    /
		   3
	 * return [1,3,2].
	 * Note: Recursive solution is trivial, could you do it iteratively?
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        L= new ArrayList();
        inorder(root);
        return L;
    }
    
    public void inorder(TreeNode root){
        if(root!=null){
            
            if(root.left!=null){
                inorder(root.left);
            }
            L.add(root.val);
            if(root.right!=null){
                inorder(root.right);
            }
        }
        return;
    }
    
}
