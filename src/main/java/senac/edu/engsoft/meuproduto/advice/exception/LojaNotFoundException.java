package senac.edu.engsoft.meuproduto.advice.exception;

public class LojaNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2003143469843668693L;



	public LojaNotFoundException() {
		super();
	}

	public LojaNotFoundException(String msg) {
		super(msg);
	}

}
