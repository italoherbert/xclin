package italo.xclin.enums.tipos;

public enum LancamentoTipo {
	DEBIDO, CREDITO;
	
	public String label() {
		switch( this ) {
			case DEBIDO: return "Saída";
			case CREDITO: return "Entrada";
		}
		return null;
	}
}
