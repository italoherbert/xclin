package italo.xclin.enums;

public enum PerguntaTipo {
	STRING, BOOLEAN, ENUM;
	
	public String label() {
		switch( this ) {
			case STRING: return "String";
			case BOOLEAN: return "Boolean";
			case ENUM: return "Enumeração";
		}
		return null;
	}
}
