package com.jason.leetcode;

import com.jason.tools.CollectionTool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * N-Queens
 * https://leetcode.com/problems/n-queens/
 * AC Runtime: 300 ms
 */
public class NQueens {
	public static void printl(List<String> l){
		CollectionTool.printListEnter(l);
	}
	public static void printll(List<String> l){
		CollectionTool.printList(l);
	}

	public static boolean log = false;

	/**
	 * use char[][]
	 * @param n
	 * @return
	 */
	public List<List<String>> solveNQueens1(int n) {
		char[][] board = new char[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				board[i][j] = '.';
		List<List<String>> res = new ArrayList<List<String>>();
		dfs(board, 0, res);
		return res;
	}

	private void dfs(char[][] board, int colIndex, List<List<String>> res) {
		if(colIndex == board.length) {
			res.add( char2ListString(board) );
			return;
		}
		for(int i = 0; i < board.length; i++) {
			if(validate(board, i, colIndex)) {
				board[i][colIndex] = 'Q';
				dfs(board, colIndex + 1, res);
				board[i][colIndex] = '.';
			}
		}
	}

	private boolean validate(char[][] board, int x, int y) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < y; j++) {
				/**
				 * x==i,same row
				 * x+y == i+j,/ diagonal
				 * x+j == y+i,\ diagonal
				 */
				if(board[i][j] == 'Q' && ( x + j == y + i || x + y == i + j || x == i ))
					return false;
			}
		}
		return true;
	}

	private List<String> char2ListString(char[][] board) {
		List<String> res = new LinkedList<String>();
		for(int i = 0; i < board.length; i++) {
			String s = new String(board[i]);
			res.add(s);
		}
		return res;
	}


	/**
	 * easy understanding
	 * timeout
	 * @param n
	 * @return
	 */
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new LinkedList<>();
		if(n<=0){
			return res;
		}else if(n==1){
			List<String> l = new ArrayList<String>(1);
			l.add("Q");
			res.add(l);
			return res;
		}
		int half = n/2;
		for(int i=0;i<half;i++){
			for(int j=0;j<half;j++){
				nqueen(getNewBoard(n),i,j,0,res);
			}
		}
		return res;
	}

	/**
	 *
	 * @param l
	 */
	public void nqueen(List<String> l,int startI,int startJ,int cnt,List<List<String>> solutions){
		int n = l.size();
		if(n==cnt){
			if(!solutions.contains(l)) {
				solutions.add(l);
			}
			return;
		}
//		int lastI = -1,lastJ = -1;
		for(int i=startI;i<n;i++){
			for(int j=startJ;j<n;j++) {
				int res = canSetQ( l, i, j);
				if(res>0) {
					List<String> copy = getCopy(l);
					setNQ(copy, i, j);
//					lastI = i;
//					lastJ = j;
					nqueen(copy, 0, 0, cnt+1,solutions);
				} else if(res == -1){
					j=n;//jump one row
				}
//				else if(res == -3 || res == -4){
					/*
					if(lastI>=0&& lastJ>=0){
						List<String> copy = getCopy(l);
						unSetNQ(copy, lastI, lastJ);
//						nqueen(copy, lastI, lastJ, cnt-1 );
					}
//					setNQ(l,lastI,lastJ);
					*/
//				}
			}
		}

	}


	public static List<String> unSetNQ(List<String> l,int i,int j){
		String s = l.get(i);
		l.set( i, set( s , j,'.'));
		return l;
	}

	public static List<String> setNQ(List<String> l, int i,int j){
		String s = l.get(i);
		l.set( i, set( s , j,'Q'));
		return l;
	}

	/**
	 *
	 *
	 * @param l
	 * @param row
	 * @param col
	 * @return
	 */
	public static int canSetQ(List<String> l,int row,int col){ //2,0
		int size = l.size();
		int res = 1;
		for(int i=0;i<size;i++){
			char[] tmp = l.get(i).toCharArray();
			for(int j=0;j<size;j++){
				if(i==row){//same row
					if(tmp[j]=='Q') {
						res = -1;
						break;
					}
				}else if(j==col){//same col
					if(tmp[j]=='Q') {
						res = -2;
						break;
					}
				}else if( (i<row && j< col) || ( i>row && j>col) ){
					//left up,right down
					if(row-i == col-j){
						if(tmp[j]=='Q')	{
							res = -3;
							break;
						}
					}
				}else if( (i>row && j<col) || (i<row && j>col) ){
					//right up,left down
					if(row-i == j-col ){
						if(tmp[j]=='Q')	{
							res = -4;
							break;
						}
					}
				}
			}
			if(res<0)
				break;
		}
		return res;
	}


	public static String set(String s,int n,char c){
		char[] arr = s.toCharArray();
		arr[n] = c;
		return new String(arr);
	}

	public static List<String> getCopy(List<String> l){
		List<String> res = new ArrayList<>();
		for(int i=0;i<l.size();i++) {
			res.add(l.get(i));
		}
		return res;
	}

	public static  List<String> getNewBoard(int n){
		List<String> res = new ArrayList<>();
		String tmp = "";
		for (int j = 0; j < n; j++) {
			tmp += ".";
		}
		for(int i=0;i<n;i++) {
			res.add(tmp);
		}
		return res;
	}



	public static void main(String[] args) {
		List<String> l = getNewBoard(5);
//		setNQ(l, 2, 3);
//		printl(l);
//		System.out.println(canSetQ(l, 3, 4));
//
//		unSetNQ(l, 2, 3);
//		printl(l);
		NQueens n = new NQueens();
		List<List<String>> solutions = n.solveNQueens(5);
		System.out.println(solutions.size());
		for(int i=0;i<solutions.size();i++){
			printll(solutions.get(i));
		}
//		n.nqueen(l, 0, 0);
//		printl(n.solutions.get(0));

//		List<String> l1 = getCopy(l);
//		printl(l);
//		l.set(1, "222");
//		printl(l);
//		printl(l1);
//		l1.get(1) = "aaa";
//		l.get(1)

		/**
		 * Output:
		 [["Q....","..Q..","....Q",".Q...","...Q."],
		 [".Q...","...Q.","Q....","..Q..","....Q"],
		 ["..Q..","Q....","...Q.",".Q...","....Q"],
		 ["...Q.","Q....","..Q..","....Q",".Q..."],
		 ["...Q.",".Q...","....Q","..Q..","Q...."],
		 ["....Q",".Q...","...Q.","Q....","..Q.."]]
		 Expected:
		 [
1		 ["Q....","..Q..","....Q",".Q...","...Q."],
2		 ["Q....","...Q.",".Q...","....Q","..Q.."],
3		 [".Q...","...Q.","Q....","..Q..","....Q"],
4		 [".Q...","....Q","..Q..","Q....","...Q."],
5		 ["..Q..","Q....","...Q.",".Q...","....Q"],
6		 ["..Q..","....Q",".Q...","...Q.","Q...."],
7		 ["...Q.","Q....","..Q..","....Q",".Q..."],
8		 ["...Q.",".Q...","....Q","..Q..","Q...."],
9		 ["....Q",".Q...","...Q.","Q....","..Q.."],
10		 ["....Q","..Q..","Q....","...Q.",".Q..."]]
		 Submitted Code



		 */
	}
}
