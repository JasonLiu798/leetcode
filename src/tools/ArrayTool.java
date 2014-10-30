package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArrayTool {

	public static void printHashMap(HashMap<Integer,ListNode> hm){
		System.out.print("HashMap:");
		//Map map = new HashMap();
		Iterator iter = hm.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			ListNode ln = (ListNode)entry.getValue();
			System.out.print("Key="+key+",Value="+ln.val+";");
		}
		System.out.println("");
	}

	public static void printHashSet(HashSet<Integer> set) {
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + ",");
		}
		System.out.println("");
	}

	public static void printList(List<Integer> l) {
		System.out.print("IntegerList:");
		Iterator<Integer> it = l.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + ",");
		}
		System.out.println("");
	}

	public static void printList(LinkedList<Integer> l) {
		System.out.print("IntegerList:");
		Iterator<Integer> it = l.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + ",");
		}
		System.out.println("");
	}

	public static void printArray(int[] array) {
		if (array == null) {
			System.out.println("Null");
		} else {
			for (int i : array) {
				System.out.print(i + ", ");
			}
			System.out.println("");
		}
	}

	public static void printArray(String[] array) {
		for (String i : array) {
			System.out.print(i + ", ");
		}
		System.out.println("");
	}

	public static int findMin(int[] arr, int biggerThan) {
		int tmp = 0;
		int idx = -1;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				tmp = arr[i];
			} else {
				if (arr[i] < tmp && arr[i] > biggerThan) {
					tmp = arr[i];
					idx = i;
				}
			}
		}
		return idx;
	}
}
