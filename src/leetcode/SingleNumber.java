package leetcode;

import tools.TreeNode;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Completed
 * @author Jason Liu
 * 
 */
public class SingleNumber {
	
	public static int singleNumber(int[] A) {
		int res = A[0];
		for (int i = 1; i < A.length; i++) {
			res = A[i] ^ res;
		}
		return res;
	}	
	public static void main(String[] args) {
		 int[] A = { 1, 2, 1, 3, 2 };
		 int res = singleNumber(A);
		 System.out.println(res);

	}

}
