package senac.edu.engsoft.meuproduto.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name="TB_HORARIO_FUNCIONAMENTO")
public class HorarioFuncionamento {

	public enum DiaSemana{
		SEGUNDA_FEIRA("Segunda-Feira"),
		TERCA_FEIRA("Terça-Feira"),
		QUARTA_FEIRA("Quarta-Feira"),
		QUINTA_FEIRA("Quinta-Feira"),
		SEXTA_FEIRA("Sexta-Feira"),
		SABADO("Sábado"),
		DOMINGO("Domingo");

		private String diaSemanaDesc;

		DiaSemana(String diaSemanaDesc) {
			this.diaSemanaDesc = diaSemanaDesc;
		}

		public String getDiaSemanaDesc() {
			return diaSemanaDesc;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="DIA_SEMANA")
	@Enumerated(EnumType.STRING)
	private DiaSemana diaSemana;

	@Column(name="ABERTO")
	private Boolean aberto;

	@Column(name="HORARIO_FUNCIONAMENTO_DE")
	private LocalTime horarioFuncionamentoDe;

	@Column(name="HORARIO_FUNCIONAMENTO_ATE")
	private LocalTime horarioFuncionamentoAte;

	@JoinColumn(name = "ID_LOJA")
	@ManyToOne(fetch = FetchType.LAZY)
	private Loja loja;

	public HorarioFuncionamento() {
		super();
	}

}
