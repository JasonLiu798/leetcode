package com.jason.leetcode;

import com.jason.tools.TreeNode;

public class BalancedBinaryTree {

	
	int i=0;
	
	
	
	public static boolean isBalanced(TreeNode root) {
	    if(root==null) return true;
	    int l=getDepth(root.left);
	    int r=getDepth(root.right);
	    return ((int)Math.abs(l-r)<2)&& isBalanced(root.left) && isBalanced(root.right);
	}
	
	public static int getDepth(TreeNode n){
		if(n==null) return 0;
	        return Math.max(getDepth(n.left),getDepth(n.right))+1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
