#!/bin/env python
#-*- coding:utf-8 -*-

'''
4. Median of Two Sorted Arrays
hard
https://leetcode.com/problems/median-of-two-sorted-arrays/
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).


'''


def isEmpty(l):
    if l==None or len(l)==0:
        return True
    return False
def divide(n1,n2):
    return float(n1)/float(n2)
def getMid(l):
    if len(l)%2==1:
        return l[len(l)/2]
    else:
        return divide(l[len(l)/2]+l[len(l)/2-1],2)

class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        if isEmpty(nums1) and isEmpty(nums2):
            return None
        if not isEmpty(nums1) and isEmpty(nums2):
            return getMid(nums1)
        if isEmpty(nums1) and not isEmpty(nums2):
            return getMid(nums2)
        d=self.getDirection(nums1)
        l1=len(nums1)
        l2=len(nums2)
        isOdd= (l1+l2)%2==1
        if d==0:
            d=self.getDirection(nums2)
        if d==0:#所有元素都相等
            if isOdd:
                return nums1[0]
            else:
                return divide(nums1[0]+nums2[0],2)
        idx=0
        idx1=0
        idx2=0
        halflen=(l1+l2)/2+1
        lastAdd=0
        preaddwhich=0
        addwhich=0
        first=True
        # print 'd',d,'halflen',halflen
        while idx<halflen:
            # print 'idx',idx,'idx1',idx1,'idx2',idx2,'ad',addwhich,'preadd',preaddwhich
            if not first:
                preaddwhich=addwhich
            else:
                first=False
            addwhich=0
            if idx1<len(nums1) and idx2<len(nums2):
                if nums1[idx1]<=nums2[idx2]:
                    if d==1:
                        idx1+=1
                        addwhich=1
                    else:
                        idx2+=1
                        addwhich=2
                else:
                    if d==-1:
                        idx1+=1
                        addwhich=1
                    else:
                        idx2+=1
                        addwhich=2
            elif idx1>=len(nums1):
                idx2+=1
                addwhich=2
            elif idx2>=len(nums2):
                idx1+=1
                addwhich=1
            idx+=1
        # print 'isOdd',isOdd,'idx',idx,'idx1',idx1,'idx2',idx2,'preadd',preaddwhich,'addw',addwhich
        if idx1>len(nums1):
            if isOdd:
                return nums2[idx2]
            else:
                if preaddwhich==1:
                    return divide(nums1[idx1]+nums2[idx2],2)
                else:
                    return divide(nums2[idx2-1]+nums2[idx2],2)
        elif idx2>len(nums2):
            if isOdd:
                return nums1[idx1]
            else:
                if preaddwhich==2:
                    return divide(nums1[idx1]+nums2[idx2],2)
                else:
                    return divide(nums1[idx1-1]+nums1[idx1],2)
        else:
            if isOdd:
                if addwhich==1:
                    return nums1[idx1-1]
                else:
                    return nums2[idx2-1]
            else:
                if addwhich==preaddwhich and addwhich==1:
                    # print 'p=a=1',idx1,idx2
                    return divide(nums1[idx1-1]+nums1[idx1-2],2)
                elif addwhich==preaddwhich and addwhich==2:
                    # print 'p=a=2',idx1,idx2
                    return divide(nums2[idx2-1]+nums2[idx2-2],2)
                elif addwhich!=preaddwhich:
                    # print 'p!=a',idx1,idx2
                    return divide(nums1[idx1-1]+nums2[idx2-1],2)
    #increase or decrease
    def getDirection(self,l):
        '''
        l not null
        return
            0 unkonwn
            1 increase
            -1 decrease
        '''
        res = 0
        # filter same val
        idx=0
        if len(l)<=1:
            return res
        while idx+2<len(l) and l[idx]==l[idx+1]:
            idx+=1
        # print idx
        if l[idx+1] > l[idx]:
            res=1
        elif l[idx+1]<l[idx]:
            res=-1
        return res

if __name__ == '__main__':
    l2=[2,3]
    # l1=[1,3,4,5,6]
    # l1=[1]
    l1=[]
    s=Solution()
    print 'act----l1',l1,'l2',
    res=s.findMedianSortedArrays(l1,l2)
    l1.extend(l2)
    l1.sort()
    print 'l2',l2,'sort',l1
    expRes=0
    if len(l1) % 2 == 1:
        idx=len(l1)/2
        expRes=l1[idx]
        print 'hi',idx,'expRes',expRes
    else:
        i1=int(len(l1)/2)
        i2=int(len(l1)/2-1)
        expRes=divide(l1[i1]+l1[i2],2)
        print 'hi',i1,i2,'expRes',expRes
    print 'res',res
    # assert res==expRes







