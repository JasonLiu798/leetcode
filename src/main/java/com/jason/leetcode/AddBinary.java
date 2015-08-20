package com.jason.leetcode;

/**
 * Easy
 * AddBinary
 * https://leetcode.com/problems/add-binary/
 * Runtime: 320 ms
 */
public class AddBinary {
	public static boolean log = false;

	/**
	 * cant't process lenght longer than 32,64
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary1(String a, String b) {

		int ia = Integer.valueOf(a,2);
		int ib = Integer.valueOf(b,2);
		int res = ia+ib;
		return Integer.toBinaryString(res);
	}


	public static String convert(String s){
		StringBuffer sb = new StringBuffer(s);
		return sb.reverse().toString();
	}

	public String addBinary(String a, String b) {
		if(log){
			System.out.println("a "+a+",b "+b);
		}
		a = convert(a);
		b = convert(b);
		char[] ca = a.toCharArray();
		char[] cb = b.toCharArray();
		char[] cr = new char[Math.max(ca.length,cb.length)+1];
		char[] longer = null;
		int len = -1;
		if(ca.length>cb.length){
			longer = ca;
			len = cb.length;
		}else if(ca.length<cb.length){
			longer = cb;
			len = ca.length;
		}
		if(len<0) {
			len = ca.length;
		}
		if(log) {
			System.out.println(len);
			System.out.println(String.valueOf(ca)+","+String.valueOf(cb));
		}
		boolean incr = false;

		for(int i=0;i<len;i++){
			if(ca[i]==cb[i] && ca[i]=='0'){
				if(incr)
					cr[i] = '1';
				else
					cr[i] = '0';
				incr = false;
			}else if(ca[i]==cb[i] && ca[i]=='1'){
				if(incr)
					cr[i] = '1';
				else
					cr[i] = '0';
				incr = true;
			}else if(ca[i]!=cb[i]){
				if(incr) {
					cr[i] = '0';
					incr = true;
				}else {
					cr[i] = '1';
					incr = false;
				}
			}
		}
		if(log) {
			System.out.println("after add,incr "+incr + String.valueOf(cr));
			String tmps = String.valueOf(cr);
			System.out.println("cv "+convert(tmps));
		}

		if(longer!=null){
			for(int i= len;i<longer.length;i++){
				if(longer[i]=='1'){
					if(incr) {
						cr[i] = '0';
						incr = true;
					}else {
						cr[i] = '1';
						incr = false;
					}
				}else if(longer[i]=='0'){
					if(incr)
						cr[i] = '1';
					else
						cr[i] = '0';
					incr = false;
				}
			}
		}

		if(log){
			System.out.println("after longer copy to cr");
			System.out.println(String.valueOf(cr));
		}
		if(incr) {
			cr[cr.length - 1] = '1';
		}else{
			cr[cr.length-1] = '0';
		}

		String res = String.valueOf(cr);

		res = convert(res);
		//remove start zeros
		while(res.startsWith("0")){
			res = res.substring(1);
		}
		if(res.length()==0){
			res ="0";
		}
		return res;
	}

}
