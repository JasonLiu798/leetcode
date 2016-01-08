package com.jason.leetcode;

/**
 * Reverse Integer	2011-12-25	40.3%
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * Completed
 * @author liujianlong
 *
 */
public class Reverse {
	
	/**
	 * reverse Department Top Three Salaries.sql number
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {
		int tmp_now = 0;
		int tmp_prv =0;
		int res = 0;
		boolean fu = false;
		if (x < 0) {
			x = -x;
			fu = true;
		}
		if(x<10){
			return x;
		}
		res = x % 10;//calc first
		x = x / 10;
		while (x != 0) {
			tmp_now = x % 10;
			res = res*10 + tmp_now;
			x = x / 10;
		}
		if (fu)
			res = -res;
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int res = reverse(-1222343);
		 System.out.println(res);
	}

}
