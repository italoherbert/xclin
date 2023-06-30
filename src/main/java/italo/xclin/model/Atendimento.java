package italo.xclin.model;

import java.util.Date;

import italo.xclin.enums.tipos.AtendimentoStatus;
import italo.xclin.enums.tipos.Turno;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtendimento;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinalizacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaveObservacoes;
		
	@Enumerated(EnumType.STRING)
	private AtendimentoStatus status;
	
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	private boolean retorno;
	
	private boolean paga;
		
	private double valor;
	
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name="profissional_id")
	private Profissional profissional;
		
	@ManyToOne
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name="clinica_id")
	private Clinica clinica;
	
	@ManyToOne
	@JoinColumn(name="especialidade_id")
	private Especialidade especialidade;
		
}
