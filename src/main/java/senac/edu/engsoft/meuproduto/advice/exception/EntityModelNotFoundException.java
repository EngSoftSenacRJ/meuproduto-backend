package senac.edu.engsoft.meuproduto.advice.exception;

import java.util.Arrays;

public class EntityModelNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2003143469843668693L;
	
	
	
	public EntityModelNotFoundException() {
		super();
	}

	public EntityModelNotFoundException(Long id) {
		super("Entidade não encontrada para o ID " + id);
	}

	public EntityModelNotFoundException(Class c, Long ...id) {
		super("Entidade "+c.getName()+" não encontrada para o ID " + id != null ? Arrays.stream(id).toString() : null);
	}

}
