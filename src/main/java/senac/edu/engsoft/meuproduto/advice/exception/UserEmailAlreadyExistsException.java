package senac.edu.engsoft.meuproduto.advice.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;



	public UserEmailAlreadyExistsException() {
		super();
	}

	public UserEmailAlreadyExistsException(String email) {
		super("Email " + email + " já está cadastrado");
	}

}
