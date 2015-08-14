package com.jason.tools;

public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		char[] arr = word.toCharArray();
		TrieNode next = this.root;
		for(int i=0;i<arr.length;i++){
			TrieNode child = next.getChild(arr[i]);
			if( child!=null){
				next = child;
			}else{
				TrieNode t = new TrieNode(arr[i]);
				next.addChild(t);
				next = t;
			}
		}
		next.isWord= true;
	}


	// Returns if the word is in the trie.
	public boolean search(String word) {
		boolean res = true;
		char[] arr = word.toCharArray();
		TrieNode next = this.root;
		for (int i=0;i<arr.length;i++){
			TrieNode child = next.getChild(arr[i]);
			if( child!=null){
				next = child;
			}else{
				res = false;
				break;
			}
		}
		if( res && !next.isWord)
			res = false;
		return  res;
	}


	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		boolean res = true;
		char[] arr = prefix.toCharArray();
		TrieNode next = this.root;
		for (int i=0;i<arr.length;i++){
			TrieNode child = next.getChild(arr[i]);
			if( child!=null){
				next = child;
			}else{
				res = false;
				break;
			}
		}
		return  res;
	}
}
