package application;

public class FullStackException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FullStackException() {
        super("The stack is full");
    }
}

