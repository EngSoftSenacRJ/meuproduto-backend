package senac.edu.engsoft.meuproduto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.service.ParametroService;
import senac.edu.engsoft.meuproduto.service.SearchService;

import javax.annotation.PostConstruct;

@Component
public class AppInitializator {

	private static final Logger logger = LogManager.getLogger(AppInitializator.class);

	private final ParametroService parametroService;
	private final SearchService searchService;

	@Autowired
	public AppInitializator(ParametroService parametroService,
							SearchService searchService) {
		this.parametroService = parametroService;
		this.searchService = searchService;
	}

	@PostConstruct
	private void init() throws InterruptedException {
		try{
			logger.info("Indexando entidades do Sistema para busca");
			searchService.indexAll();
		}catch (InterruptedException e){
			logger.error("Error indexing for hibernate search. Error: {}", e);
		}

		logger.info("Carregando Parâmetros do Sistema");
		parametroService.carregarParametros();
	}
	
}
