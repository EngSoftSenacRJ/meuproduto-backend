package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

    @Query(value = "select p from Produto p where p.nome = :nome")
    public Produto getByNome(@Param("nome") String nome);

    @Query(value = "select p from Produto p where p.categoria.id = :categoriaId and p.marca.id = :marcaId")
    public Iterable<Produto> getByCategoriaIdAndMarcaId(@Param("categoriaId") Long categoriaId, @Param("marcaId") Long marcaId);
}
