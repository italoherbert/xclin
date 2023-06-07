package italo.xclin.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import italo.xclin.exception.ConverterException;
import italo.xclin.exception.Erro;

@Component
public class Converter {

	private final String DATAHORA_BD_FORMATO = "yyyy-MM-dd HH:mm:ss";
	private final String DATA_BD_FORMATO = "yyyy-MM-dd";
	
	private final String DATAHORA_BR_FORMATO = "dd/MM/yyyy HH:mm:ss";
	private final String DATA_BR_FORMATO = "dd/MM/yyyy";
		
	private final SimpleDateFormat dataHoraBDFormato = new SimpleDateFormat( DATAHORA_BD_FORMATO );
	private final SimpleDateFormat dataBDFormato = new SimpleDateFormat( DATA_BD_FORMATO );

	private final SimpleDateFormat dataHoraBRFormato = new SimpleDateFormat( DATAHORA_BR_FORMATO );
	private final SimpleDateFormat dataBRFormato = new SimpleDateFormat( DATA_BR_FORMATO );

	public String formataDataHoraBR( Date data ) {
		return dataHoraBRFormato.format( data );
	}
	
	public String formataDataBR( Date data ) {
		return dataBRFormato.format( data ); 
	}
	
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
	
	public Date stringToDataHoraNEx( String datastr ) {
		try {
			return this.stringToDataHora( datastr );
		} catch ( ConverterException e ) {
			
		}
		return null;
	}
	
	public Date stringToDataNEx( String datastr ) {
		try {
			return this.stringToData( datastr );
		} catch ( ConverterException e ) {
			
		}
		return null;
	}
	
	public String dataHoraToString( Date dataHora ) {
		return dataHoraBDFormato.format( dataHora );
	}
	
	public String dataToString( Date data ) {
		return dataBDFormato.format( data );
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
