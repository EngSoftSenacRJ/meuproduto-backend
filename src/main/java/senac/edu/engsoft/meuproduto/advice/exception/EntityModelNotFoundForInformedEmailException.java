package senac.edu.engsoft.meuproduto.advice.exception;

public class EntityModelNotFoundForInformedEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public EntityModelNotFoundForInformedEmailException() {
		super();
	}

	public EntityModelNotFoundForInformedEmailException(String email) {
		super("Could not find Model by email " + email);
	}

}
