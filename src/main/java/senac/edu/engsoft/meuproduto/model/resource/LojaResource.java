package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.HorarioFuncionamento;
import senac.edu.engsoft.meuproduto.model.Loja;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonInclude
public class LojaResource extends RepresentationModel<LojaResource> {

	@Getter
	@Setter
	private class HorarioFuncionamentoResource {

		private String diaSemana;
		private Boolean aberto;
		private LocalTime horarioFuncionamentoDe;
		private LocalTime horarioFuncionamentoAte;

		public HorarioFuncionamentoResource() {
			super();
		}
	}

	private Long id;
	private String nome;
	private String razaoSocial;
	private String cnpj;
	private String ruaEnderecoComercial;
	private String numeroEnderecoComercial;
	private String bairroEnderecoComercial;
	private String cidadeEnderecoComercial;
	private String estadoEnderecoComercial;
	private String cepEnderecoComercial;
	private String telefoneContato;
	private Set<HorarioFuncionamentoResource> horarioFuncionamentoSet = new HashSet<>();

	public LojaResource() {
		super();
	}

	public LojaResource(Loja loja) {
		this.id = loja.getId();
		this.nome = loja.getNome();
		this.razaoSocial = loja.getRazaoSocial();
		this.cnpj = loja.getCnpj();
		this.ruaEnderecoComercial = loja.getRuaEnderecoComercial();
		this.numeroEnderecoComercial = loja.getNumeroEnderecoComercial();
		this.bairroEnderecoComercial = loja.getBairroEnderecoComercial();
		this.cidadeEnderecoComercial = loja.getCidadeEnderecoComercial();
		this.estadoEnderecoComercial = loja.getEstadoEnderecoComercial();
		this.cepEnderecoComercial = loja.getCepEnderecoComercial();
		this.telefoneContato = loja.getTelefoneContato();

		Set<HorarioFuncionamentoResource> horarioFuncionamentos = new HashSet<>();
		for(HorarioFuncionamento horarioFuncionamento : loja.getHorarioFuncionamentoSet()){
			HorarioFuncionamentoResource horarioFuncionamentoResourceNew = new HorarioFuncionamentoResource();
			horarioFuncionamentoResourceNew.setDiaSemana(horarioFuncionamento.getDiaSemana().toString());
			horarioFuncionamentoResourceNew.setAberto(horarioFuncionamento.getAberto());
			horarioFuncionamentoResourceNew.setHorarioFuncionamentoDe(horarioFuncionamento.getHorarioFuncionamentoDe());
			horarioFuncionamentoResourceNew.setHorarioFuncionamentoAte(horarioFuncionamento.getHorarioFuncionamentoAte());
			horarioFuncionamentoSet.add(horarioFuncionamentoResourceNew);
		}
	}
}
