
public class NonAlphaNumericException extends Exception {
	
	private static final String MESSAGE = "Non-alphanumeric data";
	
	public NonAlphaNumericException() {
		super(MESSAGE);
	}
		
	public NonAlphaNumericException(String message) {
		super(message);
	}
}
