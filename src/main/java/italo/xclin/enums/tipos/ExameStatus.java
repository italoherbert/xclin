package italo.xclin.enums.tipos;

public enum ExameStatus {
	REGISTRADO, CANCELADO, FINALIZADO;
	
	public String label() {
		switch( this ) {
			case REGISTRADO: return "Registrado";
			case CANCELADO: return "Cancelado";
			case FINALIZADO: return "Finalizado";
		}
		return null;
	}
}
