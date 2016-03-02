#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 77. Combinations
@Difficulty Medium
@Pass 33.5%
@link https://leetcode.com/problems/combinations/
@introduction
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
@res
'''

import itertools

class Solution(object):
    #use lib function
    def combine0(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        if n<=0:
            return []
        res = list(itertools.combinations( range(1,n+1),k))
        res = map(lambda x:list(x),res)
        return res
    #https://leetcode.com/discuss/79615/two-ways-in-python-recursive-and-dp
    def combine(self, n, k):
        if k==1:
            return [[i+1] for i in range(n)]
        return [r+[n] for r in self.combine(n-1, k-1)]+self.combine(n-1,k) if n-1>=k else [r+[n] for r in self.combine(n-1, k-1)]
    #dp https://leetcode.com/discuss/79615/two-ways-in-python-recursive-and-dp
    def combine2(self, n, k):
        base=[[[j] for j in xrange(1, i+1)] for i in xrange(1, n+1)]
        for height in xrange(k-1):
            newBase=[[]]
            for i in xrange(1, len(base)):
                newBase.append(newBase[-1]+[b+[height+i+1] for b in  base[i-1]])
            base=newBase[1:]
        return base[-1]

    def combinations(n, k, start=1):
        if k == 1:
            return [[x,] for x in xrange(start, n+1)]
        Result = []
        for FirstNum in xrange(start, n - k + 2):
            for Comb in combinations(n, k-1, FirstNum + 1):
                Result.append([FirstNum,] + Comb)
        return Result
    #backtracking https://leetcode.com/discuss/61607/ac-python-backtracking-iterative-solution-60-ms
    '''
    Combinations is typical application for backtracking. Two conditions for back track: (1) the stack length is already k (2) the current value is too large for the rest slots to fit in since we are using ascending order to make sure the uniqueness of each combination.
    '''
    def combine4(self, n, k):
        ans = []
        stack = []
        x = 1
        while True:
            print stack,ans
            l = len(stack)
            if l == k:
                ans.append(stack[:])
            if l == k or x > n - k + l + 1:
                if not stack:
                    return ans
                x = stack.pop() + 1
            else:
                stack.append(x)
                x += 1
if __name__ == '__main__':
    s=Solution()
    res=s.combine4(4,2)
    print 'res',res




