package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.LojaProdutoSearch;

@Repository
public interface LojaProdutoSearchRepository extends CrudRepository<LojaProdutoSearch, Long>{

}
