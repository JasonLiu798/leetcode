package leetcode;

public class SingleNumberII {

	/**
	 * https://oj.leetcode.com/discuss/857/constant-space-solution
	 * @param A
	 * @return
	 */
	public static int singleNumber(int[] A) {
		int len  = A.length;
		
		int ones=0;
		int twos=0;
		int threes=0;
		for(int i=0;i<len;i++){
	        twos = ones & A[i] | twos;
	        ones =  ones^A[i];
	        threes = ~(ones&twos);
	        ones = ones&threes;
	        twos = twos&threes;
	    }
		//ones = ones^A & ~(ones&twos)
		return ones;
    }
	
	public static void main(String[] args) {
		int a=5;//011
		int b=4;//100
		//a^b=111 7
		int xor = a^b;
		int xor1 = (~a&b)|(a&~b);
		// (~a&b)|(a&~b) = ~a|(a&~b) & b|(a&~b)
//		int xor2 =(~a|(a&~b)) & (b|(a&~b));
//		int xor3 = ( (~a|a) & (~a|~b) ) & ( (b|a) & (b|~b) ) ;
		
//		int c=a|b;
//		int d=a^b;
//		int e=a&b;
		
		//System.out.println( c );
//		System.out.println( xor&d );
//		System.out.println( xor&e );
////		System.out.println( xor1 );
//		System.out.println( xor2 );
//		System.out.println( xor3 );
		int[] A= {1,1,1,2,2,2,3,4,4,4};
		System.out.println(singleNumber(A));
		
		
	}

}
