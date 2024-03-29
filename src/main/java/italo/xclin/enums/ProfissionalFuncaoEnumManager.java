package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.ProfissionalFuncao;

@Component
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