package italo.xclin.enums.tipos;

public enum ConsultaStatus {
	REGISTRADA, INICIADA, CANCELADA, FINALIZADA;
	
	public String label() {
		switch( this ) {
			case REGISTRADA: return "Registrada";
			case INICIADA: return "Iniciada";
			case CANCELADA: return "Cancelada";
			case FINALIZADA: return "Finalizada";
		}
		return null;
	}
}
