package com.jason.leetcode;

import com.jason.tools.GotLR;
import com.jason.tools.TreeNode;

import java.util.ArrayList;
import java.util.List;


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
	 * Given Department Top Three Salaries.sql binary tree, return the preorder traversal of its nodes' values.
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
     * Given Department Top Three Salaries.sql binary tree, return the postorder traversal of its nodes' values.
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
     * Given Department Top Three Salaries.sql binary tree, return the inorder traversal of its nodes' values.
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
//CollectionTool.printList(L);
//CollectionTool.printList(R);
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
    	/*
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
    	*/
//    	CollectionTool.printList(br.L);
//    	CollectionTool.printList(br.R);
    	TreeNode r1 = new TreeNode(1);
    	TreeNode r2 = new TreeNode(1);
    	
    	TreeNode r1l = new TreeNode(2);
    	TreeNode r1r = new TreeNode(3);
    	r1.left = r1l;
    	r1.right = r1r;
    	
    	TreeNode r2l = new TreeNode(2);
    	TreeNode r2r = new TreeNode(3);
    	r2.left = r2l;
    	r2.right = r2r;
    	
    	BinaryTree br = new BinaryTree();
    	System.out.println(br.isSameTree(r1,r2));
	}
    
    
    
    List<Integer> LI;
    List<GotLR> LT;
    
    public void preorderV(TreeNode root){
        if(root!=null){
            LI.add( root.val );
            LT.add( new GotLR(root.left!=null?true:false, root.right!=null?true:false) );
            if(root.left!=null){
            	preorderV(root.left);
            }
            if(root.right!=null){
            	preorderV(root.right);
            }
        }
        return;
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean res = false;
        if(p==null && q==null){
            res = true;
            return res;
        }
        if(p==null && q!=null){
            return res;
        }
        if(p!=null && q==null){
            return res;
        }
        if(p!=null && q!=null){
            LI=new ArrayList();
            LT=new ArrayList();
            this.preorderV(p);
            List<Integer> LI1=LI;
            List<GotLR> LT1=LT;
            
            LI=new ArrayList();
            LT=new ArrayList();
            this.preorderV(q);
            if( LI.size()!=LI1.size() || LT.size()!=LT1.size() ){
                return res;
            }
            res = true;
            for(int i=0;i<LI.size();i++){
                if( LI.get(i)!=LI1.get(i) || LT.get(i).gotL!=LT1.get(i).gotL || LT.get(i).gotR!=LT1.get(i).gotR ){
                    res = false;
                }
            }
            return res;
        }
        return res;
    }
    
    /**
     * 
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeR(TreeNode p, TreeNode q) {
        if (p==null || q==null) 
            return p==q;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
}
