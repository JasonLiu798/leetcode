package com.jason.leetcode;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * AddandSearchWordDatastructuredesign Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 20, 2015</pre>
 */
public class AddandSearchWordDatastructuredesignTest {

	public static AddandSearchWordDatastructuredesign a = new AddandSearchWordDatastructuredesign();

	@Before
	public void before() throws Exception {
		a.WordDictionary();
//		a.log = true;
		a.log = true;
	}

	@After
	public void after() throws Exception {
	}

	@Test
	public void testWordDictionary() throws Exception {
//		a.addWord("bad");
//		a.addWord("dad");
//		a.addWord("mad");
//
// addWord("at"),addWord("and"),addWord("an"),addWord("add"),search("a"),search(".at"),addWord("bat"),search(".at"),search("an."),
//		a.addWord("a");
//		a.addWord("ab");

		assertEquals(true,a.search("a"));
		assertEquals(true,a.search("a."));
		assertEquals(true, a.search("ab"));
		assertEquals(false,a.search(".a"));
		assertEquals(true,a.search(".b"));
		assertEquals(false,a.search("ab."));
		assertEquals(true,a.search("."));


		/**
		 * addWord("a"),addWord("ab"),search("a"),search("a."),search("ab"),search(".a"),search(".b"),search("ab."),search(".")
		 */
//		System.out.println(a.t);
		/**
		 * root
		 * b 	d 	m
		 * a 	a 	a
		 * d 	d 	d
		 */
//		assertEquals(true,a.search("bad"));
//		assertEquals(false,a.search("pad"));
//		assertEquals(true,a.search(".ad"));
//		assertEquals(true,a.search("b.."));

//		System.out.println(a.search("bad"));
//		System.out.println(a.search("pad"));
//		System.out.println(a.search(".ad"));
//		System.out.println(a.search("b.."));
//		a.addWord("bad");
	}

	@Test
	public void testCase2(){
		a.addWord("at");
		a.addWord("and");
		a.addWord("an");
		a.addWord("add");

		//search("a"),search(".at"),addWord("bat"),search(".at"),search("an."),
		//[false,false,true,true,false,false,true,false]
		assertEquals(false,a.search("a"));
		assertEquals(false,a.search(".at"));
		a.addWord("bat");
		assertEquals(true,a.search(".at"));
		assertEquals(true,a.search("an."));


	}




} 
