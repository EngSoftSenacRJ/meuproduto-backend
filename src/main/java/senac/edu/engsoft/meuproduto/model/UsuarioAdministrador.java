package senac.edu.engsoft.meuproduto.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = UsuarioType.Values._ADMINISTRADOR)
public class UsuarioAdministrador extends Usuario {
	
	public UsuarioAdministrador() {
		super();
	}

	public UsuarioAdministrador(String ruaEnderecoPessoal, 
			String numeroEnderecoPessoal,
			String bairroEnderecoPessoal, 
			String cidadeEnderecoPessoal, 
			String estadoEnderecoPessoal,
			String cepEnderecoPessoal, 
			String nome,
			Long telefoneContato, 
			Long cpf, 
			String email,
			LocalDate dataAniversario) {
		super(ruaEnderecoPessoal, numeroEnderecoPessoal, bairroEnderecoPessoal, cidadeEnderecoPessoal,
				estadoEnderecoPessoal, cepEnderecoPessoal, nome, telefoneContato, cpf, email, dataAniversario);
	}
	
}