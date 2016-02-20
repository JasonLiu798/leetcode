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
import itertools as its

class Solution(object):
    ht={"1":"","2":"abc","3":"def","4":"ghi","5":"jkl","6":"mno","7":"pqrs","8":"tuv","9":"wxyz"}
    #recusive
    def letterCombinations1(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        if digits==None:
            return []
        digits=filter(lambda x:x!='1',digits)
        # print 'after filter ',digits
        if len(digits)==0:
            return []
        wordlist=[]
        for d in digits:
            wordlist.append(self.ht.get(d))
        # print wordlist
        res=[]
        for l in wordlist[0]:
            self.bfs(1,l,wordlist,res)
        return res
    def bfs(self,lv,wd,wordlist,res):
        # print wd
        if lv<len(wordlist):
            for letter in wordlist[lv]:
                self.bfs(lv+1,wd+letter,wordlist,res)
        else:
            res.append(wd)
            return

    #use map,itertool
    def letterCombinations2(self, digits):
        g = map( lambda x: [' ','*','abc','def','ghi','jkl','mno','pqrs','tuv','wxyz'][int(x)], digits )
        return [''.join(i) for i in its.product(*g)] if digits else []


if __name__ == '__main__':
    s=Solution()
    res=s.letterCombinations1('2173')
    print res





