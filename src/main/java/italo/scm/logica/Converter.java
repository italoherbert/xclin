package italo.scm.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import italo.scm.exception.ConverterException;
import italo.scm.exception.Erro;

@Component
public class Converter {

	private final String DATAHORA_BD_FORMATO = "dd-MM-yyyy HH:mm:ss";
	private final String DATAHORA_FORMATO = "dd/MM/yyyy HH:mm:ss";
	
	private final String DATA_BD_FORMATO = "dd-MM-yyyy";
	private final String DATA_FORMATO = "dd/MM/yyyy";
	
	private final SimpleDateFormat dataHoraBDFormato = new SimpleDateFormat( DATAHORA_BD_FORMATO );
	private final SimpleDateFormat dataBDFormato = new SimpleDateFormat( DATA_BD_FORMATO );

	private final SimpleDateFormat dataHoraFormato = new SimpleDateFormat( DATAHORA_FORMATO );
	private final SimpleDateFormat dataFormato = new SimpleDateFormat( DATA_FORMATO );
	
	public Date stringToDataHora( String datastr ) throws ConverterException {
		try {
			return dataHoraBDFormato.parse( datastr );
		} catch (ParseException e) {
			throw new ConverterException( Erro.STRDATA_INVALIDO, datastr );
		}
	}
	
	public Date stringToData( String datastr ) throws ConverterException {
		try {
			return dataBDFormato.parse( datastr );
		} catch (ParseException e) {
			throw new ConverterException( Erro.STRDATA_INVALIDO, datastr );
		}
	}
	
	public String dataHoraToString( Date dataHora ) {
		return dataHoraFormato.format( dataHora );
	}
	
	public String dataToString( Date data ) {
		return dataFormato.format( data );
	}
	
	public String intToString( int num ) {
		return String.valueOf( num );
	}
	
	public String longToString( Long num ) {
		return String.valueOf( num );
	}
	
	public Long stringToLong( String num ) throws ConverterException {
		try {
			return Long.parseLong( num );
		} catch ( NumberFormatException e ) {
			throw new ConverterException( Erro.STRNUM_INVALIDO, num );
		}
	}
	
	public int stringToInt( String num ) throws ConverterException {
		try {
			return Integer.parseInt( num );
		} catch ( NumberFormatException e ) {
			throw new ConverterException( Erro.STRNUM_INVALIDO, num );
		}
	}
	
}
