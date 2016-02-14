package com.jason.leetcode;

import com.jason.tools.TreeNode;

/**
 * Easy
 * Invert Binary Tree
 Invert indexA binary tree.
 https://leetcode.com/problems/invert-binary-tree/
 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 to
 4
 /   \
 7     2
 / \   / \
 9   6 3   1
AC Runtime: 296 ms

 */
public class InvertBinaryTree {


	public TreeNode invertTree(TreeNode root) {
		if(root==null){
			return null;
		}
		invert(root);
		return root;
	}

	public static void invert(TreeNode tr){
		TreeNode tmp = tr.left;
		tr.left = tr.right;
		tr.right = tmp;
		if(tr.left!=null){
			invert(tr.left);
		}
		if(tr.right!=null){
			invert(tr.right);
		}
	}




}
