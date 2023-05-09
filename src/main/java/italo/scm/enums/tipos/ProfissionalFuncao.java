package italo.scm.enums.tipos;

public enum ProfissionalFuncao {
	MEDICO, ENFERMEIRO, DENTISTA, PROTETICO;
	
	public String label() {
		switch( this ) {
			case MEDICO: return "Médico(a)";
			case ENFERMEIRO: return "Enfermeiro(a)";
			case DENTISTA: return "Cirurgião dentista";
			case PROTETICO: return "Protético(a)";
		}
		return null;
	}
	
}
