package italo.xclin.enums.tipos;

public enum Cicatrizacao {
	NORMAL, COMPLICADA;
	
	public String label() {
		switch( this ) {
			case NORMAL: return "Normal";
			case COMPLICADA: return "Complicada";
		}
		return null;
	}
}
