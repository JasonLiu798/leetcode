package tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayTool {
	
	public static void printList(List<Integer> l){
		System.out.print("IntegerList:");
		Iterator<Integer> it = l.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+",");
		}
		System.out.println("");
	}
	
	public static void printList(LinkedList<Integer> l){
		System.out.print("IntegerList:");
		Iterator<Integer> it = l.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+",");
		}
		System.out.println("");
	}
	public static void printArray(int[] array){
		for(int i : array){
		   System.out.print(i + ", ");
		}
		System.out.println("");
	}
	
	public static void printArray(String[] array){
		for(String i : array){
		   System.out.print(i + ", ");
		}
		System.out.println("");
	}
	
	public static int findMin(int[] arr,int biggerThan){
		int tmp=0;
		int idx = -1;
		for (int i = 0; i < arr.length; i++){  
			if(i == 0){   
				tmp = arr[i];  
			}else{
				if(arr[i] < tmp && arr[i]>biggerThan){
					tmp = arr[i];
					idx=i;
				}
			}
		}
		return idx;
	}
}
