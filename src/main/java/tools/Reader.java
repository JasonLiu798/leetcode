package tools;

import leetcode.LinkedListCycle;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JasonLiu on 2015/8/5.
 */
public class Reader {

	public static final int INITLEN = 1024;
	/**
	 * file format:
	 * int1,int2,int3,....,intn
	 * @param filepath
	 * @return
	 */
	public static Object[] File2intArr(String filepath){
//		RandomAccessFile raf = new

		FileInputStream fis = null;

		File f=new File(filepath);
		List<Integer> listint = new LinkedList<>();
		try {
			fis = new FileInputStream(filepath);
			int n = fis.read();
			int i=0;
			List<Integer> tmp = new LinkedList<>();
			while(n != -1){ //未到达流的末尾
				if( n != ','){
					n=n-'0';
					tmp.add(n);
				}else{//,
					listint.add(ByteUtil.byte2Int(tmp));
					tmp.clear();
				}
				i++;
				n = fis.read();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println(f.length());
//		int initlen = 0;
//		if(f.length()<INITLEN){
//			initlen = (int)f.length();
//		}
//		int res[] = new int[initlen];
		return listint.toArray();
	}

	public static void main(String[] args) {
		Object[] i= File2intArr("C:\\project\\leetcode-java\\src\\main\\resources\\ContainerWithMostWater.dat");

//		Object[] i= File2intArr("C:\\project\\leetcode-java\\src\\main\\resources\\a.dat");
		CollectionTool.printArray(i);
	}
}
