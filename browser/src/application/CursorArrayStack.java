package application;
import java.util.Collection;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Iterator;

public class CursorArrayStack<T> implements Deque<T> {
    // The array of elements in the stack
    private T[] elements;

    // The cursor that keeps track of the top of the stack
    private int cursor;

    // Constructor that initializes the stack
    public CursorArrayStack(int capacity) {
        // Create the array with the given capacity
        elements = (T[]) new Object[capacity];

        // Initialize the cursor to -1 to indicate that the stack is empty
        cursor = -1;
    }

    @Override
    public void push(T element) {
        // Check if the stack is full
        if (cursor == elements.length - 1) {
            // Throw an exception if the stack is full
            throw new FullStackException();
        }

        // Increment the cursor and set the element at the top of the stack
        cursor++;
        elements[cursor] = element;
    }

    @Override
    public T pop() {
        // Check if the stack is empty
        if (cursor < 0) {
            // Throw an exception if the stack is empty
            throw new EmptyStackException();
        }

        // Get the element at the top of the stack and decrement the cursor
        T element = elements[cursor];
        cursor--;
        return element;
    }

    @Override
    public T peek() {
        // Check if the stack is empty
        if (cursor < 0) {
        	throw new EmptyStackException();
        	
        }
        else 
        {	
        	return    elements[cursor];

        }
        	
        
    }

        // Return the element at the top of the stack


	@Override
	public boolean isEmpty() {

			if (cursor < 0) {
				return true;
			}
			else {
				return false;
			}
	}

	@Override
	public Object[] toArray() {
		
		return elements;
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
			
		for (int i = 0; i < cursor; i++) {
			if (elements[i] == c) {
				for (int j = i; j < cursor; j++) {
					elements[j] = elements[j+1];
				}
				cursor--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		for (int i = 0; i < cursor; i++) {
			if (elements[i] == c) {
				for (int j = i; j < cursor; j++) {
					elements[j] = elements[j+1];
				}
				cursor--;
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
	
		for (int i = 0; i < cursor; i++) {
			elements[i] = null;
		}
		cursor = -1;
	}

	@Override
	public void addFirst(T e) {
	
		if (cursor == elements.length - 1) {
			throw new FullStackException();
		}
		else {
			for (int i = cursor; i >= 0; i--) {
				elements[i+1] = elements[i];
			}
			elements[0] = e;
			cursor++;
		}
	}

	@Override
	public void addLast(T e) {
		
		if (cursor == elements.length - 1) {
			throw new FullStackException();
		}
		else {
			cursor++;
			elements[cursor] = e;
		}
	}

	@Override
	public boolean offerFirst(T e) {

		if  (cursor == elements.length - 1) {
			return false;
		}
		else {
			for (int i = cursor; i >= 0; i--) {
				elements[i+1] = elements[i];
			}
			elements[0] = e;
			cursor++;
			return true;
		}
	}

	@Override
	public boolean offerLast(T e) {
	
		if (cursor == elements.length - 1) {
			return false;
		}
		else {
			cursor++;
			elements[cursor] = e;
			return true;
		}
	}

	@Override
	public T removeFirst() {
		
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else {
			T element = elements[0];
			for (int i = 0; i < cursor; i++) {
				elements[i] = elements[i+1];
			}
			cursor--;
			return element;
		}
	}

	@Override
	public T removeLast() {
		
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else {
			T element = elements[cursor];
			elements[cursor] = null;
			cursor--;
			return element;
		}	
	}

	@Override
	public T pollFirst() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else {
			T element = elements[0];
			for (int i = 0; i < cursor; i++) {
				elements[i] = elements[i+1];
			}
			cursor--;
			return element;
		}	
	}

	@Override
	public T pollLast() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else {
			T element = elements[cursor];
			elements[cursor] = null;
			cursor--;
			return element;
		}	
	}

	@Override
	public T getFirst() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			return    elements[0];

		}
	}

	@Override
	public T getLast() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			return    elements[cursor];

		}	
	}

	@Override
	public T peekFirst() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			return    elements[0];

		}

	}

	@Override
	public T peekLast() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			return    elements[cursor];

		}	
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			if (elements[0] == o) {
				for (int i = 0; i < cursor; i++) {
					elements[i] = elements[i+1];
				}
				cursor--;
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean removeLastOccurrence(Object o) {

		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			if (elements[cursor] == o) {
				cursor--;
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean add(T e) {
		if (cursor == elements.length - 1) {
			throw new FullStackException();
		}
		else {
			cursor++;
			elements[cursor] = e;
			return true;
		}

	}

	@Override
	public boolean offer(T e) {
		if (cursor == elements.length - 1) {
			return false;
		}
		else {
			cursor++;
			elements[cursor] = e;
			return true;
		}
	}

	@Override
	public T remove() {
		
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else {
			T element = elements[0];
			for (int i = 0; i < cursor; i++) {
				elements[i] = elements[i+1];
			}
			cursor--;
			return element;
		}
	}

	@Override
	public T poll() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else {
			T element = elements[0];
			for (int i = 0; i < cursor; i++) {
				elements[i] = elements[i+1];
			}
			cursor--;
			return element;
		}	
	}

	@Override
	public T element() {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			return    elements[0];

		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (cursor == elements.length - 1) {
			throw new FullStackException();
		}
		else {
			for (T t : c) {
				cursor++;
				elements[cursor] = t;
			}
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
			if (elements[0] == o) {
				for (int i = 0; i < cursor; i++) {
					elements[i] = elements[i+1];
				}
				cursor--;
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean contains(Object o) {
	 
		for (int i = 0; i <= cursor; i++) {
			if (elements[i] == o) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		if (cursor < 0) {
			return 0;
		}
		else 
		{	
			return cursor + 1;
		}	
	}

	@Override
	public Iterator<T> iterator() {
	
		
		return new Iterator<T>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i <= cursor;
			}

			@Override
			public T next() {
				return elements[i++];
			}
		};
	}

	@Override
	public Iterator<T> descendingIterator() {

		if (cursor < 0) {
			throw new EmptyStackException();
		}
		else 
		{	
		 if (cursor == 0) {
			 return null;
		 }
		 else {
			 return new Iterator<T>() {
				 int i = cursor;
				 @Override
				 public boolean hasNext() {
					 return i >= 0;
				 }

				 @Override
				 public T next() {
					 return elements[i--];
				 }
			 };
		 }
		}
}
	

}