package utils;

public class InternalErrorException extends RuntimeException {
	
	private String message;
	
	public InternalErrorException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}

