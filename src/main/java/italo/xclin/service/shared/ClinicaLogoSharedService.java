package italo.xclin.service.shared;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.xclin.Erro;
import italo.xclin.exception.ServiceException;
import italo.xclin.logica.ImageUtil;
import italo.xclin.model.Clinica;
import italo.xclin.model.response.Base64ImageResponse;

@Service
public class ClinicaLogoSharedService {
    
    public final static String DEFAULT_LOGO_IMAGE_TYPE = "png";

	@Autowired
	private ImageUtil imageUtil;			

	public Base64ImageResponse getLogo( Clinica clinica ) throws ServiceException {		
		String logoBase64 = clinica.getLogomarca();

        Base64ImageResponse resp = new Base64ImageResponse();

        boolean semLogo = false;
        if ( logoBase64 == null )
            semLogo = true;
        else if ( logoBase64.isEmpty() )
            semLogo = true;

        if ( semLogo ) {
            InputStream in = getClass().getResourceAsStream( "/xclin-logo.png" );
            if ( in == null )
                throw new ServiceException( Erro.FALHA_LEITURA_LOGO_DEFAULT );

            try {
                byte[] bytes = in.readAllBytes();                
                resp.setImageBase64( imageUtil.bytesToBase64( bytes, DEFAULT_LOGO_IMAGE_TYPE ) );
            } catch ( IOException e ) {
                throw new ServiceException( Erro.FALHA_LEITURA_LOGO_DEFAULT );
            }
        } else {
            if ( !imageUtil.verifyIfBase64ImageFile( logoBase64 ) )
                throw new ServiceException( Erro.ARQ_IMG_NAO_BASE64 );

		    resp.setImageBase64( logoBase64 ); 
        }

        return resp;
	}

}
