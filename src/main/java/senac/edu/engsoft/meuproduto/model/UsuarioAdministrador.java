package senac.edu.engsoft.meuproduto.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = UsuarioType.Values._ADMINISTRADOR)
public class UsuarioAdministrador extends Usuario {
	
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public UsuarioAdministrador() {
		super();
	}
	
}