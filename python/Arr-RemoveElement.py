#!/bin/env python
#-*- coding:utf-8 -*-

'''
27.Remove Element
Easy
Given an array and a value, remove all instances of that value in place and return the new length.
The order of elements can be changed. It doesn't matter what you leave beyond the new length.
PS:same as No.26

'''

class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        if nums==None or len(nums)<1:
            return 0
        if len(nums)==1:
            return 0 if val==nums[0] else 1
        curIdx=0    #实际索引
        arrIdx=0    #不包含val正常数组 索引
        length=len(nums)
        # print 'list',nums,'len',length
        while curIdx<len(nums):
            # print 'cur',curIdx,'arrIdx',arrIdx,'len',length
            isPass=False
            if nums[curIdx]==val:
                length-=1
                isPass=True
            if not isPass:
                nums[arrIdx]=nums[curIdx]
                arrIdx+=1
                # print 'AFCP:',nums
            curIdx+=1
        return length

if __name__ == '__main__':
    s = Solution()
    #  0 1 2 3 4 5 6
    # l=[1,2,2,2,3,3,5]
    l=[1,2]
    s=Solution()
    res = s.removeElement(l,2)
    print res,l
