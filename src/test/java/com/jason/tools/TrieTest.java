package com.jason.tools;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Trie Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 14, 2015</pre>
 */
public class TrieTest {

	private static Trie t;
	public TrieTest(){
		t = new Trie();
	}
	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}


	@Test
	public void test(){
//		t.insert("ab");
//		assertEquals(false,t.search("a"));
//		assertEquals(true,t.startsWith("a"));

		t.insert("abc");
		assertEquals(true,t.search("abc"));
		assertEquals(false,t.search("ab"));
		t.insert("ab");
		assertEquals(true,t.search("ab"));
		t.insert("ab");
		assertEquals(true,t.search("ab"));
	}





} 
