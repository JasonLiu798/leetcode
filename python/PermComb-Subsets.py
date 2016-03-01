#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 78. Subsets
@Difficulty Medium
@Pass 30.7%
@link https://leetcode.com/problems/subsets/
@introduction
Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
@res
'''

import itertools
class Solution(object):
    #user lib,itertools
    def subsets1(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        res=[]
        res.append([])
        if len(nums)==0:
            return res
        else:
            for i in range(1,len(nums)+1):
                iter = itertools.combinations(nums,i)
                for item in iter:
                    res.append(sorted(list(item)))
        return res
    #recusive https://leetcode.com/discuss/89409/share-easy-to-understand-recursive-python-solution-ms-83-84%25
    def subsets(self, nums):
        if not nums:
            return [[]]
        head, tail = nums[0], nums[1:]
        smaller = self.subsets(tail)
        ret = []
        for item in smaller:
            ret.append(item)
            ret.append( sorted(item + [head]))
        return ret


if __name__ == '__main__':
    s=Solution()
    l=[1,2,3]
    res=s.subsets(l)
    print 'res',res




