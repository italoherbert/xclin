package italo.xclin.enums.tipos;

public enum AtendimentoStatus {
	REGISTRADO, ESPERANDO, INICIADO, CANCELADO, FINALIZADO;
	
	public String label() {
		switch( this ) {
			case REGISTRADO: return "Agendado";
			case ESPERANDO: return "Esperando";
			case INICIADO: return "Iniciado";
			case CANCELADO: return "Cancelado";
			case FINALIZADO: return "Finalizado";
		}
		return null;
	}
}
