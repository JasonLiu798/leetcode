#!/bin/env python
#-*- coding:utf-8 -*-
'''
@Questions 3. Longest Substring Without Repeating Characters
@Difficulty Medium
@Pass       21.3%
@link https://leetcode.com/problems/longest-substring-without-repeating-characters/
@introduction
Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
'''

class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        pwwkew
        p,w
        w,k,e
        """
        if len(s)<=0:
            return 0
        if len(s)==1:
            return 1
        words={}
        wlen=len(words)
        maxlen=0
        for i in range(len(s)):
            wi=words.get(s[i])
            if wi == None:
                words[s[i]]=i
            else:#got a same one
                if( len(words)>maxlen):
                    maxlen=len(words)
                if wi==i-1:
                    words={}
                    words[s[i]]=i
                else:
                    for k in words.keys():
                        if words[k] < wi:
                            del(words[k])
                        words[s[i]]=i
            # printDict(words)
            # print i
        if len(words) > maxlen:
            maxlen=len(words)
        return maxlen

    # def dropWordBeforeI(self, words, i):


def printDict(d):
    print "----dict start----"
    d.keys()
    for k in d.keys():
        print "key:%s,value:%s" % (k,d[k])
    print "----dict end----"

if __name__ == '__main__':
    s = Solution()

    # res = s.lengthOfLongestSubstring('pwwkew')
    # res = s.lengthOfLongestSubstring('dvdf')
    # res = s.lengthOfLongestSubstring('bbbbb')
    # res = s.lengthOfLongestSubstring('abcabcbb')
    # res = s.lengthOfLongestSubstring('c')
    # res = s.lengthOfLongestSubstring('ac')
    res = s.lengthOfLongestSubstring('bpfbhmipx') #expect7
    print 'res: %s' % res
