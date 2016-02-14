package com.jason.leetcode;

import java.util.HashMap;

/**
 * Easy
 * ContainsDuplicateII
 * https://leetcode.com/problems/contains-duplicate-ii/
 * Runtime: 356 ms
 */
public class ContainsDuplicateII {

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		if(k<1){
			return false;
		}
		if(nums==null ){
			return false;
		}
		boolean res =false;
		HashMap s =  new HashMap<>();
		for(int i=0;i<nums.length;i++){
			Object idx = s.get(nums[i]);
			if(idx==null){
				s.put(nums[i],i);
			}else{
				int iidx = (int)idx;
				if(Math.abs(iidx-i)<=k){
					res = true;
					break;
				}else{
					s.put(nums[i],i);
				}
			}
		}
		return res;
	}

}
