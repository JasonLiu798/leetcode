#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 41. First Missing Positive
@Difficulty Hard
@Pass 23.5%
@link https://leetcode.com/problems/first-missing-positive/
@introduction
Given an unsorted integer array, find the first missing positive integer.
For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.
Your algorithm should run in O(n) time and uses constant space.

@res
'''

class Solution(object):
    '''
    use bitmap
    Your runtime beats 54.13% of pythonsubmissions.
    '''
    def firstMissingPositive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums)<=0:
            return 1
        idx,minn,maxn=0,float('inf'),0
        nums=filter(lambda x:x>=0,nums)
        for i in nums:
            if i>0:
                if i>maxn:
                    maxn=i
                if i<minn:
                    minn=i
        if maxn==0 or minn>1:
            return 1
        x=0
        for i in nums:
            if i>0:
                tmp=i-minn
                tmp1=1<<tmp
                x=x|tmp1
        got,bitcount=False,maxn-minn+1
        # print minn,maxn,'bitcount',bitcount,bin(x)
        for i in range(bitcount):
            tmp=1<<i
            tmp=x&tmp
            if tmp==0:
                got=True
                break
        if got:
            res=i+minn
        else:
            if minn>1:
                res=minn-1
            else:
                res=bitcount+1
        return res


    #bitmap solution,simple way https://leetcode.com/discuss/70262/python-bit-operation-solution-which-takes-space-passes-tests
    def firstMissingPositive1(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        from math import log
        ans = 0
        n = len(nums)
        for i in nums:
            if 0 < i <= n:
                ans |= 1 << i - 1
        return int(round(log(~ans & ans + 1, 2) + 1)) # find first zero
'''
000111011101
111000100011
000000000001
00100
'''
if __name__ == '__main__':
    s=Solution()

    l=[1,0,3]
    l=[3,4,-1,1]
    l=[0]
    l=[2,2]
    l=[1000,-1]
    l=[1,2,3]
    res=s.firstMissingPositive(l)
    print 'res',res



