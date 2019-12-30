package senac.edu.engsoft.meuproduto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

@Repository
public interface UsuarioAdministradorRepository extends CrudRepository<UsuarioAdministrador, Long>{

	@Query(value="select l from UsuarioAdministrador l where l.nome = :nome")
	Optional<UsuarioAdministrador> getByNome(@Param("nome") String nome);

	
}
