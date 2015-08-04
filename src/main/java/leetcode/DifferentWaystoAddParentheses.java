package leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JasonLiu on 2015/8/4.
 */
public class DifferentWaystoAddParentheses {

	private class TreeNode{
		public TreeNode left;
		public TreeNode right;
	}
	/**
	 *
	 123
	 132

	 213
	 231

	 321
	 312

	 321 *-*
	 (2*(3-(4*5))) = -34
	 132 **-
	 ((2*3)-(4*5)) = -14
	 213 -**
	 ((2*(3-4))*5) = -10
	 231 -**
	 (2*((3-4)*5)) = -10
	 123 *-*
	 (((2*3)-4)*5) = 10

	 * @param input
	 * @return
	 */

	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new LinkedList<>();
		List ops;
		List nums;
		divide(input,ops,nums);
		char op = getOp();
		int r = getOperR();
		int l = getOperL();
		int tmpRes = calc(r,l,op);


		return res;
	}

	public int calc(int r,int l,char op){
		int res = 0;
		switch( op ){
			case '+':
				res = r+l;
			case '-':
				res = r-l;
			case '*':
				res = r*l;
//			case '/':
//				res = r/l;
		}
		return res;
	}

	public static void main(String[] args) {

	}

}
