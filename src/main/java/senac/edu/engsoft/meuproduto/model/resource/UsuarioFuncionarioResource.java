package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioFuncionarioResource extends RepresentationModel<UsuarioFuncionarioResource> {

	private Long id;
	private Long cpf;
	private String nome;
	private Long telefoneContato;
	private String email;

	private String ruaEnderecoPessoal;
	private String numeroEnderecoPessoal;
	private String bairroEnderecoPessoal;
	private String cidadeEnderecoPessoal;
	private String estadoEnderecoPessoal;
	private String cepEnderecoPessoal;

	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDate dataAniversario;
	
	public UsuarioFuncionarioResource() {
		super();
	}

	public UsuarioFuncionarioResource(UsuarioFuncionario usuarioFuncionario) {
		super();
		this.id = usuarioFuncionario.getId();
		this.cpf = usuarioFuncionario.getCpf();
		this.nome = usuarioFuncionario.getNome();
		this.telefoneContato = usuarioFuncionario.getTelefoneContato();
		this.email = usuarioFuncionario.getUsername();
		this.ruaEnderecoPessoal = usuarioFuncionario.getRuaEnderecoPessoal();
		this.numeroEnderecoPessoal = usuarioFuncionario.getNumeroEnderecoPessoal();
		this.bairroEnderecoPessoal = usuarioFuncionario.getBairroEnderecoPessoal();
		this.cidadeEnderecoPessoal = usuarioFuncionario.getCidadeEnderecoPessoal();
		this.estadoEnderecoPessoal = usuarioFuncionario.getEstadoEnderecoPessoal();
		this.cepEnderecoPessoal = usuarioFuncionario.getCepEnderecoPessoal();
		this.dataAniversario = usuarioFuncionario.getDataAniversario();
	}
	
}
