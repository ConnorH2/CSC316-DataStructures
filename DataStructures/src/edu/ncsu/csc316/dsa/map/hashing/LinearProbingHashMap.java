package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/** Hash table object which is an array of TableEntry objects. */
    private TableEntry<K, V>[] table;
    /** The size of the Hash table. */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
    	for (TableEntry<K, V> entry : table) {
    		// Only add the entry if it is not deleted
    		if(entry != null && !entry.isDeleted) {
    			collection.add(entry);
    		}
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int idx = findBucket(hash, key);
        
        // key exists, return value
        if(idx >= 0) {
        	return table[idx].getValue();
        } else {
        	// key does not exist, return null
        	return null;
        }
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int idx = findBucket(hash, key);
        
        // key already exists at idx, update value
        if(idx >= 0) {
        	V removed = table[idx].getValue();
        	table[idx].setValue(value);
        	return removed;
        } else {
        	// key does not exist, insert at -idx - 1
        	size++;
        	idx = -idx - 1;
        	table[idx] = new TableEntry<K, V>(key, value);
        	return null;
        }
    }

    private int findBucket(int index, K key) {
        int avail = -1;
        int currentIdx = index;
        
        do {
        	if(isAvailable(currentIdx)) {
        		// If we have not yet encountered an available index, set avail
        		if(avail == -1) {
        			avail = currentIdx;
        		}
        		// If we reach the end of this line of entries, return
        		if(table[currentIdx] == null) {
        			return -(avail + 1);
        		}
        	} else if (table[currentIdx].getKey().equals(key)) {
        		// If we find the key, return the current index
        		return currentIdx;
        	}
        	currentIdx  = (currentIdx + 1) % table.length;
        } while (currentIdx != index);
        
        // Return -(avail + 1) as the index the key should be inserted at
        return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
    	int idx = findBucket(hash, key);
        
        // key exists, remove it
        if(idx >= 0) {
        	V removed = table[idx].getValue();
        	table[idx].setDeleted(true);
        	size--;
        	return removed;
        } else {
        	// key does not exist, return null
        	return null;
        }
        // Remember to set the table bucket as DELETED using setDeleted(true)
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** Boolean flag indicating whether this TableEntry has been deleted. */
        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}

