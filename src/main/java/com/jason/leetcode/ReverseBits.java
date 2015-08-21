package com.jason.leetcode;

import com.jason.tools.CollectionTool;

import java.text.CollationElementIterator;

/**
 * Easy
 * Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 * Runtime: 328 ms
 */
public class ReverseBits {

	public static boolean log = false;
	public static void print(char[] a){
		CollectionTool.printArray(a);
	}

	public int reverseBits(int n) {
		if (n == 0) {
			return 0;
		}
		if( n == Integer.MIN_VALUE){
			return 1;
		}
		char[] arr = Integer.toBinaryString(n).toCharArray();
		char[] narr = null;
		if(arr.length<32){
			narr = new char[32];

			for(int i=narr.length-1,j=arr.length-1;i>=0;i--){

				if(i>=narr.length-arr.length){
//					System.out.println(arr[j]);
					narr[i] = arr[j];
					j--;
				}else{
					narr[i] = '0';
				}
			}
		}else{
			narr = arr;
		}
		if(log){
			System.out.println("narr");
			print(narr);
		}
		int b = 0;
		int start = narr.length-1;

//		if(narr[start]=='0'){
//			for(int i=narr.length-1;i>=0;i--){
//				if(narr[i]!='0'){
//					start = i;
//					break;
//				}
//			}
//		}
//		System.out.println(start);

		for (int i = start; i >=0 ;i--) {
			b=b<<1;
			if(narr[i]=='1'){
				b =b|0x1;
			}
			if(log) {
				System.out.println("b "+Integer.toBinaryString(b));
			}
		}
		return b;
	}


}
