package leetcode;

import tools.TreeNode;
import java.util.ArrayList;
import java.util.List;

import tools.ArrayTool;


public class BinaryTree {

	

//	private class TreeNode {
//	    int val;
//	    TreeNode left;
//	    TreeNode right;
//	    TreeNode(int x) { val = x; }
//	}
//	
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
    
    public void inorderR(TreeNode root){
        if(root!=null){
            if(root.right!=null){
            	inorderR(root.right);
            }
            R.add(root.val);
            if(root.left!=null){
            	inorderR(root.left);
            }
        }
        return;
    }
    
    
    
    //public List<Integer> SL;
    public List<Integer> R;
    
    public boolean isSymmetric(TreeNode root) {
    	boolean res = false;
    	L= new ArrayList();
    	R= new ArrayList();
    	if( root.left==null && root.right==null){
    		res = true;
    	}else if( root.left == null || root.right == null){
    		res = false;
    	}else{
    		this.inorder(root.left);
    		this.inorderR(root.right);
    		if(R.size()!=L.size()){
    			res = false;
    		}else{
    			res = true;
//ArrayTool.printList(L);
//ArrayTool.printList(R);
    			for(int i=0;i<L.size();i++){
        			if(R.get(i)!=L.get(i)){
        				res = false;
        			}
        		}
    		}
    	}
    	return res;
    }
    
    
    
    public static void main(String[] args) {
		/**
				1
			   / \
			  2   2
			 / \ / \
			3  4 4  3
		 */
    	BinaryTree br = new BinaryTree();
    	TreeNode root = new TreeNode(1);
    	TreeNode l = new TreeNode(2);
    	TreeNode r = new TreeNode(2);
    	root.left = l;
    	root.right = r;
    	TreeNode ll = new TreeNode(3);
    	TreeNode lr = new TreeNode(4);
    	l.left = ll;
    	l.right =lr;
    	TreeNode rl = new TreeNode(4);
    	TreeNode rr = new TreeNode(3);
    	r.left = rl;
    	r.right = rr;
    	System.out.println("res:"+br.isSymmetric(root));
//    	ArrayTool.printList(br.L);
//    	ArrayTool.printList(br.R);

    	
	}
    
}
