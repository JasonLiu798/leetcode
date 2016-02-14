#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 17. Letter Combinations of a Phone Number
@Difficulty Medium
@Pass   27.5%
@link https://leetcode.com/problems/letter-combinations-of-a-phone-number/
@introduction
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
1     2abc   3def
4ghi  5jkl   6mno
7pqrs 8tuv   9wxyz
*+    0space #
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

'''

class Solution(object):
    ht={"1":"","2":"abc","3":"def","4":"ghi","5":"jkl","6":"mno","7":"pqrs","8":"tuv","9":"wxyz"}
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        for d in digits:
            letters=self.ht.get(d)
            for in



if __name__ == '__main__':
    s=Solution()
    res=s.
    print res





