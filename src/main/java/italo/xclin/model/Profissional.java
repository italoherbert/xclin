package italo.xclin.model;

import java.util.List;

import italo.xclin.enums.tipos.ProfissionalFuncao;
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
@Table(name="profissional")
public class Profissional {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nome;
			
	private ProfissionalFuncao funcao;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy="profissional", cascade=CascadeType.ALL)
	private List<ProfissionalEspecialidadeVinculo> profissionalEspecialidadeVinculos;		
		
	@OneToMany(mappedBy="profissional", cascade=CascadeType.ALL)
	private List<ProfissionalExameVinculo> profissionalClinicaExameVinculos;
	
	@OneToMany(mappedBy="profissional", cascade=CascadeType.REMOVE)
	private List<AnamneseModelo> anamneseModelos;
	
	@OneToMany(mappedBy="profissional", cascade=CascadeType.ALL)
	private List<Atendimento> atendimentos;
	
}
