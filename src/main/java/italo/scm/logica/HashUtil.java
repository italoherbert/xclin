package italo.scm.logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {
	
	public String geraHash( String pw ) {
		try {
			MessageDigest md = MessageDigest.getInstance( "SHA-256" );
			byte[] cript = md.digest( pw.getBytes() );			
			return new String( Hex.encode( cript ) );
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println( new HashUtil().geraHash( "italo" ) );
	}
		
}
