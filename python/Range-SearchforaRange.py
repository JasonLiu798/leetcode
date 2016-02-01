#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 34. Search for a Range
@Difficulty Medium
@Pass   28.4%
@link https://leetcode.com/problems/search-for-a-range/
@introduction
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

'''

class Solution(object):
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        res=[-1,-1]
        if nums==None or len(nums)==0:
            return res
        got=self.binarySearch(nums,target)
        if got>=0:
            l=r=got
            # print 'before',l,r
            while l>=0:
                if nums[l]==target:
                    if l-1>=0:
                        l-=1
                    else:
                        break
                else:
                    l+=1
                    break
            # print 'l',l,'before r',r
            while r<len(nums):
                if nums[r]==target:
                    if r+1<len(nums):
                        r+=1
                    else:
                        break
                else:
                    r-=1
                    break
            # print 'r',r
            return [l,r]
        else:
            return res

    def binarySearch(self,nums,target):
        l=0
        r=len(nums)-1
        res=-1
        m=(l+r)/2
        while l<=r :
            # print 'loop1',l,r
            m=(l+r)/2
            if target==nums[m]:
                res=m
                break
            elif target<nums[m]:
                r=m-1
            else: #target>nums[m]
                l=m+1
        return res

if __name__ == '__main__':
    s=Solution()
    l=[4]
    # l=[4,4,4,4,4]
    # l=[1,4]
    # l=[4,5]
    tgt=4
    print 'l',l,'tgt',tgt
    res=s.searchRange(l,tgt)
    print res



