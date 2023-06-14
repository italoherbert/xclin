package italo.xclin.enums.tipos;

public enum LancamentoTipo {
	CREDITO, DEBITO;
	
	public String label() {
		switch( this ) {
			case CREDITO: return "Entrada";
			case DEBITO: return "Saída";
		}
		return null;
	}
}
