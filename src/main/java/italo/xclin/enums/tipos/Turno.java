package italo.xclin.enums.tipos;

public enum Turno {
	MANHA, TARDE, NOITE;
	
	public String label() {
		switch( this ) {
			case MANHA: return "Manhã";
			case TARDE: return "Tarde";
			case NOITE: return "Noite";			
		}
		return null;
	}
}
