package italo.xclin.model;

import italo.xclin.enums.tipos.PerguntaTipo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="anamnese_modelo_pergunta")
public class AnamneseModeloPergunta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String pergunta;
	
	@Enumerated(EnumType.STRING)
	private PerguntaTipo tipo;
	
	private String enumValues;
	
	@ManyToOne
	@JoinColumn(name="anamnese_modelo_id")
	private AnamneseModelo anamneseModelo;
	
}
