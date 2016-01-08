package com.jason.leetcode;

/**
 * You are climbing Department Top Three Salaries.sql stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * @author Jason Liu
 * 45 / 45 test cases passed.
 * Status: Accepted
 * Runtime: 348 ms
 */
public class ClimbingStairs {

	
	public int climbStairs(int n) {
		int[] tmp = new int[n];
        if (n < 2){
            return 1;
        }
        tmp[0] = 1;
        tmp[1] = 2;
        for (int i = 2; i < n; i++){
            tmp[i] = tmp[i-1] + tmp[i-2];
        }
        return tmp[n-1];
    }
	
	//9 ,4*2+1，
//	public int addRange(int start,int end,int n){
//		return(start+end)*n/2;
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(5/2);
		ClimbingStairs cs = new ClimbingStairs();
//		cs.climbStairs(4);
//		cs.climbStairs(5);
//		cs.climbStairs(6);
		
		System.out.println(cs.climbStairs(7));;
		//System.out.print(cs.addRange(1, 100, 100-1+1));
	}

}
