package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.LojaProduto;

import java.util.List;
import java.util.Optional;

@Repository
public interface LojaProdutoRepository extends CrudRepository<LojaProduto, Long>{

//    @Query(value = "insert into TB_MEUPRODUTO_SEARCH (" +
//            "LOJA_PRODUTO_ID, " +
//            "LOJA_PRODUTO_PRECO, " +
//            "PRODUTO_ID, " +
//            "PRODUTO_NOME, " +
//            "PRODUTO_DESCRICAO, " +
//            "PRODUTO_MESES_GARANTIA, " +
//            "MARCA_ID, " +
//            "MARCA_HABILITADO, " +
//            "MARCA_NOME, " +
//            "MARCA_DESCRICAO, " +
//            "CATEGORIA_ID, " +
//            "CATEGORIA_NOME, " +
//            "CATEGORIA_DESCRICAO, " +
//            "LOJA_ID, " +
//            "LOJA_NOME, " +
//            "LOJA_RAZAOSOCIAL, " +
//            "LOJA_CNPJ, " +
//            "LOJA_RUAENDERECOCOMERCIAL, " +
//            "LOJA_NUMEROENDERECOCOMERCIAL, " +
//            "LOJA_BAIRROENDERECOCOMERCIAL, " +
//            "LOJA_CIDADEENDERECOCOMERCIAL, " +
//            "LOJA_ESTADOENDERECOCOMERCIAL, " +
//            "LOJA_CEPENDERECOCOMERCIAL, " +
//            "LOJA_TELEFONECONTATO, " +
//            "LOJA_HR_FUNC_DIA_SEMANA, " +
//            "LOJA_HR_FUNC_ABERTO, " +
//            "LOJA_HR_FUNC_DE, " +
//            "LOJA_HR_FUNC_ATE " +
//            "VALUES (?1," +
//            "?2," +
//            "?3," +
//            "?4," +
//            "?5," +
//            "?6," +
//            "?7," +
//            "?8," +
//            "?9," +
//            "?10," +
//            "?11," +
//            "?12," +
//            "?13," +
//            "?14," +
//            "?15," +
//            "?16," +
//            "?17," +
//            "?18," +
//            "?19," +
//            "?20," +
//            "?21," +
//            "?22," +
//            "?23," +
//            "?24 "+ ")", nativeQuery = true)
//    void insertForSearchAPI(Long lojaProdutoId, Double lojaProdutoPreco, Long produtoId, String produtoNome, String produtoDesc, Integer produtoMesesGarantia, Long marcaId, Boolean marcaHabilitado, String marcaNome, String marcaDescricao, Long categoriaId, String categoriaNome, String categoriaDescricao,
//                            Long lojaId, String lojaNome, String lojaRazaoSocial, String lojaCnpj, String lojaRuaEnderecoComercial, String lojaNumeroEnderecoComercial, String lojaBairroEnderecoComercial, String lojaCidadeEnderecoComercial, String lojaEstadoEnderecoComercial,
//                            String lojaCepEnderecoComercial, String lojaTelefoneContato);
    //String lojaHoraFuncionamentoDiaSemana, Boolean lojaHoraFuncionamentoAberto, Date lojaHoraFuncionamentoDe, Date lojaHoraFuncionamentoAte


    @Query(value="select l from LojaProduto l where l.loja.id = :lojaId and l.produto.id = :produtoId")
    Optional<LojaProduto> getByLojaIdAndProdutoId(@Param("lojaId") Long lojaId, @Param("produtoId") Long produtoId);

    @Query(value="select lp from LojaProduto lp " +
            "where (:idMarca is null or lp.produto.marca.id = :idMarca) and " +
            "(:idCategoria is null or lp.produto.categoria.id = :idCategoria) and " +
            "(:idLoja is null or lp.loja.id = :idLoja) ")
    List<LojaProduto> searchByMarcaCategoriaAndLoja(@Param("idMarca") Long idMarca,
                                                @Param("idCategoria") Long idCategoria,
                                                @Param("idLoja") Long idLoja);

}
