package senac.edu.engsoft.meuproduto.advice.exception;

public class UserCpfAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public UserCpfAlreadyExistsException() {
		super();
	}

	public UserCpfAlreadyExistsException(Long cpf) {
		super("CPF " + cpf + " já está cadastrado");
	}

}
