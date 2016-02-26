#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 71. Simplify Path
@Difficulty Medium
@Pass 21.5%
@link https://leetcode.com/problems/simplify-path/
@introduction
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

@res
'''

class Solution(object):
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        if len(path)<=1:
            return path
        idx=0
        pathlist=[]
        while idx<len(path):
            if path[idx]=='/':
                L=idx
            else:
                while idx<len(path) and path[idx]!='/':#get next /
                    idx+=1
                R=idx
                # print L,R,pathlist
                if idx<len(path):
                    sepPath=path[L+1:R]
                    if path[R]=='/' :
                        # if len(sepPath)==1:
                        if sepPath!='.':
                            pathlist.append(sepPath)
                else:
                    if path[L+1:R]!='.':
                        pathlist.append(path[L+1:R])
                L=R
            idx+=1
        idx,res,stack=0,'',[]
        while idx<len(pathlist):
            if pathlist[idx]=='..':
                if len(stack)>0:
                    stack.pop()
            else:
                stack.append(pathlist[idx])
            idx+=1
        if len(stack)==0:
            res='/'
        else:
            res='/'+'/'.join(stack)
        return res

    #https://leetcode.com/discuss/66592/9-lines-of-python-code
    def simplifyPath2(self, path):
            places = [p for p in path.split("/") if p!="." and p!=""]
            print places
            stack = []
            for p in places:
                if p == "..":
                    if len(stack) > 0:
                        stack.pop()
                else:
                    stack.append(p)
            return "/" + "/".join(stack)

if __name__ == '__main__':
    s=Solution()
    string='/a/./b/../../c/'
    # string='///'
    res=s.simplifyPath(string)
    print 'res',res






