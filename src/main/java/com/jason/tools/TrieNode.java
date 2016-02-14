package com.jason.tools;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by JasonLiu on 2015/8/14.
 */
public class TrieNode {
	// Initialize your data structure here.
	public char val;
	public boolean isWord;
	public List<TrieNode> next;

	public TrieNode() {
		val = 0;
		isWord = false;
		next = new LinkedList<>();
	}

	public TrieNode(char val) {
		this.val = val;
		next = new LinkedList<>();
	}

	public void addChild(TrieNode t){
		next.add(t);
	}

	public boolean hasChild(){
		return this.next.size()!=0;
	}

	public TrieNode getChild(char c){
		if(this.next.size()==0){
			return null;
		}
		TrieNode res = null;
		for(TrieNode t:next){
			if(t.val == c)
				res = t;
		}
		return  res;
	}
}



