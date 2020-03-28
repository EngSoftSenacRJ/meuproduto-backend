package senac.edu.engsoft.meuproduto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senac.edu.engsoft.meuproduto.service.ParametroService;

import javax.annotation.PostConstruct;

@Component
public class AppInitializator {

	private static final Logger logger = LogManager.getLogger(AppInitializator.class);
	
	private final ParametroService parametroService;

	@Autowired
	public AppInitializator(ParametroService parametroService) {
		logger.info("Carregando Parâmetros do Sistema");
		this.parametroService = parametroService;
	}

	@PostConstruct
	private void init(){
		parametroService.carregarParametros();
	}
	
}
