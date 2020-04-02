package senac.edu.engsoft.meuproduto.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = UsuarioType.Values._ADMINISTRADOR)
public class UsuarioAdministrador extends Usuario {

	public UsuarioAdministrador() {
		super();
	}

	public UsuarioAdministrador(Usuario usuario){
		super(usuario);
	}

	public UsuarioAdministrador copyForNew(UsuarioAdministrador other){
		if(other.getNewPassword() != null && other.getNewPassword().length() > 1)
			super.setPassword(other.getNewPassword());
		if(other.getRuaEnderecoPessoal() != null)
			super.setRuaEnderecoPessoal(other.getRuaEnderecoPessoal());
		if(other.getNumeroEnderecoPessoal() != null)
			super.setNumeroEnderecoPessoal(other.getNumeroEnderecoPessoal());
		if(other.getBairroEnderecoPessoal() != null)
			super.setBairroEnderecoPessoal(other.getBairroEnderecoPessoal());
		if(other.getCidadeEnderecoPessoal() != null)
			super.setCidadeEnderecoPessoal(other.getCidadeEnderecoPessoal());
		if(other.getEstadoEnderecoPessoal() != null)
			super.setEstadoEnderecoPessoal(other.getEstadoEnderecoPessoal());
		if(other.getCepEnderecoPessoal() != null)
			super.setCepEnderecoPessoal(other.getCepEnderecoPessoal());
		if(other.getNome() != null)
			super.setNome(other.getNome());
		if(other.getTelefoneContato() != null)
			super.setTelefoneContato(other.getTelefoneContato());
		if(other.getCpf() != null)
			super.setCpf(other.getCpf());
		if(other.getUsername() != null)
			super.setUsername(other.getUsername());
		if(other.getDataAniversario() != null)
			super.setDataAniversario(other.getDataAniversario());
		if(other.getTokenValidacaoEmail() != null)
			super.setTokenValidacaoEmail(other.getTokenValidacaoEmail());

		return this;
	}
	
}