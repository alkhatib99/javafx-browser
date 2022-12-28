package application;

import java.util.Collection;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Deque<T> {
    // The Node inner class represents a node in the linked list
    private class Node {
        T element;
        Node next;

        public Node(T element) {
            this.element = element;
            this.next = null;
        }
    }

    // The top of the stack
    private Node top;

    // The size of the stack
    private int size;

    // Constructor that initializes the stack
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(T element) {
        // Create a new node and set it as the top of the stack
        Node node = new Node(element);
        node.next = top;
        top = node;

        // Increment the size of the stack
        size++;
    }

    @Override
    public T pop() {
        // Check if the stack is empty
        if (top == null) {
            // Throw an exception if the stack is empty
            throw new EmptyStackException();
        }

        // Get the element at the top of the stack and set the next node as the top
        T element = top.element;
        top = top.next;

        // Decrement the size of the stack
        size--;
        return element;
    }

    @Override
    public T peek() {
        // Check if the stack is empty
        if (top == null) {
            // Return null if the stack is empty
            return null;
        }

        // Return the element at the top of the stack
        return top.element;
    }


	@Override
	public boolean isEmpty() {
if (top == null) {
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Object[] toArray() {
		
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		if (c.containsAll(c)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
			
		if (c.removeAll(c)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		for (int i = 0; i < c.size(); i++) {
			if (c.contains(c)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		top = null;
		size = 0;
	}

	@Override
	public void addFirst(T e) {
	 		Node node = new Node(e);
		node.next = top;
		top = node;
		size++;
	}

	@Override
	public void addLast(T e) {
		
	 		Node node = new Node(e);
		node.next = top;
		top = node;
		size++;
	}

	@Override
	public boolean offerFirst(T e) {
		
		if (top == null) {
			return false;
		}
		else {
			Node node = new Node(e);
			node.next = top;
			top = node;
			size++;
			return true;
		}
	}

	@Override
	public boolean offerLast(T e) {
	
		
		if (top == null) {
			return false;
		}
		else {
			Node node = new Node(e);
			node.next = top;
			top = node;
			size++;
			return true;
		}
	}

	@Override
	public T removeFirst() {
		
		if (top == null) {
			throw new EmptyStackException();
		}
		else {
			T element = top.element;
			top = top.next;
			size--;
			return element;
		}
	}

	@Override
	public T removeLast() {
	 		
		if (top == null) {
			throw new EmptyStackException();
		}
		else {
			T element = top.element;
			top = top.next;
			size--;
			return element;
		}
	}

	@Override
	public T pollFirst() {
		 		
		if (top == null) {
			throw new EmptyStackException();
		}
		else {
			T element = top.element;
			top = top.next;
			size--;
			return element;
		}
	}

	@Override
	public T pollLast() {
	 		 		
		if (top == null) {
			throw new EmptyStackException();
		}
		else {
			T element = top.element;
			top = top.next;
			size--;
			return element;
		}
	}

	@Override
	public T getFirst() {
	  if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return element;
	  }

	}

	@Override
	public T getLast() {
		 	  if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return element;
	  }
	}

	@Override
	public T peekFirst() {
	 		 	  if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return element;
	  }
	}

	@Override
	public T peekLast() {
	 		 	  if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return element;
	  }
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		 if (top == null) {
				throw new EmptyStackException();
		  }

		  else {
			  T element = top.element;
			  top = top.next;
			  size--;
			  return true;
		  }
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		 if (top == null) {
				throw new EmptyStackException();
		  }

		  else {
			  T element = top.element;
			  top = top.next;
			  size--;
			  return true;
		  }
	}

	@Override
	public boolean add(T e) {
	 if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return true;
	  }

	}

	@Override
	public boolean offer(T e) {
		 if (top == null) {
				throw new EmptyStackException();
		  }

		  else {
			  T element = top.element;
			  top = top.next;
			  size--;
			  return true;
		  }
	}

	@Override
	public T remove() {
	 		 if (top == null) {
				throw new EmptyStackException();
		  }

		  else {
			  T element = top.element;
			  top = top.next;
			  size--;
			  return element;
		  }
	}

	@Override
	public T poll() {
			 		 if (top == null) {
				throw new EmptyStackException();
		  }

		  else {
			  T element = top.element;
			  top = top.next;
			  size--;
			  return element;
		  }
	}

	@Override
	public T element() {
	
		 if (top == null) {
				throw new EmptyStackException();
		  }

		  else {
			  T element = top.element;
			  top = top.next;
			  size--;
			  return element;
		  }
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		
		if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return true;
	  }
	}

	@Override
	public boolean remove(Object o) {
		
		if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return true;
	  }
	}

	@Override
	public boolean contains(Object o) {
	 
	
		if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return true;
	  }
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
	
		
		
		
		if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
		  T element = top.element;
		  top = top.next;
		  size--;
		  return null;
	  }
	}

	@Override
	public Iterator<T> descendingIterator() {

		if (top == null) {
			throw new EmptyStackException();
	  }

	  else {
// create iterator
		Iterator<T> iterator = new Iterator<T>() {
			Node current = top;
			@Override
			public boolean hasNext() {
				return top != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();				}
				T element = current.element;
				current = current.next;
				return element;
			}
		};
		return iterator;
	  }
		}

	}
	

