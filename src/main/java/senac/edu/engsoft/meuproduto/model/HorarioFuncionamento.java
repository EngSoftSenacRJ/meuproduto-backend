package senac.edu.engsoft.meuproduto.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Estático, o usuário só poderá manter o horário de funcionamento e a flag de abertura
 */

@Entity
@Table(name="TB_HORARIO_FUNCIONAMENTO")
public class HorarioFuncionamento {
	
	enum DiaSemana{
		SEGUNDA_FEIRA("Segunda-Feira"),
		TERCA_FEIRA("Terça-Feira"),
		QUARTA_FEIRA("Quarta-Feira"),
		QUINTA_FEIRA("Quinta-Feira"),
		SEXTA_FEIRA("Sexta-Feira"),
		SABADO("Sábado"),
		DOMINGO("Domingo");
		
		private String diaSemanaDesc;

		private DiaSemana(String diaSemanaDesc) {
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

	public HorarioFuncionamento(DiaSemana diaSemana, Boolean aberto, LocalTime horarioFuncionamentoDe,
			LocalTime horarioFuncionamentoAte, Loja loja) {
		super();
		this.diaSemana = diaSemana;
		this.aberto = aberto;
		this.horarioFuncionamentoDe = horarioFuncionamentoDe;
		this.horarioFuncionamentoAte = horarioFuncionamentoAte;
		this.loja = loja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Boolean getAberto() {
		return aberto;
	}

	public void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public LocalTime getHorarioFuncionamentoDe() {
		return horarioFuncionamentoDe;
	}

	public void setHorarioFuncionamentoDe(LocalTime horarioFuncionamentoDe) {
		this.horarioFuncionamentoDe = horarioFuncionamentoDe;
	}

	public LocalTime getHorarioFuncionamentoAte() {
		return horarioFuncionamentoAte;
	}

	public void setHorarioFuncionamentoAte(LocalTime horarioFuncionamentoAte) {
		this.horarioFuncionamentoAte = horarioFuncionamentoAte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorarioFuncionamento other = (HorarioFuncionamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
