package senac.edu.engsoft.meuproduto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.MarcaProduto;

import java.util.Optional;

@Repository
public interface MarcaProdutoRepository extends CrudRepository<MarcaProduto, Long>{

    @Query(value="select l from MarcaProduto l where l.nome = :nome")
    Optional<MarcaProduto> getByNome(@Param("nome") String nome);
}
