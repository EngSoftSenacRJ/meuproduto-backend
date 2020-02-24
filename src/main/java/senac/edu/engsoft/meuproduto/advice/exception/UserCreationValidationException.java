package senac.edu.engsoft.meuproduto.advice.exception;

public class UserCreationValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public UserCreationValidationException() {
		super();
	}

	public UserCreationValidationException(String msg) {
		super(msg);
	}

}
