package italo.xclin.enums.tipos;

public enum UsuarioPerfil {
	RAIZ, ADMIN, DIRETOR, SUPORTE, PROFISSIONAL, RECEPCIONISTA, VISITANTE;
	
	public String label() {
		switch( this ) {
			case RAIZ: return "Raiz";
			case ADMIN: return "Administrador(a)";
			case DIRETOR: return "Diretor(a)";
			case SUPORTE: return "Suporte";
			case PROFISSIONAL: return "Profissional";
			case RECEPCIONISTA: return "Recepcionista";
			case VISITANTE: return "Visitante";
		}
		return null;
	}
	
}
