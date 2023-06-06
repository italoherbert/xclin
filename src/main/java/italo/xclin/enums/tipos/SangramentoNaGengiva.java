package italo.xclin.enums.tipos;

public enum SangramentoNaGengiva {
	NAO, SIM, AS_VEZES, DURANTE_A_HIGIENE;
	
	public String label() {
		switch( this ) {
			case NAO: return "Não";
			case SIM: return "Sim";
			case AS_VEZES: return "As vezes";
			case DURANTE_A_HIGIENE: return "Durante a higiene";
		}
		return null;
	}
}
