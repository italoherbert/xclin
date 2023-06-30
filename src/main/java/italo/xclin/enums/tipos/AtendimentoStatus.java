package italo.xclin.enums.tipos;

public enum AtendimentoStatus {
	REGISTRADO, INICIADO, CANCELADO, FINALIZADO;
	
	public String label() {
		switch( this ) {
			case REGISTRADO: return "Registrado";
			case INICIADO: return "Iniciado";
			case CANCELADO: return "Cancelado";
			case FINALIZADO: return "Finalizado";
		}
		return null;
	}
}
