package com.jason.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Medium 20.3%
 * Add and Search Word - Data structure design
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * Runtime: 736 ms
 */
public class AddandSearchWordDatastructuredesign {
	public static boolean log = false;

	public static Trie t = null;

	public void WordDictionary(){
		t = new Trie();
	}

	public void addWord(String word) {
		t.insert(word);
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		boolean res = t.searchregx(word);
		return res;
	}


	/**
	 * trie node
	 */
	public static class TrieNode {
		public char val;
		public boolean isWord;
		public List<TrieNode> next;
		public TrieNode() {
			val = 0;
			isWord = false;
			next = new LinkedList<>();
		}

		@Override
		public String toString() {
			return "TrieNode{" +
					"val=" + val +
					", isWord=" + isWord +
					", next=" + next +
					'}';
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

	/**
	 * Trie tree
	 */
	public static class Trie {

		private static TrieNode root;
		public Trie() {
			searchres = false;
			root = new TrieNode();
		}

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

		private static boolean searchres = false;
		public boolean searchregx(String word){
			searchres = false;
			char[] arr = word.toCharArray();
			for( int i=0;i<this.root.next.size();i++){
				rsearch( this.root.next.get(i),arr,0);
			}
			boolean res = searchres;
			searchres =false;
			return res;
		}


		public static void rsearch( TrieNode t ,char[] arr,int i){
			if(i>arr.length-1){
				return;
			}
			if(log)
				System.out.println("i "+i+",t "+t+",arr["+i+"]"+arr[i]);
			if( t.val == arr[i] || arr[i] == '.'){
				if(i==arr.length-1){
					if(t.isWord)
						searchres = true;
					return;
				}
				if(t.next.size()!=0 && i<arr.length) {
					for (int k = 0; k < t.next.size(); k++) {
						rsearch(t.next.get(k), arr, i + 1);
					}
				}else{
					if(log)
						System.out.println("i "+i+",arr len "+(arr.length-1));
					if(t.isWord && i==arr.length-1){
						if(log)
							System.out.println("find");
						searchres = true;
					}
					return;
				}
			}else{
				return;
			}
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

		@Override
		public String toString() {
			TrieNode next = root;
			rdown(root);
			return "Trie";
		}

		public void rdown(TrieNode t){
			if(t.next!=null) {
				for (int i = 0; i < t.next.size(); i++) {
					TrieNode tt = t.next.get(i);
					System.out.println(tt);
					rdown(tt);
				}
			}
			return;
		}


	}


}
