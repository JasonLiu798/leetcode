#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 32. Longest Valid Parentheses
@Difficulty Hard
@Pass 22.1%
@link https://leetcode.com/problems/longest-valid-parentheses/
@introduction
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
@res
'''

def peek(l):
    return l[len(l)-1]
class Solution(object):

    '''
    scan use stack get valid pair
    O(n)+O(nlogn)+O(n)
    '''
    def longestValidParentheses1(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s==None or len(s)<=1:
            return 0
        stack,valid,idx,maxValidLen=[],{},0,0
        for letter in s:
            if len(stack)==0:
                stack.append( (idx,letter) )
            else:
                pk=peek(stack)
                if pk[1]!=letter and pk[1]=='(':
                    tmp=stack.pop()
                    valid[tmp[0]]=tmp[1]
                    valid[idx]=letter
                else:
                    stack.append( (idx,letter))
            idx+=1
        # check is sequence
        keys=sorted(valid.keys())
        pre,cur,seq,maxSeq=0,1,0,0
        while cur<len(keys):
            if keys[cur] - keys[pre]==1:
                if seq==0:
                    seq+=2
                else:
                    seq+=1
            else:
                maxSeq=max(maxSeq,seq)
                seq=0
            pre=cur
            cur+=1
        maxSeq=max(maxSeq,seq)
        return maxSeq

    '''
    https://leetcode.com/discuss/60730/my-ten-lines-python-solution
    let dp[i] is the number of longest valid Parentheses ended with the i - 1 position of s, then we have the following relationship:
    dp[i + 1] = dp[p] + i - p + 1
    where p is the position of '(' which can matches current ')' in the stack.
    '''
    def longestValidParentheses2(self, s):
        dp, stack = [0]*(len(s) + 1), []
        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            else:
                if stack:
                    p = stack.pop()
                    dp[i + 1] = dp[p] + i - p + 1
            print i,dp,stack
        return max(dp)

    '''
    https://leetcode.com/discuss/66266/python-solution-starting-index-stack-detailed-explaination
    stk store the index of '('.
    we intereate through the string.
    result is the length of the longest unbroken bracket chain at that position, we update it when we iterate through the string. We initiate it to 0.
    lst is -1 or the starting index of the unbroken bracket chain we just extended with a matching ')', its initial value is -1, if we iterate to a '(' or a umatchable ')', we set lst to -1. So we are using lst for two purposes.
    Every time we see a '(', we push a index to the stack. if the lst is not -1 (means last time we just got a matching ')' and extended the current unbroken bracket chain and we set lst to the starting index of that chain), we push lst into the stack. If lst is -1, it means we are on index 0 or last character is not a match ')'. We push the index i into the stack. We set lst to -1 in either case.
    Every time we see a ')' and the stack is not empty ( means it's a matching ')' ),we pop the stack. we calculate the length of the chain and update the result and set the lst to the popped index. If the stack is empty, it means that's a unmatchable ')', and we set lst to -1.
    A lot of times, we pop a index and push it right back like when we iterate to index 2 of string "()()" In the end of the interation we will have the length of the longest chain in the result variable

    Let me know if the explaination doesn't make sense or hard to understand. I will try my best to revise it.
    '''
    def longestValidParentheses3(self, s):
        """
        :type s: str
        :rtype: int
        """
        result=0
        stk=[]
        lst=-1
        for i in xrange(len(s)):
            if s[i]=='(':
                if lst!=-1:
                    stk.append(lst)
                    lst=-1
                else:
                    stk.append(i)
            else:
                if stk:
                    stt=stk.pop()
                    if i-stt+1>result:
                        result=i-stt+1
                    lst=stt
                else:
                    lst=-1
            print stk
        return result

    #https://leetcode.com/discuss/76707/o-1-space-python-solution-no-stack
    def longestValidParentheses4(self, s):
        return max(self.helper(s,'('), self.helper(s[::-1],')'))
    def helper(self, s, left):
        ans = 0
        maxendinghere = 0
        count = 0
        for c in s:
            if c == left:    #when scan s from left to right, left is '(', otherwise is ')'
                count += 1
                maxendinghere += 1
            else:
                count -= 1
                if count <0:
                    maxendinghere = 0
                    count = 0
                else:
                    maxendinghere += 1
                    if count == 0:
                        ans = max(ans, maxendinghere)
        return ans
    '''
    https://leetcode.com/discuss/85971/python-code-even-odd-check
    Odd number means the pair is open to be finished. Even number means it is finished.
    Number itself is the length of open or closed pairs.
    Additional ")" means previous pairs are permanently closed so start a new pair with lengh 0.
    Return max element in stack if it is even, or max - 1 if odd.
    '''
    def longestValidParentheses5(self, s):
        stack = [0]
        for x in s:
            if x == '(':
                stack.append(1)
            elif stack[-1] & 1 == 1:
                a = stack.pop()
                stack[-1] += a + 1
            else:
                stack.append(0)
            print stack
        return max(i for i in stack)//2 * 2



if __name__ == '__main__':
    s=Solution()
    string='()()'
    string=')()())'
    string=")("
    string="()(()"
    string="()((())"
    print string
    # string="(((((((()"
    res=s.longestValidParentheses2(string)
    print 'res',res





