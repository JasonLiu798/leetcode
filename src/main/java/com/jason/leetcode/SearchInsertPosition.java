package com.jason.leetcode;

/**
 * Given Department Top Three Salaries.sql sorted array and Department Top Three Salaries.sql target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * @author Jason Liu
 * 62 / 62 test cases passed. 
 * Status: Accepted 
 * Runtime: 416 ms
 */
public class SearchInsertPosition {

	public int searchInsert(int[] A, int target) {
		int idx = -1;
        for(int i=0;i<A.length;i++){
        	if(target==A[i]){
        		return i;
        	}
        	/*else if(target <A[i+1] && target>A[i]){
        		
        	}*/else if(target <A[i]){
        		idx=i;
        		return idx;
        	}
        }
        if(idx==-1){
        	idx=A.length;
        }
		return idx;
    }
	public static void main(String[] args) {
		int[] a = {1,3,5,6};
		int target= 5;
		System.out.println(new SearchInsertPosition().searchInsert(a, target));
		int[] b = {1,3,5,6};
		target = 2;
		System.out.println(new SearchInsertPosition().searchInsert(b, target));
		int[] c = {1,3,5,6};
		target = 7;
		System.out.println(new SearchInsertPosition().searchInsert(c, target));
		int[] d = {1,3,5,6};
		target = 0;
		System.out.println(new SearchInsertPosition().searchInsert(d, target));
		/* 
		 * [], 2 → 1
		 * [], 7 → 4
		 * [], 0 → 0
		 */
	}

}
