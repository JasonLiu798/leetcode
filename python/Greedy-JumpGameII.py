#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 45. Jump Game II
@Difficulty hard
@Pass 24.9%
@link https://leetcode.com/problems/jump-game-ii/
@introduction
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.
For example:
Given array A = [2,3,1,1,4]
The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
Note:
You can assume that you can always reach the last index.

@res
'''
log=True
# log=False

class Solution(object):
    def jump(self, nums):
        res = 0
        edge = 0
        maxEdge = 0
        for i in range(len(nums)):
            if i > edge:
                edge = maxEdge
                res += 1
            maxEdge = max(maxEdge,i+nums[i])
        return res
    '''
    def jump(self, nums):
        if len(nums)<=1:
            return 0
        idx=0
        jmpCnt=0
        while idx<=len(nums)-1:
            if idx==len(nums)-1:
                break
            step=nums[idx]
            if log: print 'step idx',idx,step
            nxtStepIdx=self.getMaxIdx(nums,idx+1,min(idx+step,len(nums)-1))
            idx=nxtStepIdx
            if log: print 'nxt step idx',nxtStepIdx,nums[nxtStepIdx],'idx',idx
            jmpCnt+=1
        return jmpCnt
    def getMaxIdx(self,nums,start,end):
        maxIdx=start
        for i in range(start+1,end+1):
            if nums[i] > nums[maxIdx]:
                maxIdx=i
        return maxIdx
    '''

if __name__ == '__main__':
    s=Solution()
    # l=[3,4,6,3,2]
    # l=[2,3,1,1,4]
    l=[1,1,1,1]
    # l=[1,1,1,1]
    # l=[2,1]
    # l=[1,2]
    print len(l)
    # res=s.jump(l)
    # print 'res',res





