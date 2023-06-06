package italo.xclin.relatorio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import italo.xclin.exception.Erro;
import italo.xclin.exception.RelatorioException;
import italo.xclin.model.Anamnese;

@Component
public class AnamneseRelatorioBuilder {

	private final Font titleFont = FontFactory.getFont( FontFactory.TIMES_ROMAN, 24, BaseColor.DARK_GRAY );	
	private final Font fieldFont = FontFactory.getFont( FontFactory.TIMES_ROMAN, 11, BaseColor.DARK_GRAY );	
	private final Font valueFont = FontFactory.getFont( FontFactory.TIMES_ROMAN, 16, BaseColor.GRAY );
	
	public void geraPDF( OutputStream out, Anamnese a, String pacienteNome ) throws RelatorioException {
		try {
			Document doc = new Document();		
			PdfWriter.getInstance( doc, out );		
			
			doc.open();
			
			Image logoImg;
			try {
				URL imgUrl = ResourceUtils.getURL( "classpath:xclin-logo.png" );
				
				logoImg = Image.getInstance( imgUrl );
			} catch (BadElementException | IOException e) {
				throw new RelatorioException( Erro.FALHA_LEITURA_IMG_DE_RELATORIO );
			}
			
			Paragraph title = new Paragraph();
			title.setFont( titleFont );		
			title.setAlignment( Element.ALIGN_CENTER );
			title.add( logoImg );
			title.add( "Anamnese do paciente" );
				
			Paragraph main = new Paragraph();
			main.add( title );
			main.add( new Paragraph( " " ) );
										
			main.add( new Paragraph( "Paciente", fieldFont ) );
			main.add( new Paragraph( pacienteNome, valueFont ) );
			main.add( new Paragraph( " " ) );
			main.add( new Paragraph( " " ) );
			
			doc.add( main );
				
			doc.close();
		} catch ( DocumentException e ) {
			throw new RelatorioException( Erro.FALHA_GER_ANAMNESE_PDF );
		}
	}
	
}
