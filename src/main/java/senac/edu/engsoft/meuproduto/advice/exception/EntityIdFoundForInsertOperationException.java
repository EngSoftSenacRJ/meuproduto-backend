package senac.edu.engsoft.meuproduto.advice.exception;

public class EntityIdFoundForInsertOperationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;



	public EntityIdFoundForInsertOperationException() {
		super();
	}

	public EntityIdFoundForInsertOperationException(Long id) {
		super("ID "+id+" informado para operação de Insert. O ID é gerado automaticamente, remova o ID da requisição");
	}

}
