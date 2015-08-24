package com.jason.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Easy
 * UglyNumber
 * https://leetcode.com/submissions/detail/37089817/
 * Runtime: 312 ms
 */
public class UglyNumber {

	public static boolean log = false;
	public boolean isUgly(int num) {
		if(num <=0){
			return false;
		}
		if(num ==1){
			return true;
		}
		long tnum = num;
//		if (num<0){
//			tnum = -tnum;
//		}
		System.out.println(tnum);
		boolean res = true;
		int i=0;
		List<Integer> l = new LinkedList<>();
		int[] arr = {2,3,5};
		while(i<arr.length ){
			int tmp = arr[i];
			if(log)
				System.out.println(tmp);
			int div = num%tmp;
			if(div==0){
				while(tnum%tmp==0){
					tnum /=tmp;
				}
			}
			if(log)
				System.out.println("after "+tmp+" div "+tnum);
			if(tnum == 1){
				break;
			}
			i++;
		}
		if(tnum>1){
			res = false;
		}

		return res;
	}




}
