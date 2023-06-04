package italo.xclin.enums;

import org.springframework.stereotype.Component;

import italo.xclin.enums.tipos.ConsultaStatus;

@Component
public class ConsultaStatusEnumManager extends AbstractEnumManager<ConsultaStatus> {

	@Override
	protected ConsultaStatus[] values() {
		return ConsultaStatus.values();
	}

	@Override
	protected String label(ConsultaStatus status) {
		return status.label();
	} 

}