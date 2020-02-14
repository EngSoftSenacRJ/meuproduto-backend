package senac.edu.engsoft.meuproduto.advice.exception;

public class AdministratorUserEmptyCpfException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	

	public AdministratorUserEmptyCpfException() {
		super("CPF é obrigatório para criação do Usuário Administrador e o mesmo não foi informado");
	}

}
