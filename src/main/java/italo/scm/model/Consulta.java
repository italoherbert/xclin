package italo.scm.model;

import java.util.Date;

import italo.scm.enums.tipos.ConsultaStatus;
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
	
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataConsulta;
	
	@Enumerated(EnumType.STRING)
	private ConsultaStatus status;
	
	private boolean retorno;
	
	private boolean paga;
	
	private int tempoEstimado;
	
	private double valor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="profissional_id")
	private Profissional profissional;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	@ManyToOne(optional = true)
	@JoinColumn(name="recebedor_id") 
	private Usuario recebedor;
	
}