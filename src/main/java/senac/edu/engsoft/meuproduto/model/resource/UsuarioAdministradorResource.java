package senac.edu.engsoft.meuproduto.model.resource;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAdministradorResource extends RepresentationModel<UsuarioAdministradorResource> {

	private Long cpf;
	private String nome;
	private Long telefoneContato;
	private String email;
	
	private String numeroEnderecoPessoal;
	private String bairroEnderecoPessoal;
	private String cidadeEnderecoPessoal;
	private String estadoEnderecoPessoal;
	private String cepEnderecoPessoal;
	
	public UsuarioAdministradorResource() {
		super();
	}

	public UsuarioAdministradorResource(Long cpf, String nome, Long telefoneContato, String email,
			String numeroEnderecoPessoal, String bairroEnderecoPessoal, String cidadeEnderecoPessoal,
			String estadoEnderecoPessoal, String cepEnderecoPessoal) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefoneContato = telefoneContato;
		this.email = email;
		this.numeroEnderecoPessoal = numeroEnderecoPessoal;
		this.bairroEnderecoPessoal = bairroEnderecoPessoal;
		this.cidadeEnderecoPessoal = cidadeEnderecoPessoal;
		this.estadoEnderecoPessoal = estadoEnderecoPessoal;
		this.cepEnderecoPessoal = cepEnderecoPessoal;
	}

}
