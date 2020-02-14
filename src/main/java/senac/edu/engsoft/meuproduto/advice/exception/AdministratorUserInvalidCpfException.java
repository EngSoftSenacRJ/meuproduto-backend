package senac.edu.engsoft.meuproduto.advice.exception;

public class AdministratorUserInvalidCpfException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public AdministratorUserInvalidCpfException() {
		super();
	}

	public AdministratorUserInvalidCpfException(Long cpf) {
		super("CPF " + cpf + "inválido");
	}

}
