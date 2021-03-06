#!/bin/env python
#-*- coding:utf-8 -*-

'''
39. Combination Sum
https://leetcode.com/problems/combination-sum/
    29.8%
Difficulty: Medium
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3]
'''

class Solution(object):
    def combinationSum(self, candidates, target):
        def recur(candidates, target, res, pre,lv):
            lv+=1
            for i in xrange(len(candidates)):
                for lvcnt in range(lv):
                    print '-',
                print pre
                if target < candidates[i]:
                    break
                elif target == candidates[i]:
                    res.append(pre + [target])
                else:
                    recur(candidates[i:], target - candidates[i], res, pre + [candidates[i]],lv)
        res = []
        candidates.sort()
        recur(candidates, target, res, [],0)
        return res

    def combinationSum1(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        num=target
        sres=set([])
        self.bfs(candidates,target,[],sres)#,0)
        lres=[]
        for i in sres:
            lres.append(list(i))
        lres.sort()
        return lres
    def bfs(self,nums,tgt,tmplist,res):#,lv):
        # lv+=1
        i=0
        while i<len(nums):
            # for lvcnt in range(lv):
                # print '-',
            ntgt=tgt-nums[i]
            # print 'i',nums[i],'tgt',ntgt
            if ntgt>=0:
                tmplistn=list(tmplist)
                tmplistn.append(nums[i])
                if ntgt==0:
                    # print tmplistn
                    tmplistn.sort()
                    res.add(tuple(tmplistn))
                else:
                    self.bfs(nums,ntgt,tmplistn,res)#,lv)
            i+=1

    #none-recur
    def combinationSum(self, candidates, target):
        temp = []
        sums = 0
        i = 0
        candidates.sort()
        l = len(candidates)
        res = []
        while  i<l:
            if sums+candidates[i] < target:
                temp.append(i)
                sums += candidates[i]
            else:
                if sums+candidates[i] == target:
                    temp.append(i)
                    res.append([candidates[j] for j in temp])
                    temp.pop()
                if not temp:
                    break
                else:
                    i = temp.pop()
                    sums -= candidates[i]
                    i += 1
                    while i==l and temp:
                        i = temp.pop()
                        sums -= candidates[i]
                        i += 1
        return res

if __name__ == '__main__':
    s=Solution()
    # l=[2,3,6,7]
    l=[8,7,4,3]
    # t=7
    t=11
    res=s.combinationSum1(l,t)
    print res



