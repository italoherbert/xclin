package italo.scm.enums.tipos;

public enum EstadoCivil {
	CASADO, SOLTEIRO, VIUVO, DIVORCIADO, OUTRO;
	
	public String label() {
		switch( this ) {
			case CASADO: return "Casado";
			case SOLTEIRO: return "Solteiro";
			case VIUVO: return "Viuvo";			
			case DIVORCIADO: return "Divorciado";			
			case OUTRO: return "Outro";			
		}
		return null;
	}
}
