package italo.scm.model;

import java.util.List;

import italo.scm.enums.tipos.ProfissionalFuncao;
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
	
	private long tempoConsulta;
	
	private long tempoConsultaRetorno;
	
	private double valorConsulta;
	
	private ProfissionalFuncao funcao;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy="diretor", cascade=CascadeType.ALL)
	private List<ProfissionalClinicaVinculo> profissionalClinicaVinculos;
	
}
