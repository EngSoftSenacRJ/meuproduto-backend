package senac.edu.engsoft.meuproduto.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

	private Long idCategoria;
	private Long idMarca;
	private Long idLoja;
	private String nomeProduto;

	private Double latitude;
	private Double longitude;
	private Double distanceKM;

}
