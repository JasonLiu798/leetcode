#!/bin/env python
#-*- coding:utf-8 -*-

'''
26. Remove Duplicates from Sorted Array
easy
https://leetcode.com/problems/remove-duplicates-from-sorted-array/
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.
For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

#TLE,find duplicate nums,copy last all elements to front
def removeDuplicates(self, nums):
        if nums==None or len(nums)<1:
            return 0
        if len(nums)==1:
            return 1
        preIdx=-1
        arrIdx=0
        curIdx=0
        dupCnt=0
        length=len(nums)
        while curIdx<length:
            print 'cur',curIdx,'pre',preIdx
            if preIdx>=0:
                while curIdx<length and nums[curIdx]==nums[preIdx]:
                    curIdx+=1
                    dupCnt+=1
                print 'afterDUPcur',curIdx,'dup',dupCnt
                if dupCnt>0:
                    arrIdx=preIdx
                    tmp=curIdx
                    tmpCur=preIdx+1
                    while tmp<length:
                        nums[tmpCur]=nums[tmp]
                        tmpCur+=1
                        tmp+=1
                    length=length-dupCnt
                    dupCnt=0
                    curIdx=preIdx-1
            preIdx=curIdx
            curIdx+=1
        return length
'''

class Solution(object):
    #find duplicate nums,dont't copy
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if nums==None or len(nums)<1:
            return 0
        if len(nums)==1:
            return 1
        curIdx=0    #实际 索引
        arrIdx=0    #不重复数组 索引
        dupCnt=0    #重复个数
        length=len(nums)
        # print 'list',nums,'len',length
        while curIdx<len(nums):
            # print 'cur',curIdx,'arrIdx',arrIdx,'len',length
            while curIdx<len(nums) and nums[curIdx]==nums[arrIdx]:
                curIdx+=1
                if curIdx!=0:
                    dupCnt+=1
            # print 'AFDUP,cur',curIdx,'dup',dupCnt
            if dupCnt>0:
                if curIdx<len(nums):
                    nums[arrIdx+1]=nums[curIdx]
                length=length-dupCnt+1
                dupCnt=0
                curIdx=curIdx-1
                preIdx=curIdx
                # print 'AFCP','cur',curIdx,'arrIdx',arrIdx,nums
            else:
                nums[arrIdx]=nums[curIdx]
                print nums
            arrIdx+=1
            curIdx+=1
        return length

if __name__ == '__main__':
    s = Solution()
    #  0 1 2 3 4 5 6
    l=[1,2,2,2,3,3,5]
    # l=[1]
    # l=[1,2]
    # l=[1,1]
    # l=[1,1,1]
    # l=[1,1,2]

    '''
    #  0 1 2 3 4 5 6 7 8
    # l=[1,2,2,2,3,3,5]

    '''

    res=s.removeDuplicates(l)
    print 'result',res,l


