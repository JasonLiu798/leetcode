#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 10. Regular Expression Matching
@Difficulty Hard
@Pass 21.5%
@link https://leetcode.com/problems/regular-expression-matching/
@introduction
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

http://www.cnblogs.com/davidluo/articles/1806842.html

@res
'''

import re

class Solution(object):
    def isMatch(self, s, p):
        lenS, lenP = len(s), len(p)
        print 'len(s)',lenS,'len(p)',lenP
        dp = [[False] * (lenP + 1) for i in range(lenS + 1)]
        print dp
        # initialization, when p is empty, always Flase, when s is empty:
        dp[0][0] = True
        for j in range(2, lenP + 1):
            dp[0][j] = dp[0][j - 2] and p[j - 1] == '*'
        print dp
        # dp
        for i in range(1, lenS + 1):
            for j in range(1, lenP + 1):
                print dp[i]
                dp[i][j] = dp[i][j - 2] or (p[j - 2] in (s[i - 1], '.') and dp[i - 1][j]) if p[j - 1] == '*' \
                    else dp[i - 1][j - 1] and p[j - 1] in ('.', s[i - 1])
                print i,j,dp[i][j]
        return dp[lenS][lenP]

    def isMatch2(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        m, n = len(s), len(p)

        dp  = [True] + [False] * n
        print dp
        for j in xrange(1, n+1):
            dp[j] = p[j-1] == '*' and (j == 1 or dp[j-2])
        print dp
        for i in xrange(1, m+1):
            print i,'-------'
            pre, dp[0] = dp[0], False
            for j in xrange(1, n+1):
                tmp = dp[j]
                print 'j-1',j-1,p[j-1]
                if p[j-1] != '*':
                    dp[j] = pre and p[j-1] in [s[i-1], '.']
                else:
                    dp[j] = dp[j-1] or j > 1 and (dp[j-2] or (dp[j] and p[j-2] in [s[i-1], '.']))
                print 'dp j',dp[j]
                pre = tmp
        return dp[-1]


    def isMatch1(self, s, p):
        p+="$"
        try:
            if re.match(p,s):
                return True
            else:
                return False
        except Exception as e:
            return False


if __name__ == '__main__':
    s=Solution()
    res=s.isMatch("aaaaabbb", "a*b*")
    print 'res',res



