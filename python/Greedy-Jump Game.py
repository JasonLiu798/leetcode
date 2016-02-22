#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 55. Jump Game
@Difficulty Medium
@Pass
@link
@introduction
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

@res
'''

class Solution(object):
    '''
    Your runtime beats 96.01% of pythonsubmissions.
    merge sequential zero,Y=next nonzero position,find previe nums[X]>Y-X+1
    '''
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        if len(nums)<=0:
            return False
        if len(nums)==1:
            return True
        res=True
        idx=0
        while idx<len(nums):
            if nums[idx]==0:
                if idx-1<0:
                    res=False
                    break
                bfIdx=idx-1
                while idx<len(nums) and nums[idx]==0 :
                    idx+=1
                if idx==len(nums):
                    idx-=1
                cnt=idx-bfIdx
                while bfIdx>=0 and nums[bfIdx]<cnt:
                    bfIdx-=1
                    cnt+=1
                ncnt = idx-bfIdx
                # print 'cnt',cnt,'ncnt',ncnt,'idx',idx,'bfidx',bfIdx
                if bfIdx<0:
                    res=False
                    break
                if nums[bfIdx]<ncnt:
                    res=False
                    break
            idx+=1
        return res
    #O(n) https://leetcode.com/discuss/83812/5-lines-simple-python-solution-beat-85%25-python-submissions
    def canJump1(self, nums):
        farthest = nums[0]
        for i in range(len(nums)):
            if i <= farthest:
                farthest = max(i+nums[i], farthest)
        return len(nums)-1 <= farthest


if __name__ == '__main__':
    s=Solution()
    l=[2,3,1,1,4]
    l=[2,0,0]
    l=[3,0,8,2,0,0,1]
    l=[2,1,0,0]
    #  0 1 2 3 4 5 6 7 8 9 0 1
    l=[5,9,3,2,1,0,2,3,3,1,0,0]
    res=s.canJump(l)
    print 'res',res



