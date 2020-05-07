package senac.edu.engsoft.meuproduto.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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

	public SearchRequestDTO() {
	}

	public SearchRequestDTO(Long idCategoria, Long idMarca, Long idLoja, String nomeProduto, Double latitude, Double longitude, Double distanceKM) {
		this.idCategoria = idCategoria;
		this.idMarca = idMarca;
		this.idLoja = idLoja;
		this.nomeProduto = nomeProduto;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distanceKM = distanceKM;
	}

	public SearchRequestDTO(Optional<Long> idCategoria, Optional<Long> idMarca, Optional<Long> idLoja, Optional<String> nomeProduto, Optional<Double> latitude, Optional<Double> longitude, Optional<Double> distanceKM) {
		this.idCategoria = idCategoria.isPresent() ? idCategoria.get() : null;
		this.idMarca = idMarca.isPresent() ? idMarca.get() : null;
		this.idLoja = idLoja.isPresent() ? idLoja.get() : null;
		this.nomeProduto = nomeProduto.isPresent() ? nomeProduto.get() : null;
		this.latitude = latitude.isPresent() ? latitude.get() : null;
		this.longitude = longitude.isPresent() ? longitude.get() : null;
		this.distanceKM = distanceKM.isPresent() ? distanceKM.get() : null;
	}
}
