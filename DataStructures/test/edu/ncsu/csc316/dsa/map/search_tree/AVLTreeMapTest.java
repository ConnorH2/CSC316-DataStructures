package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap.BalanceableBinaryTree.BSTNode;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {

	/** The AVLTreeMap object for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(44, "44");
        tree.put(17, "17");
        tree.put(78, "78");
        tree.put(32, "32");
        tree.put(50, "50");
        tree.put(88, "88");
        tree.put(48, "48");
        tree.put(62, "62");
        
        assertEquals(4, ((BSTNode<Entry<Integer, String>>)tree.root()).getProperty());
        assertEquals(8, tree.size());
        
        assertEquals("78", tree.right(tree.root()).getElement().getValue());
        assertEquals("50", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("62", tree.right(tree.left(tree.right(tree.root()))).getElement().getValue());
        assertEquals("48", tree.left(tree.left(tree.right(tree.root()))).getElement().getValue());
        
        tree.put(54, "54");
        assertEquals(4, ((BSTNode<Entry<Integer, String>>)tree.root()).getProperty());
        assertEquals(9, tree.size());
        
        assertEquals("62", tree.right(tree.root()).getElement().getValue());
        assertEquals("50", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("54", tree.right(tree.left(tree.right(tree.root()))).getElement().getValue());
        assertEquals("48", tree.left(tree.left(tree.right(tree.root()))).getElement().getValue());
        
        assertEquals("44", tree.root().getElement().getValue());
        assertEquals("17", tree.left(tree.root()).getElement().getValue());
        assertEquals("32", tree.right(tree.left(tree.root())).getElement().getValue());
        assertNull(tree.right(tree.right(tree.left(tree.root()))).getElement());
        assertNull(tree.left(tree.right(tree.left(tree.root()))).getElement());
        
        tree.put(34, "34");
        assertEquals(10, tree.size());
        assertEquals(2, ((BSTNode<Entry<Integer, String>>)tree.left(tree.root())).getProperty());
        
        assertEquals("44", tree.root().getElement().getValue());
        assertEquals("32", tree.left(tree.root()).getElement().getValue());
        assertEquals("17", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("34", tree.right(tree.left(tree.root())).getElement().getValue());
        
        tree.put(36, "36");
        tree.put(35, "35");
        assertEquals(12, tree.size());
        assertEquals(3, ((BSTNode<Entry<Integer, String>>)tree.left(tree.root())).getProperty());
        
        assertEquals("32", tree.left(tree.root()).getElement().getValue());
        assertEquals("35", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("34", tree.left(tree.right(tree.left(tree.root()))).getElement().getValue());
        assertEquals("36", tree.right(tree.right(tree.left(tree.root()))).getElement().getValue());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.get(1));
        assertNull(tree.get(0));
        assertNull(tree.get(2));
        
        tree.put(5,  "five");
        tree.put(11,  "eleven");
        tree.put(9,  "nine");
        tree.put(7,  "seven");
        tree.put(-5, "-five");
        tree.put(-4, "-four");
        
        assertEquals("-four", tree.get(-4));
        assertEquals("seven", tree.get(7));
        assertEquals("one", tree.get(1));
        assertEquals("-five", tree.get(-5));
        
        assertNull(tree.get(0));
        assertNull(tree.get(2));
        assertNull(tree.get(-7));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        tree.put(1, "1");
    	assertEquals("1", tree.root().getElement().getValue());
    	tree.remove(1);
    	assertNull(tree.root().getElement());
        
        tree.put(44, "44");
        tree.put(17, "17");
        tree.put(62, "62");
        tree.put(22, "22");
        tree.put(50, "50");
        tree.put(78, "78");
        tree.put(48, "48");
        tree.put(54, "54");
        tree.put(88, "88");
        
        
        assertEquals(9, tree.size());
        
        assertEquals("44", tree.root().getElement().getValue());
        assertEquals("17", tree.left(tree.root()).getElement().getValue());
        assertNull(tree.left(tree.left(tree.root())).getElement());
        assertEquals("22", tree.right(tree.left(tree.root())).getElement().getValue());
        
        assertEquals("22", tree.remove(22));
        
        assertEquals("62", tree.root().getElement().getValue());
        assertEquals("44", tree.left(tree.root()).getElement().getValue());
        assertEquals("17", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("50", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals(8, tree.size());
        
        assertEquals("50", tree.remove(50));
        
        assertEquals(7, tree.size());
        assertEquals("54", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("48", tree.left(tree.right(tree.left(tree.root()))).getElement().getValue());
        assertNull(tree.right(tree.right(tree.left(tree.root()))).getElement());
        
        assertEquals("17", tree.remove(17));
        
        assertEquals(6, tree.size());
        assertEquals("48", tree.left(tree.root()).getElement().getValue());
        assertEquals("44", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("54", tree.right(tree.left(tree.root())).getElement().getValue());
        
        assertNull(tree.remove(17));
        assertEquals(6, tree.size());
        assertEquals("48", tree.left(tree.root()).getElement().getValue());
        assertEquals("44", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("54", tree.right(tree.left(tree.root())).getElement().getValue());
        
        tree.remove(62);
        tree.remove(88);
        
        assertEquals(4, tree.size());
        assertEquals("48", tree.root().getElement().getValue());
        assertEquals("44", tree.left(tree.root()).getElement().getValue());
        assertEquals("78", tree.right(tree.root()).getElement().getValue());
        assertEquals("54", tree.left(tree.right(tree.root())).getElement().getValue());
    }
    
    /**
     * Test the behaviors of the tree with a custom comparator.
     */     
    @Test
    public void testCustomComparator() {
    	StudentGPAComparator c1 = new StudentGPAComparator();
    	BinarySearchTreeMap<Student, String> tree2 = new AVLTreeMap<Student, String>(c1);
    	
    	Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		
		tree2.put(sOne, "1");
		tree2.put(sTwo, "2");
		
		assertEquals(2, tree2.size());
		assertEquals("1", tree2.root().getElement().getValue());
		assertNull(tree2.right(tree2.root()).getElement());
		assertEquals("2", tree2.left(tree2.root()).getElement().getValue());
		
		tree2.put(sThree, "3");
		
		assertEquals(3, tree2.size());
		assertEquals("2", tree2.root().getElement().getValue());
		assertEquals("1", tree2.right(tree2.root()).getElement().getValue());
		assertEquals("3", tree2.left(tree2.root()).getElement().getValue());
		
		tree2.put(sFour, "4");
		tree2.remove(sOne);
		
		assertEquals(3, tree2.size());
		assertEquals("3", tree2.root().getElement().getValue());
		assertEquals("2", tree2.right(tree2.root()).getElement().getValue());
		assertEquals("4", tree2.left(tree2.root()).getElement().getValue());
    }
}
