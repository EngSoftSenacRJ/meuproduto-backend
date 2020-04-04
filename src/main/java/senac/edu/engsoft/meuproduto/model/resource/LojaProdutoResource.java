//package senac.edu.engsoft.meuproduto.model.resource;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.hateoas.RepresentationModel;
//import senac.edu.engsoft.meuproduto.model.HorarioFuncionamento;
//import senac.edu.engsoft.meuproduto.model.Loja;
//
//import java.time.LocalTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@JsonInclude
//public class LojaProdutoResource extends RepresentationModel<LojaProdutoResource> {
//
//	@Getter
//	@Setter
//	private class HorarioFuncionamentoResource {
//		private String diaSemana;
//		private Boolean aberto;
//		private LocalTime horarioFuncionamentoDe;
//		private LocalTime horarioFuncionamentoAte;
//
//		public HorarioFuncionamentoResource() {
//			super();
//		}
//	}
//
//	@Getter
//	@Setter
//	private class ProdutoPrecoResource {
//		private ProdutoResource produtoResource;
//		private Double preco;
//
//		public ProdutoPrecoResource() {
//			super();
//		}
//
//		public ProdutoPrecoResource(ProdutoResource produtoResource, Double preco) {
//			this.produtoResource = produtoResource;
//			this.preco = preco;
//		}
//	}
//
//	private Long id;
//	private String nome;
//	private String razaoSocial;
//	private String cnpj;
//	private String ruaEnderecoComercial;
//	private String numeroEnderecoComercial;
//	private String bairroEnderecoComercial;
//	private String cidadeEnderecoComercial;
//	private String estadoEnderecoComercial;
//	private String cepEnderecoComercial;
//	private String telefoneContato;
//	private Set<HorarioFuncionamentoResource> horarioFuncionamentoSet = new HashSet<>();
//	private Set<ProdutoPrecoResource> produtoPrecoSet = new HashSet<>();
//
//	public LojaProdutoResource() {
//		super();
//	}
//
//	public LojaProdutoResource(Loja loja) {
//		this.id = loja.getId();
//		this.nome = loja.getNome();
//		this.razaoSocial = loja.getRazaoSocial();
//		this.cnpj = loja.getCnpj();
//		this.ruaEnderecoComercial = loja.getRuaEnderecoComercial();
//		this.numeroEnderecoComercial = loja.getNumeroEnderecoComercial();
//		this.bairroEnderecoComercial = loja.getBairroEnderecoComercial();
//		this.cidadeEnderecoComercial = loja.getCidadeEnderecoComercial();
//		this.estadoEnderecoComercial = loja.getEstadoEnderecoComercial();
//		this.cepEnderecoComercial = loja.getCepEnderecoComercial();
//		this.telefoneContato = loja.getTelefoneContato();
//
//		horarioFuncionamentoSet.clear();
//		for(HorarioFuncionamento horarioFuncionamento : loja.getHorarioFuncionamentoSet()){
//			HorarioFuncionamentoResource horarioFuncionamentoResourceNew = new HorarioFuncionamentoResource();
//			horarioFuncionamentoResourceNew.setDiaSemana(horarioFuncionamento.getDiaSemana().toString());
//			horarioFuncionamentoResourceNew.setAberto(horarioFuncionamento.getAberto());
//			horarioFuncionamentoResourceNew.setHorarioFuncionamentoDe(horarioFuncionamento.getHorarioFuncionamentoDe());
//			horarioFuncionamentoResourceNew.setHorarioFuncionamentoAte(horarioFuncionamento.getHorarioFuncionamentoAte());
//			horarioFuncionamentoSet.add(horarioFuncionamentoResourceNew);
//		}
//
//		produtoPrecoSet.clear();
//		for(senac.edu.engsoft.meuproduto.model.LojaProduto lojaProduto : loja.getLojaProdutoSet()){
//			produtoPrecoSet.add(new ProdutoPrecoResource(new ProdutoResource(lojaProduto.getProduto()), lojaProduto.getPreco()));
//		}
//	}
//}
