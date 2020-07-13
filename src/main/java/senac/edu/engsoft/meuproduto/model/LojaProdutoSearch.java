package senac.edu.engsoft.meuproduto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Data
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Indexed
@Table(name = "TB_LOJA_PRODUTO_SEARCH")
public class LojaProdutoSearch extends BaseIndexedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOJA_PRODUTO_ID")
	private Long lojaProdutoId;

    @Column(name = "LOJA_PRODUTO_PRECO")
	Double lojaProdutoPreco;

    @Column(name = "PRODUTO_ID")
	Long produtoId;

    @Column(name = "PRODUTO_NOME")
	String produtoNome;

    @Column(name = "PRODUTO_DESCRICAO")
	String produtoDesc;

    @Column(name = "PRODUTO_MESES_GARANTIA")
	Integer produtoMesesGarantia;

    @Column(name = "MARCA_ID")
	Long marcaId;

    @Column(name = "MARCA_HABILITADO")
	Boolean marcaHabilitado;

    @Column(name = "MARCA_NOME")
	String marcaNome;

    @Column(name = "MARCA_DESCRICAO")
	String marcaDescricao;

    @Column(name = "CATEGORIA_ID")
	Long categoriaId;

    @Column(name = "CATEGORIA_NOME")
	String categoriaNome;

    @Column(name = "CATEGORIA_DESCRICAO")
	String categoriaDescricao;

    @Column(name = "LOJA_ID")
    Long lojaId;

    @Column(name = "LOJA_NOME")
    String lojaNome;

    @Column(name = "LOJA_RAZAOSOCIAL")
    String lojaRazaoSocial;

    @Column(name = "LOJA_CNPJ")
    String lojaCnpj;

    @Column(name = "LOJA_RUAENDERECOCOMERCIAL")
    String lojaRuaEnderecoComercial;

    @Column(name = "LOJA_NUMEROENDERECOCOMERCIAL")
    String lojaNumeroEnderecoComercial;

    @Column(name = "LOJA_BAIRROENDERECOCOMERCIAL")
    String lojaBairroEnderecoComercial;

    @Column(name = "LOJA_CIDADEENDERECOCOMERCIAL")
    String lojaCidadeEnderecoComercial;

    @Column(name = "LOJA_ESTADOENDERECOCOMERCIAL")
    String lojaEstadoEnderecoComercial;

    @Column(name = "LOJA_CEPENDERECOCOMERCIAL")
    String lojaCepEnderecoComercial;

    @Column(name = "LOJA_TELEFONECONTATO")
    String lojaTelefoneContato;

    public LojaProdutoSearch() {
    }

    public LojaProdutoSearch(Long lojaProdutoId, Double lojaProdutoPreco, Long produtoId, String produtoNome, String produtoDesc, Integer produtoMesesGarantia, Long marcaId, Boolean marcaHabilitado, String marcaNome, String marcaDescricao, Long categoriaId, String categoriaNome, String categoriaDescricao, Long lojaId, String lojaNome, String lojaRazaoSocial, String lojaCnpj, String lojaRuaEnderecoComercial, String lojaNumeroEnderecoComercial, String lojaBairroEnderecoComercial, String lojaCidadeEnderecoComercial, String lojaEstadoEnderecoComercial, String lojaCepEnderecoComercial, String lojaTelefoneContato) {
        this.lojaProdutoId = lojaProdutoId;
        this.lojaProdutoPreco = lojaProdutoPreco;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.produtoDesc = produtoDesc;
        this.produtoMesesGarantia = produtoMesesGarantia;
        this.marcaId = marcaId;
        this.marcaHabilitado = marcaHabilitado;
        this.marcaNome = marcaNome;
        this.marcaDescricao = marcaDescricao;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
        this.categoriaDescricao = categoriaDescricao;
        this.lojaId = lojaId;
        this.lojaNome = lojaNome;
        this.lojaRazaoSocial = lojaRazaoSocial;
        this.lojaCnpj = lojaCnpj;
        this.lojaRuaEnderecoComercial = lojaRuaEnderecoComercial;
        this.lojaNumeroEnderecoComercial = lojaNumeroEnderecoComercial;
        this.lojaBairroEnderecoComercial = lojaBairroEnderecoComercial;
        this.lojaCidadeEnderecoComercial = lojaCidadeEnderecoComercial;
        this.lojaEstadoEnderecoComercial = lojaEstadoEnderecoComercial;
        this.lojaCepEnderecoComercial = lojaCepEnderecoComercial;
        this.lojaTelefoneContato = lojaTelefoneContato;
    }
}
