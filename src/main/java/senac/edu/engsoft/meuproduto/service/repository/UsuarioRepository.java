package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	@Query(value="select l from Usuario l where l.cpf = :cpf")
	Optional<Usuario> getByCpf(@Param("cpf") Long cpf);

	@Query(value="select l from Usuario l where l.username = :username")
	Optional<Usuario> getByEmail(String username);


}
