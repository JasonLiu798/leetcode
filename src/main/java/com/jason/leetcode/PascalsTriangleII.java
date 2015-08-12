package com.jason.leetcode;

import com.jason.tools.CollectionTool;

import java.math.BigDecimal;
import java.util.*;

/**
 * easy
 * Pascal's Triangle II
 * https://leetcode.com/problems/pascals-triangle-ii/
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],k=3
 [1,4,6,4,1],k=4
 [1,5,10,10,5,1]k=5
 [1,6,15,20,15,6,1]k=6
 *
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 * Created by JasonLiu on 2015/8/7.
 * getRow AC Runtime: 344 ms
 * getRow AC Runtime: 256 ms
 */
public class PascalsTriangleII {

	List<Integer> getRow1(int rowIndex) {
		List<Integer> res = new ArrayList<>();
		if(rowIndex<=0){
			res.add(1);
			return res;
		}
		Integer[] arr = new Integer[rowIndex+1];
		int i, j;
		for(i=1, arr[0]=1; i<=rowIndex;++i) {
			if(arr[i]==null){
				arr[i] = 0;
			}
			for (j = i; j > 0; --j) {
				System.out.println("i "+i+",j "+j);
				arr[j] += arr[j - 1];
			}
		}
		return Arrays.asList(arr);
	}

	/**
	 * use fomular
	 * @param rowIndex
	 * @return
	 */
	public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<>();
		if(rowIndex<=0){
			res.add(1);
			return res;
		}
		int total = rowIndex+1;
		Integer [] arr = new Integer[total];
		for(int i=0;i<rowIndex/2+1;i++){
			if(i==0){
				arr[i]=arr[rowIndex]=1;
			}else{
//				System.out.println("i "+i+",rowindex "+rowIndex);
				int tmp = calcCnm(i,rowIndex);
				arr[i] = tmp;
				arr[rowIndex-i] = tmp;
//				System.out.println(i+","+arr[i]);
			}
		}
		res = Arrays.asList(arr);
		return res;
	}

	/**
	 * C r,n = n!/(r!*(n-r)!) = n*(n-1)*...(n-r+1)/r!
	 * 1,13 = 13!/(1*12!)
	 * 2,13 = 13!/(2*11!)
	 * @param r
	 * @param n
	 * @return
	 */
	public static int calcCnm(int r,int n){
//		System.out.println( factorial(13) );
//		System.out.println( factorial(12) );
		BigDecimal tmp= new BigDecimal(1);
//		long tmp = 1;//factorial(n)/(factorial(r)*factorial(n-r));
		// 9,18  18!/(9!*9!)
		// 18*...10/9!
		for(int i=n;i>= n-r+1;i--){
			BigDecimal tmpi = new BigDecimal(i);
			tmp = tmp.multiply(tmpi);
//			tmp *= i;
//			System.out.println(i+","+tmp);
		}
//		System.out.println("tmp " + tmp);
		BigDecimal tmpFac = new BigDecimal(factorial(r));
		tmp = tmp.divide(tmpFac);
//		System.out.println("tmp "+tmp);
//		int res = 	(int)tmp;
		int res = tmp.intValue();
//		System.out.println("res "+res);
		return res;
	}

	public static long factorial(int n){
		if(n==0){
			return 1;
		}
		long res = 1;
		for(int i=1;i<=n;i++){
			res *=i;
		}
		return res;
	}

	public static void main(String[] args) {
//		System.out.println(factorial(5));
//		System.out.println(calcCnm(1,4));
		PascalsTriangleII p = new PascalsTriangleII();
//k=13		[1,13,78,286,715,1287,1716,1716,1287,715,286,78,13,1]
//		System.out.println(calcCnm(2, 13));
//			[1,18,153,816,3060,8568,18564,31824,43758,1276, 43758,31824,18564,8568,3060,816,153,18,1]
//Expected:
//k=18		[1,18,153,816,3060,8568,18564,31824,43758,48620,43758,31824,18564,8568,3060,816,153,18,1]
//`	 		1,18,153,816,3060,8568,18564,31824,43758,48620,43758,31824,18564,8568,3060,816,153,18,1,
//		[1,30,435,4060,27405,142506,593775,2035800,5852925,14307150,30045015,54627300,86493225,119759850,-66175233,-54279,-66175233,119759850,86493225,54627300,30045015,14307150,5852925,2035800,593775,142506,27405,4060,435,30,1]
//		[1,30,435,4060,27405,142506,593775,2035800,5852925,14307150,30045015,54627300,86493225,119759850,145422675,155117520,145422675,119759850,86493225,54627300,30045015,14307150,5852925,2035800,593775,142506,27405,4060,435,30,1]

		List<Integer> l = p.getRow1(30);
		CollectionTool.printList(l);
//		System.out.println(factorial(9));
//		System.out.println(calcCnm(9,18));


	}

}
