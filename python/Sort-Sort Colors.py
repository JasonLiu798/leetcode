#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 75. Sort Colors
@Difficulty Medium
@Pass 34.1%
@link https://leetcode.com/problems/sort-colors/
@introduction
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
@res
桶排序

'''


class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        nums.sort()

    #https://leetcode.com/discuss/75221/python-one-pass-insert-0-at-the-beginning-and-2-at-the-end
    def sortColors1(self, nums):
        curr = 0
        for _ in range(len(nums)):
            if nums[curr] == 0:
                x = nums.pop(curr)
                nums.insert(0, x)
                curr += 1
            elif nums[curr] == 1:
                curr += 1
            else:
                x = nums.pop(curr)
                nums.append(x)

if __name__ == '__main__':
    s=Solution()
    l=[2,2,0,1,2,1,2,0]
    s.sortColors(l)
    print 'res',l









