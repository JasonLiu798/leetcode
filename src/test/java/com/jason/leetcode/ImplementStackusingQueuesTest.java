package com.jason.leetcode;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ImplementStackusingQueues Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 17, 2015</pre>
 */
public class ImplementStackusingQueuesTest {

	public static ImplementStackusingQueues q;
	public ImplementStackusingQueuesTest(){
		q = new ImplementStackusingQueues();
	}
	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}


	@Test
	public void test() throws Exception {
		q.push(1);
		q.push(2);
		q.push(3);
		q.push(4);
		System.out.println(q.top() + "");
		q.pop();
		System.out.println(q.top() + "");
		q.pop();
		System.out.println(q.top() + "");
		q.pop();
		System.out.println(q.top() + "");
		q.pop();
		System.out.println(q.empty());
	}


} 
