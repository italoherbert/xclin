package italo.scm.enums.tipos;

public enum ConsultaStatus {
	REGISTRADA, CANCELADA, FINALIZADA;
	
	public String label() {
		switch( this ) {
			case REGISTRADA: return "Registrada";
			case CANCELADA: return "Cancelada";
			case FINALIZADA: return "Finalizada";								
		}
		return null;
	}
}
