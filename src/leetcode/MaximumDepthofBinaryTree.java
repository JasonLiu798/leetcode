package leetcode;

import tools.TreeNode;

/**
 * Maximum Depth of Binary Tree	2012-09-29	43.9%
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * 
 * Runtime Error Message:	Line 21: java.lang.NullPointerException
 * Last executed input:	{}
 * 
 * @author Jason Liu
 *
 */
public class MaximumDepthofBinaryTree {

	public static int maxlevel;

	public static int maxDepth(TreeNode root) {
		maxlevel = 0;
		maxDepthR(root,1);
		return maxlevel;
	}

	public static int maxDepthR(TreeNode root, int level) {
		if (root.left != null) {
			level += 1;
			if (level > maxlevel)
				maxlevel = level;
			maxDepthR(root.left, level);
		}
		if (root.right != null) {
			level += 1;
			if (level > maxlevel)
				maxlevel = level;
			maxDepthR(root.right, level);
		}
		return maxlevel;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode("root");
		root.left = new TreeNode("L");
		root.right = new TreeNode("R");
		root.left.right = new TreeNode("LR");
		root.left.right.left = new TreeNode("LRL");
		int res = maxDepthR(root, 1);

		System.out.println(res);
		
		/*
		 * int leve = 1; TreeNode root = new TreeNode("root"); root.left = new
		 * TreeNode("L"); root.right = new TreeNode("R"); root.left.right = new
		 * TreeNode("LR"); root.left.right.left = new TreeNode("LRL"); int res =
		 * maxDepthR(root,1);
		 */
	}

}
