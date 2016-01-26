package com.jason.leetcode;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * GrayCode Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 24, 2015</pre>
 */
public class GrayCodeTest {

    @Before
    public void before() throws Exception {
        g = new GrayCode();
    }

    @After
    public void after() throws Exception {
    }

    public GrayCode g;
    /**
     * Method: grayCode(int n)
     */
    @Test
    public void testGrayCode() throws Exception {
        int n1 = 10;//1010  1,3
        g.grayCode(n1);

    }


} 
