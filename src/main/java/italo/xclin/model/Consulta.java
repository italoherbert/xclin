package italo.xclin.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
				
	private double valor;
	
	private boolean concluida;
	
	@OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
	private ConsultaEspecialidadeVinculo consultaEspecialidadeVinculo;
	
	@OneToOne
	@JoinColumn(name="orcamento_id")
	private Orcamento orcamento;
	
}
