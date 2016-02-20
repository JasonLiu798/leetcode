#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 69. Sqrt(x)
@Difficulty Medium
@Pass 24.7%
@link https://leetcode.com/problems/sqrtx/
@introduction
Implement int sqrt(int x).
Compute and return the square root of x.

@res
'''

import math
class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x==0:
            return 0
        return int(math.sqrt( x ))


    def mySqrt1(self, k):
        '''
        Newton's method
        Newton's method finds the root of a function f(x) = 0.
        In this case f(x) = k - x^2, x > 0, where k is the given input and x is the variable we are trying to solve for.
        Take an initial guess of x, call it x_0.
        We can draw a tangent to the curve of the function y = f(x) at the point (x_0, f(x_0)).
        We can then find the intersection of this tangent with the x-axis.
        The intersection point can be solved as:
        (x_0 + (k - x_0^2)/(2 * x_0), 0)
        In general the intersection point is (x_0 - f(x_0) / f'(x_0), 0), where f'(x) is the derivative of f(x).

        We can then update our guess to x_1 = x_0 + (k - x_0^2)/(2 * x_0) and iterate the process until a tolerance is reached:
        '''
        #the sqrt of x should be less than (x+1)/2.0
        x = (k + 1)/4.0
        x2 = x * x
        diff = x2 - k
        e = 0.5
        while diff < -e or diff > e:
            x = x + (k - x2) / (2 * x)
            x2 = x * x
            diff = x2 - k
        return int(x)

if __name__ == '__main__':
    s=Solution()
    res=s.mySqrt(100)
    print 'res',res





