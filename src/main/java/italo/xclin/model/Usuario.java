package italo.xclin.model;

import java.util.List;

import italo.xclin.enums.tipos.UsuarioPerfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private UsuarioPerfil perfil;
	
	@OneToOne
	@JoinColumn(name="criador_id")
	private Usuario criador;

	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<UsuarioGrupoVinculo> usuarioGrupoVinculos;
		
}
