package com.jason.leetcode;

import com.jason.tools.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 257
 * Easy
 * Binary Tree Paths
 * https://leetcode.com/problems/binary-tree-paths/
 * Runtime: 308 ms
 */
public class BinaryTreePaths {

	public List<String> binaryTreePaths(TreeNode root) {
		res = new LinkedList<>();
		if(root==null){
			return res;
		}
		if(root.left==null&& root.right==null){
			res.add(root.val+"");
			return res;
		}


		firstTraverse(root,"");
		return res;
	}

	public static List<String> res;

	public void firstTraverse(TreeNode tr,String path){
		if(tr.left==null && tr.right == null){
			path+=tr.val;
			res.add(path);
			return;
		}else{
			path= path+tr.val+"->";
		}
		if(tr.left!=null)
			firstTraverse(tr.left,path);
		if(tr.right!=null)
			firstTraverse(tr.right,path);
	}




}
