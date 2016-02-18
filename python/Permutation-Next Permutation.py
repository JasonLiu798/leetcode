#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 31. Next Permutation
@Difficulty Medium
@Pass 26.0%
@link https://leetcode.com/problems/next-permutation/
@introduction
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

@res
'''
import itertools
class Solution(object):

    #https://leetcode.com/discuss/82270/python-neat-code-with-explanation
    def nextPermutation(self, nums):
        l=len(nums)
        if nums[::-1]==sorted(nums):#decrease seq
            nums[:]=nums[::-1]
        else:
            for i in reversed(xrange(l)):
                if nums[i]>nums[i-1]:
                     break
            for j in reversed(xrange(l)):
                if nums[j]>nums[i-1]:
                        nums[i-1],nums[j]=nums[j],nums[i-1]
                        nums[i:]=nums[:i-1:-1]
                        break
    '''
    find increase seq from end,change seq's last two element return
    if all decrease seq:
        sort(nums) return
    else:
        b / nums[max] \  [c,d]
        if [c,d] exist element nums[x] bigger than b
            exchange numx[max,x] to b
            sort last elements
        else
            exchange nums[max,]
            move nums[max] to b
            sort last elements
    '''
    def nextPermutation(self, nums):
        if nums==None or len(nums)<=1:
            return
        pre=len(nums)-2
        cur=len(nums)-1
        incrIdx=-1
        while pre>=0:
            if nums[pre]<nums[cur]:
                incrIdx=cur
                break
            cur=pre
            pre-=1
        # print 'incrIdx',incrIdx
        if incrIdx==len(nums)-1:#tail increase
            self.swap(nums,cur,cur-1)
            return
        if incrIdx==-1:
            nums.sort()
            return
        #get decrease's smallest but larger than nums[pre]
        tgt=nums[pre]
        idx=incrIdx+1
        got = -1
        while idx<len(nums):
            if nums[idx]>tgt:
                got=idx
            idx+=1
        # print 'got',got,'incrIdx',incrIdx
        if got>0:
            tmp=nums[got]
            # print nums
            nums[incrIdx:got+1]=nums[incrIdx-1:got-1+1]
            # self.arrMovBack(nums,incrIdx-1,got)
            nums[incrIdx-1]=tmp
            newarr=sorted(nums[incrIdx:])
            nums[incrIdx:]=newarr[:]
        else:
            tmp=nums[incrIdx-1]
            # self.arrMovFront(nums,incrIdx-1,len(nums)-1)
            nums[incrIdx-1:len(nums)-2+1]=nums[incrIdx:]
            nums[len(nums)-1]=tmp
            newarr=sorted(nums[incrIdx:])
            nums[incrIdx:]=newarr[:]
    def swap(self,nums,i,j):
        tmp=nums[i]
        nums[i]=nums[j]
        nums[j]=tmp
    def arrMovFront(self,arr,left,right):
        # print 'l',left,'r',right,arr
        idx=left
        while idx<right:
            arr[idx]=arr[idx+1]
            idx+=1
        # print arr
    def arrMovBack(self,arr,left,right):
        idx=right
        while idx>left:
            arr[idx]=arr[idx-1]
            idx-=1


    #TLE O(n!),get permutation,sort,get next num
    def nextPermutation1(self, nums):
        """
        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        if nums==None or len(nums)<=1:
            return
        pres=itertools.permutations(nums,len(nums))
        strnums=[]
        for i in pres:
            # print i
            strnums.append(''.join(map(str,i)))
        tgtnum=''.join(map(str,nums))
        idx=0
        strnums.sort()
        for n in strnums:
            if n==tgtnum:
                break
            idx+=1
        # print idx
        if idx<len(strnums)-1:
            i=0
            for n in strnums[idx+1]:
                nums[i]=int(n)
                i+=1
            return
        else:
            i=0
            for n in strnums[0]:
                nums[i]=int(n)
                i+=1
            return

if __name__ == '__main__':
    s=Solution()
    # l=[1,2,3]
    # l=[1,3,2] #213
    # l=[2,3,1] #312
    # l=[5,4,7,5,3,2]
    # l=[1,3,4,2]
    # l=[2,2,3,4,2,3,1,1,2]
    # l=[2,2,3,4,2,3,1,5,2]
    l=[4,2,0,2,3,2,0]
    print 'BF',l
    # s.arrMovBack(l,3,7)
    # s.arrMovFront(l,3,7)
    res=s.nextPermutation(l)
    print 'AF',l






