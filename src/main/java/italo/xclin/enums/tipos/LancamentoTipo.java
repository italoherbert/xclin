package italo.xclin.enums.tipos;

public enum LancamentoTipo {
	CREDITO, DEBITO;
	
	public String label() {
		switch( this ) {
			case CREDITO: return "Receita";
			case DEBITO: return "Despesa";
		}
		return null;
	}
}
