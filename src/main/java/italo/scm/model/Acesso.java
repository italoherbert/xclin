package italo.scm.model;

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
@Table(name="acesso")
public class Acesso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean leitura;
	
	private boolean escrita;
	
	private boolean remocao;
	
	@ManyToOne( fetch=FetchType.EAGER ) 
	@JoinColumn(name="grupo_id")
	private UsuarioGrupo grupo;
	
	@ManyToOne( fetch=FetchType.EAGER ) 
	@JoinColumn(name="recurso_id")
	private Recurso recurso;
	
}
