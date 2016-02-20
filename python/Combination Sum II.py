#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 40. Combination Sum II
@Difficulty Medium
@Pass 26.9%
@link https://leetcode.com/problems/combination-sum-ii/
@introduction
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
Each number in C may only be used once in the combination.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8,
A solution set is:
[1, 7]
[1, 2, 5]
[2, 6]
[1, 1, 6]
@res
'''

import copy
idx=0
class Solution(object):
    #bfs
    def combinationSum21(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        candidates = filter(lambda x:x<=target,candidates)
        candidates.sort()
        tmpRes,now=set([]),[]
        self.bfs(candidates,now,target,tmpRes)
        res=[]
        for it in tmpRes:
            res.append(list(it))
        return res
    def bfs(self,pre,now,target,res):
        if pre:
            for i in range(len(pre)):
                # cpNow=copy.copy(now)
                newNow=now+[pre[i]]
                # cpNow.append(pre[i])
                if sum(newNow)>target:
                    return
                if sum(newNow)==target:
                    res.add(tuple(sorted(newNow)))
                    return
                cpPre=pre[0:i]+pre[i+1:len(pre)]
                self.bfs(cpPre,newNow,target,res)

    #dfs
    def combinationSum20(self, candidates, target):
        res = []
        candidates.sort()
        self.dfs(candidates, target, 0, [], res)
        return res
    def dfs(self, candidates, target, index, path, res):
        if target < 0:
            return  # backtracking
        if target == 0:
            res.append(path)
            return  # backtracking
        for i in xrange(index, len(candidates)):
            print target-candidates[i],i+1, path+[candidates[i]]
            if i > index and candidates[i] == candidates[i-1]:
                continue
            self.dfs(candidates, target-candidates[i], i+1, path+[candidates[i]], res)


    def combinationSum22(self, candidates, target):
        if not candidates:
            return []
        candidates.sort()
        res = []
        i = 0
        while i < len(candidates):
            if i >= 1 and candidates[i] == candidates[i-1]:
                i+=1
                continue
            if candidates[i] == target:
                res.extend([[candidates[i]]])
            elif candidates[i] > target:
                break
            else:
                print candidates[i+1:]
                res.extend([[candidates[i]]+l for l in self.combinationSum2(candidates[i+1:],target-candidates[i]) if l])
            i += 1
        return res

    #recu https://leetcode.com/discuss/61272/new-record-python-recursive-code-with-important-explanation
    def combinationSum23(self, cand, target):
        ans = []
        cand.sort()
        ans = self.combSumHelper(cand, target, 0)
        return ans
    def combSumHelper(self, cand, target, start):
        ans = []
        L = len(cand)
        i = start
        while i < L:
            curN = cand[i]
            newTarget = target-curN
            if newTarget > 0:
                result = self.combSumHelper(cand, newTarget, i+1)
                ans += [[curN]+ele for ele in result]
                i += 1
                while i<L and cand[i]==curN: #avoid duplicates
                    i += 1
            elif newTarget == 0:
                ans += [[curN]]
                return ans
            else:
                return ans
        return ans

    def combinationSum24(self, candidates, target):
        res=[]
        candidates.sort()
        self.cal(candidates, target, res, [])
        return res
    def cal(self, candidates, target, res, temp):
        if target==0:
            res+=[temp]
            return
        for i in xrange(len(candidates)):
            if candidates[i]<=target and (i==0 or candidates[i]!=candidates[i-1]):
                print candidates[i+1:],temp+[candidates[i]],target-candidates[i]
                self.cal(candidates[i+1:], target-candidates[i], res, temp+[candidates[i]])


if __name__ == '__main__':
    s=Solution()
    l=[10,1,2,7,6,1,5]
    # l=[18,6,18,18,11,30,18,33,24,18,17,15,29,8,18,30,28,34,10,28,20,34,5,15,18,28,34,7,10,8,33,23,31,16,29,7,33,12,27,23,18,9,25,34,34,30,29,27,21,26,21,6,25,21,16,30,6,15,32,11,10,23,30,29,26,24]
    tgt=8
    # tgt=28
    res=s.combinationSum21(l,tgt)
    print 'res',res





