package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.Loja;

import java.util.List;
import java.util.Optional;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long>{

	@Query(value="select l from Loja l where l.nome = :nome")
	Optional<Loja> getByNome(@Param("nome") String nome);

	@Query(value="select l from Loja l where l.emailUsuarioCriadorLoja = :emailUsuarioCriadorLoja")
	List<Loja> getLojasByUsernameAdministrador(@Param("emailUsuarioCriadorLoja") String emailUsuarioCriadorLoja);
	
}
