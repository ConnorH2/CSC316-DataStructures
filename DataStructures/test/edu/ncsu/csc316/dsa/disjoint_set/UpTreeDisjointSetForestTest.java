package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 *
 */
public class UpTreeDisjointSetForestTest {

	/** DisjointSetForest object for testing. */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        Position<String> three = set.makeSet("two");
        assertEquals("two", three.getElement());
        assertEquals("two", two.getElement());
        
        assertNotEquals(two, three);
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        
        // you should draw out examples by hand (or use the examples
        // in the lecture slides or textbook) to help guide your test cases.
        // Be sure to perform find operations followed by union
        // operations to make sure the appropriate root notes are
        // returned and used when union-ing
        
        assertEquals(three, set.find("three"));
        
        set.union(one, three);
        assertEquals(three, set.find("one"));
        assertEquals(three, set.find("three"));
        
        assertEquals(two, set.find("two"));
        assertEquals(four, set.find("four"));
        
        set.union(four, two);
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("two"));
        
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("four"));
        
        set.union(five, six);
        assertEquals(six, set.find("five"));
        assertEquals(six, set.find("six"));
        
        set.union(three, five);
        assertEquals(three, set.find("one"));
        assertEquals(three, set.find("three"));
        assertEquals(three, set.find("five"));
        assertEquals(six, set.find("six"));
        
        set.union(six, two);
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("six"));
        assertEquals(three, set.find("five"));
        
        set.union(seven, four);
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("seven"));
        
        set.union(two, three);
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("three"));
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("five"));
        assertEquals(two, set.find("six"));
        assertEquals(two, set.find("seven"));
        
        set.union(nine, eight);
        set.union(ten, nine);
        assertEquals(eight, set.find("ten"));
        
        ten = set.makeSet("ten");
        
        assertEquals(ten, set.find("ten"));
        
        assertThrows(IllegalArgumentException.class, () -> set.union(null, nine));
    }
}
