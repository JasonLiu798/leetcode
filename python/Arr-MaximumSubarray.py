#!/bin/env python
#-*- coding:utf-8 -*-
'''
53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/
Difficulty: Medium
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

[Maximum subarray problem](https://en.wikipedia.org/wiki/Maximum_subarray_problem)
'''

class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        for i in xrange(1,len(nums)):
            nums[i]=max(nums[i], nums[i]+nums[i-1])
            print nums
        return max(nums)
    def maxSubArray1(self, nums):
        summ=[0]
        for i in range(0,len(nums)):
            summ.append(summ[-1]+nums[i])
            print summ
            maxv=-float('inf')
            maxd=-float('inf')
        for j in range(len(summ)-1,-1,-1):
            if maxv-summ[j]>maxd:
                maxd=maxv-summ[j]
            if summ[j]>maxv:
                maxv=summ[j]
        return maxd
    def maxSubArray2(self, nums):
        max_notcon = -999999999999
        max_con = nums[0]
        for i in range(1,len(nums)):
            max_notcon = max(max_notcon, max_con)
            max_con = max(nums[i], max_con+nums[i])
        return max(max_notcon,max_con)
if __name__ == '__main__':
    s=Solution()
    nums=[-2,1,-3,4,-1,2,1,-5,4]
    s.maxSubArray(nums)


