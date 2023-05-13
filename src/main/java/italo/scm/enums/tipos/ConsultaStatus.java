package italo.scm.enums.tipos;

public enum ConsultaStatus {
	REGISTRADA, EM_ATENDIMENTO, CANCELADA, FINALIZADA;
	
	public String label() {
		switch( this ) {
			case REGISTRADA: return "Registrada";
			case EM_ATENDIMENTO: return "Em atendimento";
			case CANCELADA: return "Cancelada";
			case FINALIZADA: return "Finalizada";								
		}
		return null;
	}
}
