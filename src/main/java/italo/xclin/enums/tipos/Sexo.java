package italo.xclin.enums.tipos;

public enum Sexo {
	MASCULINO, FEMININO, OUTRO;
	
	public String label() {
		switch( this ) {
			case MASCULINO: return "Masculino";
			case FEMININO: return "Feminino";
			case OUTRO: return "Outro";			
		}
		return null;
	}
}
