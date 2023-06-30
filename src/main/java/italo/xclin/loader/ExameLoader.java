package italo.xclin.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.xclin.exception.ConverterException;
import italo.xclin.exception.LoaderException;
import italo.xclin.logica.Converter;
import italo.xclin.model.Exame;
import italo.xclin.model.Paciente;
import italo.xclin.model.request.save.ExameSaveRequest;
import italo.xclin.model.response.ExameResponse;

@Component
public class ExameLoader {
	
	@Autowired
	private Converter converter;

	public void loadBean( Exame exame, ExameSaveRequest request ) throws LoaderException {
		exame.setNome( request.getNome() );
		exame.setDescricao( request.getDescricao() );
		exame.setValor( request.getValor() );
		
		try {
			exame.setDataExame( converter.stringToDataHora( request.getDataExame() ) );
		} catch (ConverterException e) {
			e.throwLoaderException();
		} 
	}
	
	public void loadResponse( ExameResponse resp, Exame exame ) {
		resp.setId( exame.getId() );
		resp.setNome( exame.getNome() );
		resp.setDescricao( exame.getDescricao() );
		resp.setValor( exame.getValor() ); 
		resp.setDataExame( converter.dataHoraToString( exame.getDataExame() ) ); 
	}
	
	public Exame novoBean( Paciente paciente ) {
		Exame exame = new Exame();
		exame.setPaciente( paciente ); 
		return exame;
	}
	
	public ExameResponse novoResponse() {
		return new ExameResponse();
	}
	
}