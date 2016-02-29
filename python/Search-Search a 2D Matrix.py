#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 74. Search a 2D Matrix
@Difficulty Medium
@Pass 33.3%
@link https://leetcode.com/problems/search-a-2d-matrix/
@introduction
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
@res
'''

class Solution(object):
    #binary search
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        res=False
        if len(matrix)>0:
            heads=map(lambda x:(x[0],x[-1]),matrix)
        else:
            return False
        # print heads
        rres=self.binarySearchRange(heads,target)
        if rres==-1:
            return False
        else:
            bres=self.binarySearch(matrix[rres],target)
        if bres>=0:
            res=True
        return res
    def binarySearchRange(self,ranges,target):
        l=0
        r=len(ranges)-1
        res=-1
        m=(l+r)/2
        while l<=r :
            # print 'loop1',l,r
            m=(l+r)/2
            if ranges[m][0]<=target and target<=ranges[m][1]:
                res=m
                break
            elif target<ranges[m][0]:
                r=m-1
            else:
                l=m+1
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

    #too slow
    def searchMatrix1(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        res=False
        for i in matrix:
            for j in i:
                if j==target:
                    res=True
                    break
        return res


if __name__ == '__main__':
    s=Solution()
    #  0 1 2 3 4 5 6
    l=[1,2,3,4,5,6,7]
    # res=s.binarySearch(l,7)
    m=[
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    res=s.searchMatrix(m,5)
    print 'res',res



