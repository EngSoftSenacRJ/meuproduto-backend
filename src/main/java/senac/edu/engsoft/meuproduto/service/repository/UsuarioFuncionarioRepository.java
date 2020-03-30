package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;

import java.util.Optional;

@Repository
public interface UsuarioFuncionarioRepository extends CrudRepository<UsuarioFuncionario, Long>{

	@Query(value="select l from UsuarioFuncionario l where l.nome = :nome ")
	Optional<UsuarioFuncionario> getByNome(@Param("nome") String nome);

	@Query(value="select l from UsuarioFuncionario l where l.enabled = true ")
    Iterable<UsuarioFuncionario> findAllEnabled();

	@Query(value="select l from UsuarioFuncionario l where l.usernameAdministrador = :usernameAdministrador and l.enabled = true")
	Iterable<UsuarioFuncionario> getByUsernameAdministrador(@Param("usernameAdministrador") String usernameAdministrador);
}
