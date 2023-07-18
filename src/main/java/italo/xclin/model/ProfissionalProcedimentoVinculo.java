package italo.xclin.model;

import jakarta.persistence.Entity;
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
@Table(name="profissional_procedimento_vinculo")
public class ProfissionalProcedimentoVinculo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private double procedimentoValor;
	
	@ManyToOne
	@JoinColumn(name="profissional_id")
	private Profissional profissional;
	
	@ManyToOne
	@JoinColumn(name="procedimento_id") 
	private Procedimento procedimento;
	
}
