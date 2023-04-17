
public class EmptySecondException extends RuntimeException {
	public EmptySecondException() {
		this(null);
	}

	public EmptySecondException(String message) {
		super(message);
	}
}


