#!/bin/env python
#-*- coding:utf-8 -*-

'''
20. Valid Parentheses
https://leetcode.com/problems/valid-parentheses/
'''

import myutil.List as List

class Solution(object):
    #p={"(":")",")":"(","[":"]","]":"[","{":"}","}":"{"}
    p={")":"(", "]":"[", "}":"{"}
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        if s==None or len(s)==0:
            return True
        if len(s)==1:
            return False
        stack=[]
        res = True
        for w in s:
            pw = self.p.get(w)
            # List.printList(stack)
            # print 'pw:',pw
            if pw==None:
                stack.append(w)
            else:
                # print 'len:',len(stack)
                if len(stack)>0:
                    sw=stack.pop()
                    # print 'sw',sw,'pw',pw
                    if sw!=pw:
                        res = False
                        break
                else:
                    res= False
                    break
        # print 'res',res
        # List.printList(stack)
        if len(stack)!=0:
            res=False
        return res


if __name__ == '__main__':
    s=Solution()
    # res = s.isValid('()[]{}')
    res = s.isValid('())')
    print res
