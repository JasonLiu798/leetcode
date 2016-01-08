package com.jason.leetcode.easy; 

import com.jason.tools.CollectionTool;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* MoveZeroes Tester. 
* 
* @author <Authors name> 
* @since <pre>十月 3, 2015</pre> 
* @version 1.0 
*/ 
public class MoveZeroesTest { 
    public static MoveZeroes m;
    @Before
    public void before() throws Exception {
        m = new MoveZeroes();
        m.log = true;
    } 

    @After
    public void after() throws Exception {
        
    }

    @Test
    public void testMoveZeroes() throws Exception {
        //          0 1 2 3 4 5
        int[] a1 = {1,2,0,0,0,4};
        int[] a2 = {1,0,0,3,0,5,6};
        CollectionTool.printArray(a2);
        m.moveZeroes(a2);
        CollectionTool.printArray(a2);
//        System.out.println(a1);
    }

}
