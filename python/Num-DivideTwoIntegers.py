#!/bin/env python
#-*- coding:utf-8 -*-

'''
29. Divide Two Integers
Difficulty: Medium
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

http://blog.csdn.net/blues1021/article/details/44077455
'''

class Solution(object):
    intMax=2147483647
    intMin=-2147483648
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        sign=True
        n1=dividend
        if dividend<0:
            n1=-dividend
            sign = not sign
        n2=divisor
        if divisor<0:
            n2=-divisor
            sign = not sign
        res=n1/n2
        if not sign:
            res = -res
        if res>self.intMax:
            res = self.intMax
        if res<self.intMin:
            res = self.intMin
        return res

if __name__ == '__main__':
    s = Solution()
    # print len(str(s.intMax)),len(str(s.intMin))
    t=(-2147483648,-1)
    t=(1004958205,-2137325331)
    # print len(str(t[0])),len(str(t[1]))
    res=s.divide(t[0],t[1])
    print res








