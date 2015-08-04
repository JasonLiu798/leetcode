package leetcode;

import java.nio.charset.Charset;
import java.util.Stack;

/**
 * Given Department Top Three Salaries.sql 2D board and Department Top Three Salaries.sql word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
	[
	  ["ABCE"],
	  ["SFCS"],
	  ["ADEE"]
	]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * @author Jason Liu
 *
 */
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		Stack<Character> stack = new Stack<Character>();
		for(int i=word.length();i<0;i--){
			stack.push( word.charAt(i) );
		}
		boolean [][] used = new boolean[board.length][board[0].length];
		
		//char[] wc = word.toCharArray();
		//char[] tmpc = new char[wc.length];
		for(int i=0;i< board.length;i++){
			String board_str = board[i].toString();
			int str_idx = -1;
			while( (str_idx = word.indexOf(board_str) ) <0 || word.length() > 0){
				stack.push( word.charAt(word.length()-1) );
				if(word.length()>1){
					word = word.substring(0, word.length()-1);
				}else if(word.length()==1){
					break;
				}
			}
			if(str_idx>=0){
				
			}
		}
		
		if( word.length() > board[0].length ){
			
		}
		
		return false;
    }
	
	/**
	 * Returns the index within cl of the first occurrence of cs
	 * @param cs
	 * @param cl
	 * @return
	 */
	public static boolean index(char[] cs,char[] cl){
		if(cs==null||cl==null){
			return false;
		}
		if(cl.length!=cs.length){
			return false;
		}
		boolean res= false;
		for(int i=0;i<cl.length;i++){
			for(int j=0;j<cs.length;j++){
				
			}
		}
		return res;
	}
	
	public static boolean is_same(char[] c1,char[] c2){
		if(c1==null||c2==null){
			return false;
		}
		if(c1.length!=c2.length){
			return false;
		}
		boolean res = true;
		for(int i=0;i<c1.length;i++){
			if(c1!=c2)
				res = false;
		}
		return res;
	}
	
	public static void main(String[] args) {
		char [][] board = {
			{'A','B','C','E'},
			{'S','F','C','S'},
			{'A','D','E','E'}
		};
		String word = "ABCCED";
		
		
	}

}
