package italo.xclin.enums.tipos;

public enum Nacionalidade {
	BRASILEIRO, ESTRANGEIRO;
	
	public String label() {
		switch( this ) {
			case BRASILEIRO: return "Brasileiro";
			case ESTRANGEIRO: return "Estrangeiro";
		}
		return null;
	}
}

