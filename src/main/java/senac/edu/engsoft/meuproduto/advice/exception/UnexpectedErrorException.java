package senac.edu.engsoft.meuproduto.advice.exception;

public class UnexpectedErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public UnexpectedErrorException() {
		super();
	}

	public UnexpectedErrorException(String cpf) {
		super("CPF Inválido: " + cpf);
	}

}
