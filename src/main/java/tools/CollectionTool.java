package tools;

import java.io.File;
import java.util.*;

public class CollectionTool {

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

	public static void printList(List l) {
		System.out.print("List:");
		Iterator it = l.iterator();
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

	public static <T> void printArray(T[] array) {
		if (array == null) {
			System.out.println("Null");
		} else {
			for (T i : array) {
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


	public static ArrayList<File> getListFiles(Object obj) {
		File directory = null;
		if (obj instanceof File) {
			directory = (File) obj;
		} else {
			directory = new File(obj.toString());
		}
		ArrayList<File> files = new ArrayList<File>();
		if (directory.isFile()) {
			files.add(directory);
			return files;
		} else if (directory.isDirectory()) {
			File[] fileArr = directory.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				File fileOne = fileArr[i];
				files.addAll(getListFiles(fileOne));
			}
		}
		return files;
	}

	public static void printStack(Stack<Integer> stack ){
		if (stack.empty())
			System.out.println("Stack empty");
		else {
			System.out.print("Stack got "+stack.size() +" elements");
			Enumeration items = stack.elements(); // 得到 stack 中的枚举对象
			while (items.hasMoreElements()) //显示枚举（stack ） 中的所有元素
				System.out.print(items.nextElement()+" ");
		}
		System.out.println(); //换行
	}


}
