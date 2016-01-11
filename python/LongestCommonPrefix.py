#!/bin/env python
#-*- coding:utf-8 -*-

'''
14. Longest Common Prefix
Total Accepted: 80902 Total Submissions: 300767 Difficulty: Easy
https://leetcode.com/problems/longest-common-prefix/

s1:loop compare
    read one str O(n)
    compare with others, n+(n-1)+...1 ~= O(n^2)
    total: O(n)*O(n^2)=O(n^3)
s2:trie tree
    add one str O(n)
    add all n*O(n) = O(n^2)
    find longest common = O(n)
    total: O(n^2)+O(n) ~= O(n^2)
'''

class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if strs == None or len(strs)==0:
            return ""
        if len(strs)==1:
            return strs[0]
        #at least got two string
        trie = Trie()
        got = False
        length = len(strs[0])
        for s in strs:
            if s=="":
                got = True
                res = ""
                break
            if len(s)<length:
                length = len(s)
            trie.insert(s)
        # print '------------'
        # trie.display()
        # print '------------'
        if not got:
            res=trie.findLongestCommon(length)
        return res

class Node:
    def __init__(self):
        self.value = None
        self.children = {}    # children is of type {char, Node}

class Trie:
    def __init__(self):
        self.root = Node()
    def insert(self, key):      # key is of type string
        # key should be a low-case string, this must be checked here!
        node = self.root
        for char in key:
            if char not in node.children:
                child = Node()
                node.children[char] = child
                node = child
            else:
                node = node.children[char]
        node.value = key

    def search(self, key):
        node = self.root
        for char in key:
            if char not in node.children:
                return None
            else:
                node = node.children[char]
        return node.value

    def display_node(self, node):
        if (node.value != None):
            print node.value
        for char in 'abcdefghijklmnopqrstuvwxyz':
            if char in node.children:
                self.display_node(node.children[char])
        return

    def display(self):
        self.display_node(self.root)

    def findLongestCommon(self,length):
        node = self.root
        res = ''
        cnt = 0
        while len(node.children) == 1 and cnt<length:
            for k in node.children.keys():
                node = node.children[k]
                res+=str(k)
            cnt+=1
        return res


if __name__ == '__main__':
    s = Solution()
    strs=['absdc','absccc','abscwer','absd']
    strs=['aa','a']
    res = s.longestCommonPrefix(strs)
    print res
    '''
    trie = Trie()
    trie.insert('hello')
    trie.insert('hesd')
    trie.insert('hertyrty')
    trie.insert('nice')
    trie.insert('to')
    trie.insert('meet')
    trie.insert('you')
    trie.display()
    print
    print trie.search('hello')
    print trie.search('HELLO')
    print
    print '================= END ====================='
    '''

    '''
    class Trie(object):
        def __init__(self):
            self.root  = Node() # Trie树root节点引用
        def add(self, word):
            #添加字符串
            node = self.root
            for c in word:
                pos = self.find(node, c)
            if pos < 0:
                node.childs.append(Node(c))
                #为了图简单，这里直接使用Python内置的sorted来排序
                #pos有问题，因为sort之后的pos会变掉,所以需要再次find来获取真实的pos
                #自定义单字符数组的排序方式可以实现任意规则的字符串数组的排序
                node.childs = sorted(node.childs, key=lambda child: child.c)
                pos = self.find(node, c)
                node = node.childs[pos]
                node.word = word
        def find(self, node, c):
            #查找字符插入的位置
            childs = node.childs
            _len   = len(childs)
            if _len == 0:
                return -1
            for i in range(_len):
                if childs[i].c == c:
                    return i
            return -1
    '''
