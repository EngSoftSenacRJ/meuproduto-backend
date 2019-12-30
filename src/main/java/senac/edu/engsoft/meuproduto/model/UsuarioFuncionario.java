package senac.edu.engsoft.meuproduto.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = UsuarioType.Values._FUNCIONARIO)
public class UsuarioFuncionario extends Usuario {
	
	

}
