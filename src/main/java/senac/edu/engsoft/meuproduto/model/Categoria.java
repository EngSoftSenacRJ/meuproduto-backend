//package senac.edu.engsoft.meuproduto.model;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.util.Objects;
//
//@Data
//@Getter
//@Setter
//@Entity
//@Table(name = "TB_CATEGORIA")
//public class Categoria {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@NotNull(message = "Nome é obrigatório")
//	@NotBlank(message = "Nome é obrigatório")
//	@Column(name = "NOME")
//	private String nome;
//
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (!(o instanceof Categoria)) return false;
//		Categoria categoria = (Categoria) o;
//		return Objects.equals(id, categoria.id);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//}
