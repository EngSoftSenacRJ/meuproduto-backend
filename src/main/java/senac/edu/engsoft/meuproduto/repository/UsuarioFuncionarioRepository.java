package senac.edu.engsoft.meuproduto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import senac.edu.engsoft.meuproduto.model.UsuarioFuncionario;

@Repository
public interface UsuarioFuncionarioRepository extends CrudRepository<UsuarioFuncionario, Long>{

	@Query(value="select l from UsuarioFuncionario l where l.nome = :nome")
	Optional<UsuarioFuncionario> getByNome(@Param("nome") String nome);

	
}
