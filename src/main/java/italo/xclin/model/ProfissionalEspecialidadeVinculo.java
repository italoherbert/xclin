package italo.xclin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="profissional_especialidade_vinculo")
public class ProfissionalEspecialidadeVinculo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private double consultaValor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="profissional_id")
	private Profissional profissional;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="especialidade_id")
	private Especialidade especialidade;
	
}