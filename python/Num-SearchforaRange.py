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

    def BinarySearch(self,a, target):
        low = 0
        high = len(a) - 1
        '''
        在其它语言中,如果low + high的和大于Integer的最大值,比如2 ** 31 - 1,计算便会发生溢出,使它成为一个负数,然后被2除时结果仍为负数。在Java语言中,这个Bug导致一个ArrayIndexOutOfBoundsException异常被抛出,而在C语言中,你会得到一个无法预测的越界的数组下标。推荐的解决方法是修改中间值的计算过程,方法之一是用减法而不是加法——来实现：mid = low + ((high - low) / 2)；或者,如果你想炫耀一下自己掌握的移位运算的知识,可以使用更快的移位运算操作,在Python中是mid = (low + high) >> 1,Java中是int mid = (low + high) >>> 1
        '''
        while low <= high:
            mid = (low + high) // 2
            midVal = a[mid]
            if midVal < target:
                low = mid + 1
            elif midVal > target:
                high = mid - 1
            else:
                return mid
        return -1


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



