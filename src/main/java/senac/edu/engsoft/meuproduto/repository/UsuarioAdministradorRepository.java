package senac.edu.engsoft.meuproduto.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

import java.util.Optional;

@Repository
public interface UsuarioAdministradorRepository extends CrudRepository<UsuarioAdministrador, Long>{

	@Query(value="select l from UsuarioAdministrador l where l.username = :username")
	Optional<UsuarioAdministrador> getByUsername(@Param("username") String username);

	@Query(value="select l from UsuarioAdministrador l where l.nome = :nome")
	Optional<UsuarioAdministrador> getByNome(@Param("nome") String nome);

	@Query(value="select l from UsuarioAdministrador l where l.cpf = :cpf")
	Optional<UsuarioAdministrador> getByCpf(@Param("cpf") Long cpf);

	@Query(value="select l from UsuarioAdministrador l where l.username = :email")
	Optional<UsuarioAdministrador> getByEmail(String email);

	
}
