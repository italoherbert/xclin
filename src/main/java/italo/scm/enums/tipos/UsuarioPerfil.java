package italo.scm.enums.tipos;

public enum UsuarioPerfil {
	RAIZ, ADMIN, DIRETOR, MEDICO, RECEPCIONISTA;
	
	public String label() {
		switch( this ) {
			case RAIZ: return "Raiz";
			case ADMIN: return "Admin";
			case DIRETOR: return "Diretor";
			case MEDICO: return "Médico";
			case RECEPCIONISTA: return "Recepcionista";
		}
		return null;
	}
	
}
