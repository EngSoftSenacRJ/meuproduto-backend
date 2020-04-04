package senac.edu.engsoft.meuproduto.advice.exception;

public class ProdutoAlreadyExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;



	public ProdutoAlreadyExistException() {
		super();
	}

	public ProdutoAlreadyExistException(String nome) {
		super("Produto já existente com nome: " + nome);
	}

}
