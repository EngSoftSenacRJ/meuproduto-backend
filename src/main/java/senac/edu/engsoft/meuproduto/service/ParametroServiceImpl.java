package senac.edu.engsoft.meuproduto.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import senac.edu.engsoft.meuproduto.model.Parametro;
import senac.edu.engsoft.meuproduto.model.ParametroEnum;
import senac.edu.engsoft.meuproduto.repository.ParametroRepository;

@Service
public class ParametroServiceImpl implements ParametroService {
	
	private static final Logger logger = LogManager.getLogger(ParametroServiceImpl.class);
	
	private final ParametroRepository parametroRepository;
	
	public static Map<ParametroEnum, Parametro> parametroMap;
	
	public ParametroServiceImpl(ParametroRepository parametroRepository) {
		super();
		this.parametroRepository = parametroRepository;
	}
	
	@Override
	public List<Parametro> carregarParametros() {
		try {
			List<Parametro> parametroList = parametroRepository.getAll();
			if(parametroList == null) {
			
			}
			
			for(Parametro parametro : parametroList) {
				ParametroEnum parametroEnum = ParametroEnum.valueOf(parametro.getNome());
				
				if(parametroEnum == null) {
					logger.warn("Parâmetro "+parametro.getNome()+" não mapeado no Sistema");
					//TODO: Throw Exception para nao permitir a inicialização da aplicação
				}
				
				parametroMap.put(parametroEnum, parametro);
				logger.info("Parâmetro "+parametroEnum.name()+" carregado, valor: "+parametro.getValor());
			}
			
		} catch (Exception e) {
			//TODO: Tratar erro
			e.printStackTrace();
		}
		return null;
	}
	
}
