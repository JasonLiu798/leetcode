package com.jason.leetcode;

import com.jason.tools.CollectionTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Medium
 * DifferentWaystoAddParentheses
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 *
 */
public class DifferentWaystoAddParentheses {
	public static List<Integer> digs = new ArrayList<>();
	public static List ops = new ArrayList();
	public static Stack<Integer> stack = new Stack<>();

	private static class Op{
		public Character op;
		public Integer left;
		public Integer right;

		@Override
		public String toString() {
			return "Op{" +
					"op=" + String.valueOf(op) +
					", left=" + left +
					", right=" + right +
					'}';
		}
	}

	public static boolean isDigit(char c){
		return c>='0'&&c<='9';
	}

	public static boolean isOp(char c){
		return c=='+'||c=='-'||c=='*';
	}

	public static int char2int(char c){
//		System.out.println(Integer.valueOf(c));
		return  c-'0';
	}

	public static void divideString(String str){
		char[] carr = str.toCharArray();
		int len = carr.length;
		for( int i=0; i< len-1; i++){
			char chari = carr[i];
			char nextc = carr[i+1];
			if( isDigit( chari)){
				stack.push( char2int(chari) );
			}else if(isOp(chari)){
				Op op = new Op();
				op.left = stack.peek();
				op.op = chari;
				op.right = char2int(nextc);
				ops.add(op);
			}
			if(i==len-2&& i!=0){
				stack.push( char2int(nextc));
			}
		}
		return;
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
	 *

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
*/

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
		divideString("1+2+3");
		CollectionTool.printStack(stack);
		CollectionTool.printList(ops);
//		System.out.println(('5'));
	}

}
