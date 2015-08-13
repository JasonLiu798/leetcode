package com.jason.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 18.7%	Easy
 * Summary Ranges Total Accepted: 14787 Total Submissions: 79081
 Given a sorted integer array without duplicates, return the summary of its ranges.
 For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

AC Runtime: 236 ms
 */
public class SummaryRanges {
	public static boolean log = false;


	public List<String> summaryRanges1(int[] nums) {

		List<String> result = new ArrayList<String>();
		if(nums.length<1)
			return result;
		int start = nums[0];
		int end = 0;
		int i = 0;
		while(i<=nums.length-1){
			if(i+1 == nums.length || nums[i+1] != nums[i]+1 ){
				end = nums[i];
				if(end == start){
					result.add(Integer.toString(start));
				}else{
					String s = Integer.toString(start) + "->" + Integer.toString(end);
					result.add(s);
				}
				if(i <nums.length-1)
					start = nums[i+1];
			}
			i++;
		}
		return result;
	}

	/**
	 * look next
	 * @param nums
	 * @return
	 */
	public List<String> summaryRanges(int[] nums) {

		List<String> res = new LinkedList<>();
		if(nums.length<=0){
			return res;
		}else if(nums.length==1){
			res.add( String.valueOf(nums[0]) );
			return res;
		}else if(nums.length == 2){
			if(nums[1]-nums[0]==1){
				add(0,1,nums,res );
			}else{
				res.add(nums[0]+"");
				res.add(nums[1]+"");
			}
			return res;
		}
		int idx=0,next=1;
		int right = 0,left=0;
		boolean isIn = false;
		boolean isFirst = true;
		for( ; idx<nums.length; next++,idx++){
			if(log) {
				System.out.println("idx " + idx + ",next " + next + ",isIn " + isIn + ",left " + left + ",right " + right);
			}
			if (next<nums.length ) {
				if( nums[next] - nums[idx]== 1 ){
					if(isFirst){
						isFirst = false;
						left=idx;
						right = next;
						isIn = true;
					}else{
						if (isIn) {
							if(left==right ){//&& !isFirst ){
								res.add( String.valueOf(nums[left]) );
								left=idx;
								right=next;
							}else{
								right = next;
							}
						} else {
							isIn = true;
							right = next;
							left = idx;
						}
					}
				}else{
					if (isFirst) {
						isFirst = false;
						left = right = idx;
						isIn = true;
					}else{
						if (isIn) {
							if(!add(left,right,nums,res)){
								//single
								isIn = true;
								left = right = idx;
							}else{
								isIn = false;
								left = right = next;
							}
						} else {
							isIn = true;
							left = right = idx;
						}
					}
				}
			}else{
				if (isIn) {
					add(left, right, nums, res);
					if(right==idx){
						isIn=false;
					}else{
						left = right = idx;
						isIn = true;
					}
				}else{
					res.add(nums[idx]+"");
				}
			}
			if (isFirst)
				if (idx >= 1)
					isFirst = false;
		}
		if(log)
			System.out.println("---idx "+idx+",next "+next+",isIn "+isIn+",left "+left+",right "+right);
		if(isIn) {
			if (left != right) {
				res.add(rs(nums[left], nums[right]));
			} else {
				res.add(String.valueOf(nums[left]));
			}
		}
		return res;
	}

	public static boolean add(int lidx,int ridx,int[] nums,List<String> res){
		if (lidx == ridx) {
			res.add(String.valueOf(nums[lidx]));
			return false;
		} else {
			res.add(rs(nums[lidx], nums[ridx]));
			return true;
		}
	}

	public static String rs(int left,int right){
		return left+"->"+right;
	}
}
