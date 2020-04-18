package senac.edu.engsoft.meuproduto.service;

import senac.edu.engsoft.meuproduto.model.LojaProduto;
import senac.edu.engsoft.meuproduto.model.dto.SearchRequestDTO;

public interface SearchService {

	Iterable<LojaProduto> search(SearchRequestDTO searchRequestDTO);
    void indexAll() throws InterruptedException;
}
