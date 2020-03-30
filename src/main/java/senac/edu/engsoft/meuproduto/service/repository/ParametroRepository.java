package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.Parametro;

import java.util.List;

@Repository
public interface ParametroRepository extends CrudRepository<Parametro, Long>{

	@Query(value="select o from Parametro o")
	List<Parametro> getAll();

	
}
