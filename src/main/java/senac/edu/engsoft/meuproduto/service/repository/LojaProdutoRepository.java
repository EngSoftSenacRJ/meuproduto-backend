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
