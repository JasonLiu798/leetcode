#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 30. Substring with Concatenation of All Words
@Difficulty Hard
@Pass 20.4%
@link https://leetcode.com/problems/substring-with-concatenation-of-all-words/
@introduction
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

@res
1 permutations
2 merge range
3
'''

import re
import itertools

class Solution(object):
    '''
    1.generate all concatenation of words(permutations of words)
    2.find string in set
    Memory Limit Exceeded O(n!)
    '''
    def findSubstring1(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        list2=[]
        #https://docs.python.org/2/library/itertools.html?highlight=itertools.permutations#itertools.permutations
        stringList=list(itertools.permutations(words,len(words)))
        # print 'l2',stringList
        strings=[]
        for tp in stringList:
            string=''
            for st in tp:
                string+=st
            strings.append(string)
        # print 'strings',strings
        res=[]
        for searchStr in strings:
            idx=s.find(searchStr)
            if idx>=0:
                res.append(idx)
        res.sort()
        return res

    '''
    1.find all words index in dictA.
    2.loop word in words,merge range (word's index,word's length)
        adjacent range must
            front: Index(tgt)=index(r)-len(tgt)
            behind:Index(tgt)=index(r)+len(r)
    3.if every words are in the range,add array to result.
    don't realize the word's length is same
    def findSubstring2(self, s, words):
        res=[]
        strIdx={}
        idx=-1
        for w in words:
            strIdx[w]=[]
            idx=s.find(w)
            print s,w,idx
            while idx>=0:
                strIdx[w].append( (idx,idx+len(w)-1) )
                idx=s.find(w,idx+1)
        print '',strIdx
        for key in strIdx.keys():
            for irg in strIdx[key]:#init range
                tmpRg=irg
                print 'tmpRg',tmpRg
                for nKey in strIdx.keys():
                    if nKey!=key:
                        got = True
                        for trg in strIdx[nKey]:#tgt range
                            print 'irg',tmpRg,'trg',trg
                            if trg[1]+1==irg[0]:#front
                                tmpRg=(trg[0],irg[1])
                            elif trg[0]==irg[1]+1:#behind
                                tmpRg=(irg[0],trg[1])
                            else:
                                got=False
                                break
                        if got:
                            res.append(tmpRg[0])
        return res
    '''

    def findSubstring3(self, s, words):
        if not s or not words or not words[0]:
            return []
        n = len(s)
        k = len(words[0])
        t = len(words) * k
        req = {} #map word count
        for w in words:
            req[w] = req[w] + 1 if w in req else 1
        print 'req',req
        res = []
        print 'k',k,'n',n,'t',t,n - t + 1
        for i in xrange(min(k, n - t + 1)):
            print 'i',i
            self._findSubstring(i, i, n, k, t, s, req, res)
        return res
    def _findSubstring(self, l, r, n, k, t, s, req, res):
        curr = {}
        while r + k <= n:
            w = s[r:r + k]
            print 'w',w,'r',r
            r += k
            if w not in req:
                l = r
                curr.clear()
            else:
                curr[w] = curr[w] + 1 if w in curr else 1
                while curr[w] > req[w]:
                    print 's[l:l + k]',s[l:l + k]
                    curr[s[l:l + k]] -= 1
                    print 'curr[s[l:l + k]]',curr[s[l:l + k]]
                    l += k
                if r - l == t:
                    res.append(l)
if __name__ == '__main__':
    s=Solution()
    string="barfoobarfoomanthfoobarefoobamanzz"
    words=["foo", "bar","man"]
    res=s.findSubstring3(string,words)
    print res





