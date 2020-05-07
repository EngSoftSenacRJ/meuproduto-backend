package senac.edu.engsoft.meuproduto.advice.exception;

public class LojaProdutoAlreadyExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;



	public LojaProdutoAlreadyExistException() {
		super();
	}

	public LojaProdutoAlreadyExistException(String nome) {
		super("Associação Loja e Produto já existente");
	}

}
