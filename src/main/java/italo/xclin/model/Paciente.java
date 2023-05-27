package italo.xclin.model;

import java.util.Date;
import java.util.List;

import italo.xclin.enums.tipos.EstadoCivil;
import italo.xclin.enums.tipos.Nacionalidade;
import italo.xclin.enums.tipos.Sexo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private String cpf;
	
	private String rg;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Enumerated(EnumType.STRING)
	private Nacionalidade nacionalidade;
	
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;
	
	private String ocupacao;
	
	private String observacoes;
		
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinica_id")
	private Clinica clinica;
	
	@OneToMany(mappedBy="paciente", cascade=CascadeType.ALL)
	private List<Consulta> consultas;
	
}
