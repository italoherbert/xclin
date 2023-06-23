package italo.xclin.enums.tipos;

public enum PerguntaTipo {
	STRING, BOOLEAN, ENUM;
	
	public String label() {
		switch( this ) {
			case STRING: return "Texto";
			case BOOLEAN: return "Sim/Não";
			case ENUM: return "Seleção";
		}
		return null;
	}
}
