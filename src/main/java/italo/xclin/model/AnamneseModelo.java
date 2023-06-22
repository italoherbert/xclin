package italo.xclin.model;

import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="anamnese_modelo")
public class AnamneseModelo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@ManyToOne
	@JoinColumn(name="profissional_id")	
	private Profissional profissional;

	@OneToMany(mappedBy = "anamneseModelo", cascade = CascadeType.ALL)
	private List<AnamneseModeloPergunta> perguntas;
	
}
