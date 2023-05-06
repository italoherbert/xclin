package italo.scm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="clinica")
public class Clinica {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario_id")
	private Usuario criador;
	
}
