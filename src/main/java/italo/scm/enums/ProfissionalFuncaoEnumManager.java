package italo.scm.enums;

import italo.scm.enums.tipos.ProfissionalFuncao;

public class ProfissionalFuncaoEnumManager extends AbstractEnumManager<ProfissionalFuncao> {

	@Override
	protected ProfissionalFuncao[] values() {
		return ProfissionalFuncao.values();
	}

	@Override
	protected String label(ProfissionalFuncao funcao) {
		return funcao.label();
	}
	
}