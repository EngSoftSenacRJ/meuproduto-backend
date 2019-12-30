package senac.edu.engsoft.meuproduto.advice.exception;

public class EntityModelNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public EntityModelNotFoundException() {
		super();
	}

	public EntityModelNotFoundException(Long id) {
		super("Could not find Model by id " + id);
	}

}
