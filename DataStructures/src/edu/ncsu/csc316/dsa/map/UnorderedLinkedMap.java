package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	/** The PositionalList object used in implementing the UnorderedLinkedMap. */
    private PositionalList<Entry<K, V>> list;
    
    /**
     * Constructor for UnorderedLinkedMap which initializes a
     * new PositionalLinkedList object.
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    private Position<Entry<K, V>> lookUp(K key)
    {
    	// Get an iterator from the list's PositionIterable object
        Iterator<Position<Entry<K, V>>> it1 = list.positions().iterator();
        while(it1.hasNext()) {
        	Position<Entry<K, V>> current = it1.next();
        	if(current.getElement().getKey().equals(key)) { // If this is they key we are searching for
        		return current;
        	}
        }
        return null;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if(p == null) {
        	return null;
        }
        moveToFront(p);
        return p.getElement().getValue();
    }
    
    private void moveToFront(Position<Entry<K, V>> position) {
        list.addFirst(list.remove(position));
    }

    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        
        if(p == null) {
        	list.addFirst(new MapEntry<K, V>(key, value));
        	return null;
        }
        V oldVal = p.getElement().getValue();
        MapEntry<K, V> entry = (MapEntry<K, V>) p.getElement();
        entry.setValue(value);
        moveToFront(p);
        return oldVal;
    }
    
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if(p == null) {
    	   return null;
       }
       return list.remove(p).getValue();
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
