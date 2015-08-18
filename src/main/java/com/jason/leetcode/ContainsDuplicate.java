package com.jason.leetcode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

/**
 * Easy
 * ContainsDuplicate
 * https://leetcode.com/problems/contains-duplicate/
 * Runtime: 484 ms
 */
public class ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {
		if(nums==null || nums.length<=1){
			return false;
		}

		boolean res =false;
		HashMap s =  new HashMap<>();
		for(int i:nums){
			if(s.get(i)==null){
				s.put(i,i);
			}else{
				res = true;
				break;
			}
		}
		return res;
	}
}
