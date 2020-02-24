package senac.edu.engsoft.meuproduto.model.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

import java.time.LocalDate;

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

	private LocalDate dataAniversario;
	
	public UsuarioAdministradorResource() {
		super();
	}

	public UsuarioAdministradorResource(UsuarioAdministrador usuarioAdministrador) {
		super();
		this.cpf = usuarioAdministrador.getCpf();
		this.nome = usuarioAdministrador.getNome();
		this.telefoneContato = usuarioAdministrador.getTelefoneContato();
		this.email = usuarioAdministrador.getUsername();
		this.numeroEnderecoPessoal = usuarioAdministrador.getNumeroEnderecoPessoal();
		this.bairroEnderecoPessoal = usuarioAdministrador.getBairroEnderecoPessoal();
		this.cidadeEnderecoPessoal = usuarioAdministrador.getCidadeEnderecoPessoal();
		this.estadoEnderecoPessoal = usuarioAdministrador.getEstadoEnderecoPessoal();
		this.cepEnderecoPessoal = usuarioAdministrador.getCepEnderecoPessoal();
		this.dataAniversario = usuarioAdministrador.getDataAniversario();
	}

}
