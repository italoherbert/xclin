package italo.scm.enums;

import org.springframework.stereotype.Component;

import italo.scm.enums.tipos.Sexo;

@Component
public class SexoEnumManager extends AbstractEnumManager<Sexo> {

	@Override
	protected Sexo[] values() {
		return Sexo.values();
	}

	@Override
	protected String label(Sexo sexo) {
		return sexo.label();
	}
	
}
