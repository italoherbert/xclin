package italo.xclin.enums.tipos;

public enum UsuarioPerfil {
	RAIZ, ADMIN, SUPORTE, DIRETOR, PROFISSIONAL, RECEPCIONISTA;
	
	public String label() {
		switch( this ) {
			case RAIZ: return "Raiz";
			case ADMIN: return "Administrador(a)";
			case SUPORTE: return "Suporte";
			case DIRETOR: return "Diretor(a)";
			case PROFISSIONAL: return "Profissional";
			case RECEPCIONISTA: return "Recepcionista";
		}
		return null;
	}
	
}
