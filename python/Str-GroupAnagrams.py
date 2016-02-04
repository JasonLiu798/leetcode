#!/bin/env python
#-*- coding:utf-8 -*-
'''
@questions 49. Group Anagrams
@Difficulty Medium
@Pass   26.3%
@link https://leetcode.com/problems/anagrams/
@introduction
Given an array of strings, group anagrams together.
For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:
[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.

@res
'''

class Solution(object):
    #sort str in strs,then use hash
    def groupAnagrams3(self, strs):
        if len(strs)==0:
            return []
        d={}
        for s in strs:
            k="".join(sorted(s))
            print k
            if not d.has_key(k):
                d[k] = []
            d[k].append(s)
        result=[]
        for k,v in d.items():
            v.sort()
            result.append(v)
        return result
    #use defaultdict
    def groupAnagrams2(self, strs):
        from collections import defaultdict
        if not strs: return []
        dic = defaultdict(list)
        for str in strs:
            hash_key = 1
            for ch in str:
                hash_key *= hash(ch)
            dic[hash_key].append(str)
        for key in dic.keys():
            dic[key].sort()
        return dic.values()

    '''
    1.use ord get hash val,group to list
    2.process same hashval list, split to different list
    TLE
    '''
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        hv_str={}
        strs.sort()#reverse=False)
        for s in strs:
            hashval=0
            for ss in s:
                hashval+=ord(ss)
            if hv_str.get(hashval)==None:
                hv_str[hashval]=[s]
            else:
                hv_str[hashval].append(s)
        # print 'dict',hv_str
        res=[]
        for k in hv_str:
            words=hv_str[k]
            if len(words)>1:
                # print '0',words[0],'1:',words[1:]
                first=True
                wdif=[]
                while first or len(wdif)>0:
                    if first:
                        first=False
                    wdif,wsame=self.chkSame(words[0],words[1:])
                    # print 'same',wsame,'dif',wdif
                    words=wdif
                    if len(wsame)>0:
                        res.append(wsame)
            else:
                res.append(hv_str[k])
        return res
    def chkSame(self,w,tgtWords):
        # print 'in func '
        wlen=len(w)
        wsame=[]
        wdif=[]
        wsame.append(w)
        for tgw in tgtWords:
            tmpW=w
            if len(tmpW)!=len(tgw):
                wdif.append(tgw)
            sameCnt=0
            # print 'w',w,'tgw',tgw
            idx1=0
            tmpTgw=tgw
            while idx1<len(tmpW):
                idx2=0
                add = True
                while idx2<len(tgw):
                    if tgw[idx2]==tmpW[idx1]:
                        sameCnt+=1
                        tgw=self.removeNthLetter(tgw,idx2)
                        add=False
                        tmpW=self.removeNthLetter(tmpW,idx1)
                        break
                    else:
                        idx2+=1
                if add:
                    idx1+=1
            # print 'sameCnt',sameCnt,'lenw',wlen#,tgw
            if sameCnt!=wlen:
                wdif.append(tmpTgw)
                # print 'diff',wdif
            else:
                wsame.append(tmpTgw)
                # print 'same',wsame
        return wdif,wsame
    #for process same letter in word
    def removeNthLetter(self,w,idx):
        if idx==0:
            res=w[1:]
        elif idx==len(w)-1:
            res=w[0:len(w)-1]
        else:
            # print w[0:idx],#w[idx+1,len(w)]
            res=w[0:idx]+w[idx+1:len(w)]
        return res


if __name__ == '__main__':
    s=Solution()
    # dif,sam=s.chkSame('ape',['arc', 'dem', 'dem', 'oaf', 'oaf'])
    # print 'res',dif,sam
    # print s.removeNthLetter('abc',2)
    # l=["",""]
    # l=["and","dan"]
    l=["eat", "tea", "tan", "ate", "nat", "bat"]
    # l=["cab","tin","pew","duh","may","ill","buy","bar","max","doc"]
    # l=["ray","cod","abe","ned","arc","jar","owl","pop","paw","sky","yup","fed","jul","woo","ado","why","ben","mys","den","dem","fat","you","eon","sui","oct","asp","ago","lea","sow","hus","fee","yup","eve","red","flo","ids","tic","pup","hag","ito","zoo"]
    # l=["aaa","pup","pup"]
    # l=["hos","boo","nay","deb","wow","bop","bob","brr","hey","rye","eve","elf","pup","bum","iva","lyx","yap","ugh","hem","rod","aha","nam","gap","yea","doc","pen","job","dis","max","oho","jed","lye","ram","pup","qua","ugh","mir","nap","deb","hog","let","gym","bye","lon","aft","eel","sol","jab"]
    # l=["tho","tin","erg","end","pug","ton","alb","mes","job","ads","soy","toe","tap","sen","ape","led","rig","rig","con","wac","gog","zen","hay","lie","pay","kid","oaf","arc","hay","vet","sat","gap","hop","ben","gem","dem","pie","eco","cub","coy","pep","wot","wee"]
    print l
    res=s.groupAnagrams3(l)
    print 'res',res


