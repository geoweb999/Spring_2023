import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
	}

	@Override
	public boolean add(T newEntry) {
		if (isEmpty()) {
			return super.add(newEntry);
		} else {
			return addEntryHelperNonRecursive(newEntry);
		}
	}

	// IMPLEMENT THIS METHOD; THIS METHOD CANNOT BE RECURSIVE
	private boolean addEntryHelperNonRecursive(T newEntry) {
		if (isEmpty()) {
			return super.add(newEntry);
		}
	    BinaryNode<T> newNode = new BinaryNode<>(newEntry);
		BinaryNode<T> currentNode = root;
	    while (currentNode != null) {
	        if (newEntry.compareTo(currentNode.getData()) < 0) {
	            if (currentNode.getLeftChild() == null) {
	                currentNode.setLeftChild(newNode);
	                return true;
	            } else {
	                currentNode = currentNode.getLeftChild();
	            }
	        } else if (newEntry.compareTo(currentNode.getData()) > 0) {
	            if (currentNode.getRightChild() == null) {
	                currentNode.setRightChild(newNode);
	                return true;
	            } else {
	                currentNode = currentNode.getRightChild();
	            }
	        } else {
	            // element already exists in the tree
	        	if (currentNode.getLeftChild() == null) {
	                currentNode.setLeftChild(newNode);
	                return true;
	            } 
	        	currentNode = currentNode.getLeftChild();
	        }
	    }
		return false;
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countIterative(T target) {
		// YOUR CODE HERE!
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> currentNode = root;
	    while (currentNode != null) {
	        if (target.compareTo(currentNode.getData()) < 0) {
                currentNode = currentNode.getLeftChild();
	        } else if (target.compareTo(currentNode.getData()) > 0) {
                currentNode = currentNode.getRightChild();
	        } else {
	        	count++;
	        	currentNode = currentNode.getLeftChild();
	        }
	    }
		return count;
	}

	// THIS METHOD MUST BE RECURSIVE! 
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterRecursive(T target) {
		// YOUR CODE HERE! 

		if (isEmpty()) {
			return 0;
		}
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		BinaryNode<T> rootNode = root;
		int count = countGreaterRecursiveHelper(target, root);

		return count;
	}
	
	private int countGreaterRecursiveHelper(T target, BinaryNode<T> currentNode) {
//    	recursionTimes++; // increment your variable here

		if (currentNode == null) {
			return 0;
		}
		int count = 0;
		int compare = currentNode.getData().compareTo(target);
		if (compare < 0) {
			if (currentNode.getRightChild() != null) {
				count += countGreaterRecursiveHelper(target, currentNode.getRightChild());
			}
		} else if (compare > 0) {
			count++;
			if (currentNode.getLeftChild() != null) {
				count += countGreaterRecursiveHelper(target, currentNode.getLeftChild());
			}
			if (currentNode.getRightChild() != null) {
				count += countGreaterRecursiveHelper(target, currentNode.getRightChild());
			}
		} else {
			if (currentNode.getLeftChild() != null) {
				count += countGreaterRecursiveHelper(target, currentNode.getLeftChild());
			}
			if (currentNode.getRightChild() != null) {
				count += countGreaterRecursiveHelper(target, currentNode.getRightChild());
			}
		}
		return count;
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {
		// YOUR CODE HERE!
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = root;
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(rootNode);

		while (!nodeStack.isEmpty()) {
			BinaryNode<T> currentNode = nodeStack.pop();
			int compare = currentNode.getData().compareTo(target);
			if (compare < 0) {
				if (currentNode.getRightChild() != null) {
					nodeStack.push(currentNode.getRightChild());
				}
			} else if (compare > 0) {
				count++;
				if (currentNode.getLeftChild() != null) {
					nodeStack.push(currentNode.getLeftChild());
				}
				if (currentNode.getRightChild() != null) {
					nodeStack.push(currentNode.getRightChild());
				}
			} else {
				if (currentNode.getLeftChild() != null) {
					nodeStack.push(currentNode.getLeftChild());
				}
				if (currentNode.getRightChild() != null) {
					nodeStack.push(currentNode.getRightChild());
				}
			}
		}
		return count;
	}

	private class InorderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InorderIterator() {
            nodeStack = new Stack<>();
            currentNode = root;
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || currentNode != null;
        }

        public T next() {
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            currentNode = nodeStack.pop();
            T result = currentNode.getData();
            currentNode = currentNode.getRightChild();
            return result;
        }
    }

    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in Part B.
	public int countUniqueValues() {
		if (isEmpty()) {
			return 0;
		}
		int count = 1;  // count first item
		T previous = null;
		Iterator<T> iterator = this.getInorderIterator();
		while (iterator.hasNext()) {
			T current = iterator.next();
			if (previous != null) {
				if (current.compareTo(previous) > 0) {
					count++;
				}
			}
		    previous = current;
		}
		return count;
	}
   
}