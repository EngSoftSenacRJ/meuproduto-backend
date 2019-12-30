package senac.edu.engsoft.meuproduto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import senac.edu.engsoft.meuproduto.model.Loja;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long>{

	@Query(value="select l from Loja l where l.nome = :nome")
	Optional<Loja> getByNome(@Param("nome") String nome);

	
}
