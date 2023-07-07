package italo.xclin.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="clinica")
public class Clinica {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario criador;
			
	@OneToMany(mappedBy="clinica", cascade=CascadeType.ALL)
	private List<UsuarioClinicaVinculo> usuarioClinicaVinculos;
	
	@OneToMany(mappedBy="clinica", cascade=CascadeType.ALL)
	private List<Paciente> pacientes;
	
	@OneToMany(mappedBy="clinica", cascade=CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
	@OneToMany(mappedBy="clinica", cascade=CascadeType.ALL)
	private List<Especialidade> especialidades;
	
	@OneToMany(mappedBy="clinica", cascade=CascadeType.ALL)
	private List<Exame> exames;
		
}
