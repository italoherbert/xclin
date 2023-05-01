package italo.scm.enums;

import italo.scm.enums.tipos.UsuarioPerfil;

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
