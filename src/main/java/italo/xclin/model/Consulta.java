package italo.xclin.model;

import java.util.Date;

import italo.xclin.enums.tipos.ConsultaStatus;
import italo.xclin.enums.tipos.Turno;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name="consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtendimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinalizacao;
		
	@Enumerated(EnumType.STRING)
	private ConsultaStatus status;
	
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	private boolean retorno;
	
	private boolean paga;
	
	private double valor;
	
	private String observacoes;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="profissional_id")
	private Profissional profissional;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="clinica_id")
	private Clinica clinica;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="especialidade_id")
	private Especialidade especialidade;
		
}
