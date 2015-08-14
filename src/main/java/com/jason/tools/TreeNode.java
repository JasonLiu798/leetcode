package com.jason.tools;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	public int val;
	//public String val;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int x) {
		this.val = x;
//		left=null;
//		right=null;
	}

	public static TreeNode initSyTree(int i,boolean isSy){
		/**
		 * 	  1
			 / \
			 2   2
			 / \ / \
			 3  4 4  3
		 */
		TreeNode root = new TreeNode(1);
		if(isSy){
			//symmertric
			if(i==1){
				/*
				1
				2 2
				 */
				TreeNode l = new TreeNode(2);
				TreeNode r = new TreeNode(2);
				root.left=l;
				root.right = r;
			}else if(i==2){
				/**
				 * 1
				 * 2 2
				 * x 3 3 x
 				 */
				TreeNode l = new TreeNode(2);
				TreeNode r = new TreeNode(2);
				root.left=l;
				root.right = r;
				TreeNode lr = new TreeNode(3);
				TreeNode rl = new TreeNode(3);
				l.right = lr;
				r.left = rl;
			}else if(i==3){
				/*
				  1
				 / \
				 2   2
				 / \ / \
				 3  4 4  3
				 */
				TreeNode l = new TreeNode(2);
				TreeNode r = new TreeNode(2);
				root.left=l;
				root.right = r;
				TreeNode ll = new TreeNode(3);
				TreeNode lr = new TreeNode(4);
				l.left = ll;
				l.right =lr;
				TreeNode rl = new TreeNode(4);
				TreeNode rr = new TreeNode(3);
				r.left = rl;
				r.right = rr;
			}
		}else{
			/**
			 1
			 2
			 sy false
			 */
			if(i==0){
				TreeNode l = new TreeNode(2);
				root.left=l;
			}else if(i==1){
				/**
				 * [1,2,3,3,null,2,null]
				 * 1
				 * 2 3
				 * 3 x 2 x
				 * mid order 3 2 1 2 3
				 * af invert
				 * 1
				 * 3 2
				 * x 2 x 3
				 * mid order 3 2 1 2 3
				 */

			}




		}
		return root;
	}

	public static void printTree(TreeNode tr){
		Queue<TreeNode> q = new LinkedList<>();
		levelTraverse(tr,q);
	}

	public static void levelTraverse(TreeNode tr,Queue<TreeNode> q){
		System.out.println("BinaryTree");
		q.offer(tr);
		while(q.size()!=0){
			TreeNode tmp = q.poll();
			System.out.print(tmp.val + ",");
			if(tmp.left!=null){
				q.offer(tmp.left);
			}
			if(tmp.right!=null){
				q.offer(tmp.right);
			}
		}
		System.out.println();
	}


}
