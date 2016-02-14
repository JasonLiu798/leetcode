#!/bin/env python
#-*- coding:utf-8 -*-

'''
18.4Sum
Difficulty: Medium
https://leetcode.com/problems/4sum/
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.

s1(TLE):
sort O(nlogn)
add each two num=r1 O(n^2)
add each r1=target O(n^2)
process the each tuple
s2:

'''

from collections import namedtuple
Num = namedtuple('Num', [ 'idx','num'])
class Solution(object):
    def fourSum(self, nums, target):
        nums.sort()
        results = []
        self.findNsum(nums, target, 4, [], results)
        return results

    def findNsum(self, nums, target, N, result, results):
        if len(nums) < N or N < 2: return

        # solve 2-sum
        if N == 2:
            l,r = 0,len(nums)-1
            while l < r:
                if nums[l] + nums[r] == target:
                    results.append(result + [nums[l], nums[r]])
                    l += 1
                    r -= 1
                    while l < r and nums[l] == nums[l - 1]:
                        l += 1
                    while r > l and nums[r] == nums[r + 1]:
                        r -= 1
                elif nums[l] + nums[r] < target:
                    l += 1
                else:
                    r -= 1
        else:
            for i in range(0, len(nums)-N+1):   # careful about range
                if target < nums[i]*N or target > nums[-1]*N:  # take advantages of sorted list
                    break
                if i == 0 or i > 0 and nums[i-1] != nums[i]:  # recursively reduce N
                    self.findNsum(nums[i+1:], target-nums[i], N-1, result+[nums[i]], results)
        return

    #
    def fourSum2(self, nums, target):
        answer = []
        nums.sort()
        length = len(nums)
        for k in range(length-3):
            if nums[k]+nums[k+1]+nums[k+2]+nums[k+3] > target:
                    break
            for i in range(k+1,length-2):
                low = i+1
                high = length - 1
                while(low < high):
                    temp = nums[i]+nums[low]+nums[high]+nums[k]
                    if temp == target:
                        ans = [nums[i],nums[low],nums[high],nums[k]]
                        ans.sort()
                        low = low + 1
                        high = high - 1
                        if ans not in answer:
                            answer.append(ans)
                        while low < high and nums[high+1] == nums[high]:  ##speed up, jump the same value
                            high -= 1
                        while low < high and nums[low] == nums[low-1]:
                            low += 1
                    elif temp > target:
                        high = high -1
                    else:
                        low = low + 1
        return answer
    # Time Limit Exceeded
    def fourSum1(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        h={}
        nums=sorted(nums)
        #print 'nums',nums
        # add each two num,add to a dict
        for i in range(len(nums)):
            for j in range(i,len(nums)):
                if i!=j:
                    tmp1=Num(i,nums[i])
                    tmp2=Num(j,nums[j])
                    tmpSum=tmp1.num+tmp2.num
                    if tmpSum<=target:
                        if h.get(tmpSum) != None:
                            h[tmp1.num+tmp2.num].add((tmp1,tmp2))
                        else:
                            h[tmp1.num+tmp2.num]=set([(tmp1,tmp2)])
        #print 'hash',h
        # add each two num's result equal to target
        s=set([])
        for k1 in h.keys():
            for k2 in h.keys():
                if k1+k2 == target and (k1,k2) not in s and (k2,k1) not in s:
                    if k1!=k2:
                        s.add((k1,k2))
        #print 'set',s
        # accorad to the set ,get result
        tmpset=set([])
        for pair in s:
            for p1 in h[pair[0]]:
                for p2 in h[pair[1]]:
                    l=list(p1)
                    l.extend(list(p2))
                    tmpset2=set([])
                    for i in l:
                        tmpset2.add(i.idx)
                    if len(tmpset2)==4:
                        tmpl=[]
                        for i in l:
                            tmpl.append(i.num)
                        tmpset.add(tuple(sorted(tmpl)))
        res = []
        for t in tmpset:
            res.append(list(t))
        return sorted(res)
    def sameC(self,t1,t2):
        s1=set([])
        s2=set([])
        for t in t1:
            s1.add(t)
        for t in t2:
            s2.add(t)
        return s1==s2
if __name__ == '__main__':
    s=Solution()
    l=[1,2,3,1,2,3]
    l=[1,0,-1,0,-2,2]
    l=[-492,-465,-454,-450,-416,-403,-384,-378,-377,-368,-360,-341,-325,-322,-315,-310,-309,-284,-275,-274,-271,-264,-255,-248,-245,-232,-222,-212,-211,-204,-184,-137,-133,-128,-120,-117,-109,-92,-88,-61,19,19,32,37,39,55,60,94,98,187,187,216,254,272,284,284,290,295,323,328,336,411,428,440]
    l=[-492,-479,-468,-447,-432,-428,-418,-406,-388,-369,-300,-275,-238,-231,-228,-225,-224,-221,-220,-219,-189,-186,-180,-144,-130,-73,-67,-66,-55,-54,-53,-19,-6,13,28,36,47,57,80,82,87,97,97,120,132,142,148,174,176,176,205,225,232,238,245,247,264,268,268,275,319,334,387,392,412,413,426,434,442,451,475,478,485,490]
    # print 'Result:',s.fourSum(l,1154)
    print 'Result:',s.fourSum(l,4631)
#
