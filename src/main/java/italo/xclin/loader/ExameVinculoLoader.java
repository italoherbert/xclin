package italo.xclin.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.ExameVinculo;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.save.ExameSaveRequest;
import italo.xclin.model.response.ExameVinculoResponse;

@Component
public class ExameVinculoLoader {
	
	@Autowired
	private Converter converter;

	public void loadBean( ExameVinculo exame, ExameSaveRequest request ) throws LoaderException {
		exame.setNome( request.getNome() );
		exame.setDescricao( request.getDescricao() );
		exame.setValor( request.getValor() );
		
		try {
			exame.setDataExame( converter.stringToDataHora( request.getDataExame() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		} 
	}
	
	public void loadResponse( ExameVinculoResponse resp, ExameVinculo exame ) {
		resp.setId( exame.getId() );
		resp.setNome( exame.getNome() );
		resp.setDescricao( exame.getDescricao() );
		resp.setValor( exame.getValor() ); 
		resp.setDataExame( converter.dataHoraToString( exame.getDataExame() ) ); 
	}
	
	public ExameVinculo novoBean( Paciente paciente ) {
		ExameVinculo exame = new ExameVinculo();
		exame.setPaciente( paciente ); 
		return exame;
	}
	
	public ExameVinculoResponse novoResponse() {
		return new ExameVinculoResponse();
	}
	
}