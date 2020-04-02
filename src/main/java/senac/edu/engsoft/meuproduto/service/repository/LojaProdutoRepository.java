package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.Loja;
import senac.edu.engsoft.meuproduto.model.LojaProduto;

@Repository
public interface LojaProdutoRepository extends CrudRepository<LojaProduto, Long>{
	
}
