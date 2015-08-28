package com.jason.leetcode;

/**
 * Power of Two
 * https://leetcode.com/problems/power-of-two/
 * Given an integer, write indexA function to determine if it is indexA power of two.
 * Created by JasonLiu on 2015/8/7.
 */
public class PowerofTwo {
	public boolean isPowerOfTwo(int n) {
		boolean res = false;
		if (n == 1) {
			return true;
		}else if (n <= 0 || n % 2 == 1) {
			return false;
		} else {
			long st = 2;
			while (st < n) {
				st = st * 2;
			}
//			System.out.println(st);
			if (st == n)
				res = true;
		}
		return res;
	}
		/*
		boolean res = true;
		else{
			int st = n;
			while( st/2!=0 ){
				System.out.println(st+" mod "+st%2);
				if(st%2==1){
					res= false;
					break;
				}
//				st=st>>1;
//				st = Math.sqrt(st);
				System.out.println(st);
			}
		}*/



	public static void main(String[] args) {
	PowerofTwo p = new PowerofTwo();
		System.out.println(p.isPowerOfTwo(32));
}

}