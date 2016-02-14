package com.jason.leetcode; 

import com.jason.tools.CollectionTool;
import com.jason.tools.TreeNode;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* BinaryTreePaths Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 27, 2015</pre> 
* @version 1.0 
*/ 
public class BinaryTreePathsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: binaryTreePaths(TreeNode root) 
* 
*/

public static BinaryTreePaths b = new BinaryTreePaths();
@Test
public void testBinaryTreePaths() throws Exception {

	/**
	 *    1
	 * 	2 	3
	 * 4 5 6 7
	 */
	TreeNode t = TreeNode.initNormalTree(1);

	List<String> l = b.binaryTreePaths(t);
	CollectionTool.printList(l);

	/**
	 *    1
	 * 	2
	 */
	TreeNode t1 = TreeNode.initNormalTree(2);
	List<String> l1 = b.binaryTreePaths(t1);
	CollectionTool.printList(l1);
}

/** 
* 
* Method: firstTraverse(TreeNode tr, String path) 
* 
*/ 
@Test
public void testFirstTraverse() throws Exception { 
//TODO: Test goes here... 
} 


} 
