#!/bin/env python
'''
8. String to Integer (atoi)
easy
https://leetcode.com/problems/string-to-integer-atoi/
'''
import re
class Solution(object):
    negative='-'
    intMax=2147483647
    intMin=-2147483648
    def myAtoi(self, sstr):
        sstr = sstr.strip()
        reserv=False
        formatError=False
        if len(sstr)<=0:
            return 0
        res = 0
        m = re.search(r'[+|-]+',sstr)
        if m:
            sign=m.group()
            if len(sign)>1:
                formatError=True
            elif len(sign)==1:
                if sign==self.negative:
                    reserv=True
        if vars().has_key('sign'):
            sstr=sstr.replace(sign,'')
            print 'sign:',sign,'str:',sstr
        if len(sstr)>0:
            if not formatError:
                m = re.search(r'\d*',sstr)
                num=m.group()
                if len(num)>0:
                    res=int(num)
                if reserv:
                    res=-res
                res=max(min(res,self.intMax),self.intMin)
                #     res=-res
                # if res>self.intMax and (not reserv):
                #     res=self.intMax
                # if res>self.intMin and reserv:
                #     res=self.intMin
        return res

if __name__ == '__main__':
    s = Solution()
    res = s.myAtoi('-2147483648')
    print '-------',res,type(res)
    '''
    arr=['+123','-123','+','+-+23','++---232','    010','abc']
    for i in range(len(arr)):
        res = s.myAtoi(arr[i])
        print '-------',res,type(res)
    '''
    # res = s.myAtoi('+-+--+23')
    # print res

    # for i in range(10):
    # print '%d res: %s' % (i, res)
    '''
    def myAtoi1(self, sstr):
        """
        :type str: str
        :rtype: int
        """
        sstr = sstr.strip()
        if len(sstr)<=0:
            return 0
        res = 0
        pre = '+'
        reserv = False
        while len(sstr)>0 and (ord(sstr[0])<self.zero or ord(sstr[0]) >self.nine):
            # print sstr,',reserv:',reserv
            if ord(sstr[0]) == self.positive:
                if pre == '-':
                    pre='-'
                    reserv = True
                sstr = sstr[1:]
            elif ord(sstr[0]) == self.negative:
                if pre == '+':
                    pre='-'
                    reserv= True
                elif pre=='-':
                    pre='+'
                    reserv = False
                sstr = sstr[1:]
            else:
                sstr = sstr[1:]

        # print 'af:',sstr,',reserv:',reserv
        if len(sstr)>0:
            for i in range(len(sstr)):
                res=10*res+(ord(sstr[i])-self.zero)
        else:
            res=0
        if reserv:
            res=-res
        return res
    '''
