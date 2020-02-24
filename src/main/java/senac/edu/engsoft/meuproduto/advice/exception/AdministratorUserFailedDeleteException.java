package senac.edu.engsoft.meuproduto.advice.exception;

public class AdministratorUserFailedDeleteException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;



	public AdministratorUserFailedDeleteException() {
		super();
	}

	public AdministratorUserFailedDeleteException(String email) {
		super("Falha ao atualizar o Administrador " + email);
	}

}
