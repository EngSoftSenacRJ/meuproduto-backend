package senac.edu.engsoft.meuproduto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import senac.edu.engsoft.meuproduto.model.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends CrudRepository<CategoriaProduto, Long>{

}
