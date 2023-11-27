package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
	@Override
    public Iterable<Position<E>> inOrder() {
		PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }

    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
    	if(left(p) != null) {
    		inOrderHelper(left(p), traversal);
    	}
        traversal.add(p);
        if(right(p) != null) {
        	inOrderHelper(right(p), traversal);
    	}
    }
    
    @Override
    public int numChildren(Position<E> p) {
        int counter = 0;
    	if(left(p) != null) {
        	counter++;
        }
    	if(right(p) != null) {
    		counter++;
    	}
    	return counter;
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
    	AbstractTreeNode<E> node = validate(p);
    	Position<E> p2 = parent(node);
    	if(p2 == null) {
    		return null;
    	}
        if(left(p2) == p) {
        	return right(p2);
        } else {
        	return left(p2);
        }
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}