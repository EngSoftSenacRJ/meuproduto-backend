//package senac.edu.engsoft.meuproduto.service.repository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import senac.edu.engsoft.meuproduto.model.LojaProdutoAudit;
//
//@Repository
//public interface LojaProdutoAuditRepository extends CrudRepository<LojaProdutoAudit, Long>{
//
//    @Query(value="select lpa from LojaProdutoAudit lpa " +
//            "where (:idLoja is null or lpa.idLoja = :idLoja) and " +
//            "(:idProduto is null or lpa.idProduto = :idProduto) ")
//    Iterable<LojaProdutoAudit> getAudit(@Param("idLoja") Long idLoja,
//                                        @Param("idProduto") Long idProduto);
//
//}
