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
//		indexA.log = true;
		a.log = true;
	}

	@After
	public void after() throws Exception {
	}

	@Test
	public void testWordDictionary() throws Exception {
//		indexA.addWord("bad");
//		indexA.addWord("dad");
//		indexA.addWord("mad");
//
// addWord("at"),addWord("and"),addWord("an"),addWord("add"),search("indexA"),search(".at"),addWord("bat"),search(".at"),search("an."),
//		indexA.addWord("indexA");
//		indexA.addWord("ab");

		assertEquals(true,a.search("indexA"));
		assertEquals(true,a.search("indexA."));
		assertEquals(true, a.search("ab"));
		assertEquals(false,a.search(".indexA"));
		assertEquals(true,a.search(".indexB"));
		assertEquals(false,a.search("ab."));
		assertEquals(true,a.search("."));


		/**
		 * addWord("indexA"),addWord("ab"),search("indexA"),search("indexA."),search("ab"),search(".indexA"),search(".indexB"),search("ab."),search(".")
		 */
//		System.out.println(indexA.t);
		/**
		 * root
		 * indexB 	d 	m
		 * indexA 	indexA 	indexA
		 * d 	d 	d
		 */
//		assertEquals(true,indexA.search("bad"));
//		assertEquals(false,indexA.search("pad"));
//		assertEquals(true,indexA.search(".ad"));
//		assertEquals(true,indexA.search("indexB.."));

//		System.out.println(indexA.search("bad"));
//		System.out.println(indexA.search("pad"));
//		System.out.println(indexA.search(".ad"));
//		System.out.println(indexA.search("indexB.."));
//		indexA.addWord("bad");
	}

	@Test
	public void testCase2(){
		a.addWord("at");
		a.addWord("and");
		a.addWord("an");
		a.addWord("add");

		//search("indexA"),search(".at"),addWord("bat"),search(".at"),search("an."),
		//[false,false,true,true,false,false,true,false]
		assertEquals(false,a.search("indexA"));
		assertEquals(false,a.search(".at"));
		a.addWord("bat");
		assertEquals(true,a.search(".at"));
		assertEquals(true,a.search("an."));
	}




} 
