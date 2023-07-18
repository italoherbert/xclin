package italo.xclin.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="procedimento")
public class Procedimento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String descricao;

	@ManyToOne
	@JoinColumn(name="clinica_id")
	private Clinica clinica;
	
	@OneToMany(mappedBy="procedimento", cascade=CascadeType.REMOVE)
	private List<ProfissionalProcedimentoVinculo> profissionalProcedimentoVinculos;
	
}
