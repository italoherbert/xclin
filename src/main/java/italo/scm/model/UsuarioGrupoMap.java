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
@Table(name="usuario_role_map")
public class UsuarioGrupoMap {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne( fetch=FetchType.EAGER ) 
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne( fetch=FetchType.EAGER ) 
	@JoinColumn(name="grupo_id")
	private UsuarioGrupo grupo;
	
}
