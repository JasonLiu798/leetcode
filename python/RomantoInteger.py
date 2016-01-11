#!/bin/env python
#-*- coding:utf-8 -*-

'''
13. Roman to Integer
https://leetcode.com/problems/roman-to-integer/
Input is guaranteed to be within the range from 1 to 3999.

1.相同数字连写 III
2.小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12
3.小的数字(限于I X C)在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9
4.在一个数的上面画一条横线，表示这个数增值 1,000 倍，如=5000

MMM     CM           XC      IX
3000 1000-100=900 100-10=90 10-1=9
DCC XC VIII
700 90 8
CM  XC IX
900 90 9
'''

class Solution(object):
    NUM={"I":1,'V':5,'X':10,'L':50,'C':100,'D':500,'M':1000}
    '''
    ONE='I'     #1
    FIVE='V'    #5
    TEN='X'     #10
    HALF_HUNDRED='L' #50
    HUNDRED='C' #100
    HALF_THOUSAND='D'#500
    THOUSAND='M'#1000
    '''
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s==None:
            return 0
        s=s.strip()
        if len(s)<=0:
            return 0
        if len(s)==1:
            return self.NUM.get(s)
        #length at least 2
        res=0   #result
        pre=self.NUM.get(s[0])
        now=0
        nxt=0
        idx=0
        length=len(s)
        for w in s:
            now=self.NUM.get(w)
            # print w,self.NUM.get(w)
            # print 'now',now,'pre',pre,'nxt',nxt
            #get next number
            if idx<length-1:
                nxt=self.NUM.get(s[idx+1])
            else:
                nxt=None
            if idx==0:
                if nxt<=now:
                    res+=now
            else:
                if now<=pre :
                    if nxt!=None :
                        if nxt<=now:
                            res+=now
                    else:
                        res+=now
                        break
                else:
                    res+=now
                    res-=pre
                pre=now
            idx+=1
            # print 'res',res
        return res

if __name__ == '__main__':
    s=Solution()
    # res=s.romanToInt('DCXXI')
    # res=s.romanToInt('DCCXCVIII')
    # res=s.romanToInt('MMMCDLVI')
    res=s.romanToInt('CMLII')# CM=900 LII=52
    print res

