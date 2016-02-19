#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 33. Search in Rotated Sorted Array
@Difficulty hard
@Pass 29.8%
@link https://leetcode.com/problems/search-in-rotated-sorted-array/
@introduction
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

@res
'''

import bisect

class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        idx=0
        for n in nums:
            if n==target:
                return idx
            idx+=1
        return -1

    #https://leetcode.com/discuss/80607/pretty-short-c-java-ruby-python
    def search2(self, nums, target):
        self.__getitem__ = lambda i: \
            (nums[0] <= target) ^ (nums[0] > nums[i]) ^ (target > nums[i])
        i = bisect.bisect_left(self, True, 0, len(nums))
        return i if target in nums[i:i+1] else -1

    #recu https://leetcode.com/discuss/52523/python-44ms-solution-binary-search-o-logn
    def search3(self, nums, target):
        if not nums:
            return -1
        return self.binarySearch(nums, target, 0, len(nums)-1)

    def binarySearch(self, nums, target, start, end):
        if end < start:
            return -1
        mid = (start+end)/2
        if nums[mid] == target:
            return mid
        if nums[start] <= target < nums[mid]: # left side is sorted and has target
            return self.binarySearch(nums, target, start, mid-1)
        elif nums[mid] < target <= nums[end]: # right side is sorted and has target
            return self.binarySearch(nums, target, mid+1, end)
        elif nums[mid] > nums[end]: # right side is pivoted
            return self.binarySearch(nums, target, mid+1, end)
        else: # left side is pivoted
            return self.binarySearch(nums, target, start, mid-1)

if __name__ == '__main__':
    s=Solution()
    res=s.
    print 'res',res



