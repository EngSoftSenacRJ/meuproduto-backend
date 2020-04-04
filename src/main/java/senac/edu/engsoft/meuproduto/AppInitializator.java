package senac.edu.engsoft.meuproduto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.service.ParametroService;
import senac.edu.engsoft.meuproduto.service.ProdutoService;

import javax.annotation.PostConstruct;

@Component
public class AppInitializator {

	private static final Logger logger = LogManager.getLogger(AppInitializator.class);

	private final ParametroService parametroService;
	private final ProdutoService produtoService;

	@Autowired
	public AppInitializator(ParametroService parametroService,
							ProdutoService produtoService) {
		logger.info("Carregando Parâmetros do Sistema");
		this.parametroService = parametroService;
		this.produtoService = produtoService;
	}

	@PostConstruct
	private void init() throws InterruptedException {
//		try{
//			produtoService.indexAll();
//		}catch (InterruptedException e){
//			logger.error("Error indexing for hibernate search. Error: {}", e);
//		}

		parametroService.carregarParametros();
	}
	
}
