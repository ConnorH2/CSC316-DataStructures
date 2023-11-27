package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** HeapAdaptablePriorityQueue object for testing. */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        heap.insert(7, "seven");
        heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        heap.insert(1, "one");
        heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.remove(e8);
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.replaceKey(e5, 11);
        assertEquals(11, (int)e5.getKey());
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(8, heap.size());
        
        heap.replaceKey(e4, -7);
        assertEquals(-7, (int)e4.getKey());
        assertEquals(-7, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
        assertEquals(8, heap.size());
        
        
        heap.remove(e2);
        
        assertThrows(IllegalArgumentException.class, () -> heap.replaceKey(e2, 22));
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        heap.insert(5, "five");
        heap.insert(4, "four");
        heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
        heap.replaceValue(e8,  "eight");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("eight",  e8.getValue());
        
        heap.replaceValue(e0,  "root");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("root", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceValue(e6,  "SIX");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("root", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("SIX",  e6.getValue());
        
        heap.remove(e2);
        
        assertThrows(IllegalArgumentException.class, () -> heap.replaceValue(e2, "TWO"));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        assertThrows(IllegalArgumentException.class, () -> heap.remove(e0));
        
        heap.remove(e3);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(7, heap.size());
        
        heap.remove(e5);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(6, heap.size());
        
        heap.remove(e1);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(5, heap.size());
        
        heap.remove(e2);
        assertEquals(4, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
        assertEquals(4, heap.size());
        
        heap.remove(e4);
        assertEquals(6, (int)heap.min().getKey());
        assertEquals("six", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.remove(e6);
        assertEquals(7, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(2, heap.size());
        
        heap.remove(e7);
        assertEquals(8, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(1, heap.size());
        
        heap.remove(e8);
        assertNull(heap.min());
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s4, "four");
        Entry<Student, String> e3 = sHeap.insert(s3, "three");
        Entry<Student, String> e2 = sHeap.insert(s2, "two");
        Entry<Student, String> e1 = sHeap.insert(s1, "one");
        
        assertEquals("one", sHeap.min().getValue());
        assertEquals(4, sHeap.size());
        
        sHeap.remove(e1);
        assertEquals("two", sHeap.min().getValue());
        assertEquals(3, sHeap.size());
        
        sHeap.replaceKey(e2, s5);
        
        assertEquals("three", sHeap.min().getValue());
        assertEquals(3, sHeap.size());
        
        sHeap.replaceValue(e3, "THREE");
        assertEquals("THREE", sHeap.min().getValue());
        assertEquals(3, sHeap.size());
    }
}
