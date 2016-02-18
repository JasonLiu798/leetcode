#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 16. 3Sum Closest
@Difficulty Medium
@Pass 28.4%
@link https://leetcode.com/problems/3sum-closest/
@introduction
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

@res
'''

class Solution(object):
    #two pointer
    def threeSumClosest(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        nums.sort()
        res = sum(nums[:3])
        for i in xrange(len(nums)):
            l, r = i+1, len(nums)-1
            while l < r:
                s = nums[i]+ nums[l]+nums[r]
                if abs(s-target) < abs(res-target):
                    res = s
                if s < target:
                    l += 1
                elif s > target:
                    r -= 1
                else: # break early
                    return res
        return res


if __name__ == '__main__':
    s=Solution()
    l=[-1 ,2 ,1 ,-4]
    l=[0,1,2]
    l=[1,1,-1,-1,3]
    tgt=1
    tgt=0
    tgt=3
    res=s.threeSumClosest(l,tgt)
    print 'res',res







