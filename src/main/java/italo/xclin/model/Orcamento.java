package italo.xclin.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name="orcamento")
public class Orcamento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean temConsulta;
	
	private boolean pago;
		
	private double valorTotal;
	
	private double valorPago;
	
	@OneToOne(mappedBy="orcamento", cascade=CascadeType.ALL, optional = true)
	private Consulta consulta;
	
	@OneToMany(mappedBy="orcamento", cascade=CascadeType.ALL)
	private List<ExameItem> exames;
	
	@OneToMany(mappedBy="orcamento", cascade=CascadeType.ALL)
	private List<ProcedimentoItem> procedimentos;
	
}
