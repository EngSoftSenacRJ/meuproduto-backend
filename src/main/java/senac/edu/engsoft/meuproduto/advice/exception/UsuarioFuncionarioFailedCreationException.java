package senac.edu.engsoft.meuproduto.advice.exception;

public class UsuarioFuncionarioFailedCreationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;


	public UsuarioFuncionarioFailedCreationException(Exception e) {
		super("Falha ao criar Funcionário", e);
	}


}
