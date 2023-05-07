package italo.scm.model;

import java.util.List;

import italo.scm.enums.tipos.UsuarioPerfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<UsuarioGrupoVinculo> usuarioGrupoVinculos;
	
	@ManyToOne(fetch=FetchType.EAGER, optional = true)
	@JoinColumn(name="clinica_id")
	private Clinica clinica;
	
}
