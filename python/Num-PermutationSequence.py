#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 60. Permutation Sequence
@Difficulty Medium
@Pass 24.4%
@link https://leetcode.com/problems/permutation-sequence/
@introduction
The set [1,2,3,…,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
Note: Given n will be between 1 and 9 inclusive.

@res
'''
import itertools
from math import factorial

class Solution(object):
    def getPermutation3(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        res = []
        nums = [i for i in xrange(1, n+1)]
        while n-1 >= 0:
            num, k = k/factorial(n-1), k % factorial(n-1)
            if k > 0:
                res.append(str(nums[num]))
                nums.remove(nums[num])
            else:
                res.append(str(nums[num-1]))
                nums.remove(nums[num-1])
            n -= 1
        return ''.join(res)

    #recu
    def getPermutation2(self, n, k):
        if n == 1:
            return '1'
        result = ''
        candidates = [str(num) for num in range(1,n+1)]
        result = self.myget(n,k,candidates,result)
        return result
    def myget(self, n,k,candidates,result):
        if n == 1:
            result += candidates[0]
            return result
        #fix the first number, how many permutations for that number
        rownum = math.factorial(n-1)
        #find the index for first number
        index = (k-1)/rownum
        #find the first number
        digit = candidates[index]
        result += digit
        #remove the first number
        candidates.remove(digit)
        #recursively do the following numbers
        result = self.myget(n-1,k%rownum, candidates, result)
        return result

    #TLE
    def getPermutation1(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        if n<=0:
            return ''
        res = list(itertools.permutations( range(1,n+1),n))
        if k<len(res):
            return list(res)[k]
        else:
            return ""


if __name__ == '__main__':
    s=Solution()
    # for i in range(8,):
    res=s.getPermutation(8,6593)
    print 'res',res



