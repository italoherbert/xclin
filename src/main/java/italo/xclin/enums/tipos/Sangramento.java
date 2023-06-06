package italo.xclin.enums.tipos;

public enum Sangramento {
	NORMAL, EXCESSIVO;
	
	public String label() {
		switch( this ) {
			case NORMAL: return "Normal";
			case EXCESSIVO: return "Excessivo";
		}
		return null;
	}
	
}
