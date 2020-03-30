package senac.edu.engsoft.meuproduto.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{
}
