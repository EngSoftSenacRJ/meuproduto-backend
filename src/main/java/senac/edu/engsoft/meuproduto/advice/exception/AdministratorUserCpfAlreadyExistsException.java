package senac.edu.engsoft.meuproduto.advice.exception;

public class AdministratorUserCpfAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public AdministratorUserCpfAlreadyExistsException() {
		super();
	}

	public AdministratorUserCpfAlreadyExistsException(Long cpf) {
		super("CPF " + cpf + " já está cadastrado");
	}

}
