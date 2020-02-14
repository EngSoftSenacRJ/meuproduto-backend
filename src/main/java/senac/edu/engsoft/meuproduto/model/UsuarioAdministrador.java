package senac.edu.engsoft.meuproduto.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = UsuarioType.Values._ADMINISTRADOR)
public class UsuarioAdministrador extends Usuario {
	
	private String cnpj;

	public UsuarioAdministrador() {
		super();
	}
	
}