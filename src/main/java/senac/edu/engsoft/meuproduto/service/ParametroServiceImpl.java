package senac.edu.engsoft.meuproduto.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import senac.edu.engsoft.meuproduto.model.Parametro;
import senac.edu.engsoft.meuproduto.model.ParametroEnum;
import senac.edu.engsoft.meuproduto.service.repository.ParametroRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParametroServiceImpl implements ParametroService {
	
	private static final Logger logger = LogManager.getLogger(ParametroServiceImpl.class);
	
	private final ParametroRepository parametroRepository;

	public Map<ParametroEnum, Parametro> parametroMap;

	public Map<ParametroEnum, Parametro> getParametroMap() {
		if(parametroMap == null)
			return new HashMap<>();
		return parametroMap;
	}

	public ParametroServiceImpl(ParametroRepository parametroRepository) {
		super();
		this.parametroRepository = parametroRepository;
	}
	
	@Override
	public void carregarParametros() {
		try {
			List<Parametro> parametroList = parametroRepository.getAll();
			if(parametroList == null) {
				return;
			}
			
			for(Parametro parametro : parametroList) {
				ParametroEnum parametroEnum = ParametroEnum.valueOf(parametro.getNome());

				parametroMap.put(parametroEnum, parametro);
				logger.info("Parâmetro "+parametroEnum.name()+" carregado, valor: "+parametro.getValor());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
