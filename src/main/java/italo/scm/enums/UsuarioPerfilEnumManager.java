package italo.scm.enums;

import org.springframework.stereotype.Component;

import italo.scm.enums.tipos.UsuarioPerfil;

@Component
public class UsuarioPerfilEnumManager extends AbstractEnumManager<UsuarioPerfil> {

	@Override
	protected UsuarioPerfil[] values() {
		return UsuarioPerfil.values();
	}

	@Override
	protected String label(UsuarioPerfil perfil) {
		return perfil.label();
	}
	
}
