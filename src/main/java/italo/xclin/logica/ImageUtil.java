package italo.xclin.logica;

import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.DatatypeConverter;

@Component
public class ImageUtil {
    
    public boolean verifyIfBase64ImageFile( String dados ) {
        return dados.startsWith( "data:image/png;base64" ) ||
            dados.startsWith( "data:image/jpg;base64" ) ||
            dados.startsWith( "data:image/jpeg;base64" ) || 
            dados.startsWith( "data:image/gif;base64" );
    }

    public String extractImageType( String dados ) {
        String base = dados.split(",")[0];
        return base
            .replace( "data:image/", "" )
            .replace( ";base64", "" );
    }

    public byte[] base64ToBytes( String dados ) {
        String base64Image = dados.split(",")[1];
		return DatatypeConverter.parseBase64Binary( base64Image );
    }

    public String bytesToBase64( byte[] bytes, String imageType ) {
        return "data:image/"+imageType+";base64," + Base64.encodeBase64String( bytes );
    }

}
