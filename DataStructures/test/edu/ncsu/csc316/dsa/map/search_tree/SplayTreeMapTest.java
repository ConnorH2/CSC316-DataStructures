package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {

	/** SplayTreeObject for testing. */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        
        tree.put(48, "48");
        assertEquals(1, tree.size());
        assertEquals("48", tree.root().getElement().getValue());
        
        tree.put(50, "50");
        assertEquals(2, tree.size());
        assertEquals("50", tree.root().getElement().getValue());
        
        tree.put(62, "62");
        assertEquals("62", tree.root().getElement().getValue());
        
        tree.put(78, "78");
        tree.put(44, "44");
        assertEquals(5, tree.size());
        
        assertEquals("44", tree.root().getElement().getValue());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals("62", tree.right(tree.root()).getElement().getValue());
        assertEquals("48", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("78", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals("50", tree.right(tree.left(tree.right(tree.root()))).getElement().getValue());
        
        tree.put(49, "49");
        assertEquals(6, tree.size());
        
        assertEquals("49", tree.root().getElement().getValue());
        assertEquals("44", tree.left(tree.root()).getElement().getValue());
        assertEquals("62", tree.right(tree.root()).getElement().getValue());
        assertEquals("50", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("78", tree.right(tree.right(tree.root())).getElement().getValue());
        assertEquals("48", tree.right(tree.left(tree.root())).getElement().getValue());
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	assertNull(tree.get(1));
    	
    	tree.put(48, "48");
    	tree.put(50, "50");
    	tree.put(62, "62");
    	tree.put(78, "78");
    	tree.put(44, "44");
    	tree.put(49, "49");
    	assertEquals(6, tree.size());
    	
    	assertEquals("78", tree.get(78));
    	
    	assertEquals("78", tree.root().getElement().getValue());
    	assertEquals("62", tree.left(tree.root()).getElement().getValue());
    	assertEquals("49", tree.left(tree.left(tree.root())).getElement().getValue());
    	assertEquals("44", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
    	assertEquals("50", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
    	assertEquals("48", tree.right(tree.left(tree.left(tree.left(tree.root())))).getElement().getValue());
    	
    	assertEquals("78", tree.get(78));
    	
    	assertEquals("78", tree.root().getElement().getValue());
    	assertEquals("62", tree.left(tree.root()).getElement().getValue());
    	assertEquals("49", tree.left(tree.left(tree.root())).getElement().getValue());
    	assertEquals("44", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
    	assertEquals("50", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
    	assertEquals("48", tree.right(tree.left(tree.left(tree.left(tree.root())))).getElement().getValue());
    	
    	assertEquals("50", tree.get(50));
    	
    	assertEquals("50", tree.root().getElement().getValue());
        assertEquals("49", tree.left(tree.root()).getElement().getValue());
        assertEquals("78", tree.right(tree.root()).getElement().getValue());
        assertEquals("62", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("44", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("48", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
        
        assertEquals("50", tree.get(50));
    	
    	assertEquals("50", tree.root().getElement().getValue());
        assertEquals("49", tree.left(tree.root()).getElement().getValue());
        assertEquals("78", tree.right(tree.root()).getElement().getValue());
        assertEquals("62", tree.left(tree.right(tree.root())).getElement().getValue());
        assertEquals("44", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("48", tree.right(tree.left(tree.left(tree.root()))).getElement().getValue());
        
        
        assertEquals(null, tree.get(79));
        
        assertEquals("78", tree.root().getElement().getValue());
        assertEquals("50", tree.left(tree.root()).getElement().getValue());
        assertEquals("49", tree.left(tree.left(tree.root())).getElement().getValue());
        assertEquals("62", tree.right(tree.left(tree.root())).getElement().getValue());
        assertEquals("44", tree.left(tree.left(tree.left(tree.root()))).getElement().getValue());
        assertEquals("48", tree.right(tree.left(tree.left(tree.left(tree.root())))).getElement().getValue());
        
        assertEquals("50", tree.get(50));
        
    }
    
    /**
     * 
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	assertNull(tree.remove(1));
    	
    	tree.put(1, "1");
    	assertEquals("1", tree.root().getElement().getValue());
    	tree.remove(1);
    	assertNull(tree.root().getElement());
    	
    	
    	tree.put(48, "48");
    	tree.put(50, "50");
    	tree.put(62, "62");
    	tree.put(78, "78");
    	tree.put(44, "44");
    	tree.put(49, "49");
    	assertEquals(6, tree.size());
    	
    	assertEquals("78", tree.get(78));
    	assertEquals("50", tree.get(50));
    	
    	assertEquals("48", tree.remove(48));
    	
    	assertEquals("44", tree.root().getElement().getValue());
    	assertEquals("49", tree.right(tree.root()).getElement().getValue());
    	assertEquals("50", tree.right(tree.right(tree.root())).getElement().getValue());
    	assertEquals("78", tree.right(tree.right(tree.right(tree.root()))).getElement().getValue());
    	assertEquals("62", tree.left(tree.right(tree.right(tree.right(tree.root())))).getElement().getValue());
    	
    	tree.put(51, "51");
    	assertEquals("49", tree.remove(49));
    	
    	assertEquals("50", tree.root().getElement().getValue());
    	assertEquals("51", tree.right(tree.root()).getElement().getValue());
    	assertEquals("62", tree.right(tree.right(tree.root())).getElement().getValue());
    	assertEquals("78", tree.right(tree.right(tree.right(tree.root()))).getElement().getValue());
    	assertEquals("44", tree.left(tree.root()).getElement().getValue());
    	
    	tree.remove(62);
    	
    	assertEquals("51", tree.root().getElement().getValue());
    	assertEquals("78", tree.right(tree.root()).getElement().getValue());
    	assertEquals("44", tree.left(tree.left(tree.root())).getElement().getValue());
    	assertEquals("50", tree.left(tree.root()).getElement().getValue());
    	
    	tree.remove(51);
    	assertEquals("78", tree.root().getElement().getValue());
    	assertEquals("44", tree.left(tree.left(tree.root())).getElement().getValue());
    	assertEquals("50", tree.left(tree.root()).getElement().getValue());
    	assertEquals(3, tree.size());
    	
    	assertNull(tree.remove(51));
    	assertEquals("50", tree.root().getElement().getValue());
    	assertEquals("44", tree.left(tree.root()).getElement().getValue());
    	assertEquals("78", tree.right(tree.root()).getElement().getValue());
    	assertEquals(3, tree.size());
    }
    
    /**
     * Test the behaviors of the tree with a custom comparator.
     */     
    @Test
    public void testCustomComparator() {
    	StudentGPAComparator c1 = new StudentGPAComparator();
    	BinarySearchTreeMap<Student, String> tree2 = new SplayTreeMap<Student, String>(c1);
    	
    	Student sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		Student sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		Student sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		Student sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		
		tree2.put(sOne, "1");
		tree2.put(sTwo, "2");
		
		assertEquals(2, tree2.size());
		assertEquals("2", tree2.root().getElement().getValue());
		assertNull(tree2.left(tree2.root()).getElement());
		assertEquals("1", tree2.right(tree2.root()).getElement().getValue());
		
		tree2.put(sThree, "3");
		
		assertEquals(3, tree2.size());
		assertEquals("3", tree2.root().getElement().getValue());
		assertEquals("2", tree2.right(tree2.root()).getElement().getValue());
		assertEquals("1", tree2.right(tree2.right(tree2.root())).getElement().getValue());
		
		tree2.put(sFour, "4");
		tree2.remove(sOne);
		
		assertEquals(3, tree2.size());
		assertEquals("2", tree2.root().getElement().getValue());
		assertEquals("3", tree2.left(tree2.root()).getElement().getValue());
		assertEquals("4", tree2.left(tree2.left(tree2.root())).getElement().getValue());
    }
}