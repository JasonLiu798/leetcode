package com.jason.leetcode; 

import com.jason.tools.CollectionTool;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.List;

/** 
* Sum3 Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 28, 2015</pre> 
* @version 1.0 
*/ 
public class Sum3Test { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: threeSum(int[] nums) 
* 
*/
public static Sum3 s = new Sum3();
@Test
public void testThreeSum() throws Exception {
    int [] a = {-1 ,0, 1, 2, -1, -4};
    List<List<Integer>> res = s.threeSum(a);
    CollectionTool.printList(res);

}


} 
