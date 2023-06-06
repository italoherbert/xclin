package italo.xclin.enums.tipos;

public enum PressaoArterial {
	NORMAL, BAIXA, ALTA, CONTROLADA;
	
	public String label() {
		switch( this ) {
			case NORMAL: return "Normal";
			case BAIXA: return "Baixa";
			case ALTA: return "Alta";
			case CONTROLADA: return "Controlada com medicamentos";
		}
		return null;
	}
}
