package italo.scm.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTLogica {

	private final static String SECRET = "italo";
	private long expirationMS = 3600000;
	
	public String geraToken( String subject, String[] roles ) {
		Map<String, Object> claims = new HashMap<>();
		claims.put( "roles", this.rolesToClaim( roles ) );		
		return this.geraToken( subject, claims );
	}
	
	public String geraToken( String subject, List<String> roles ) {
		Map<String, Object> claims = new HashMap<>();
		claims.put( "roles", this.rolesToClaim( roles ) );		
		return this.geraToken( subject, claims );
	}
	
	public JWTTokenInfo tokenInfo( String token ) {
		Claims claims = this.extraiClaims( token );
		boolean expirado = ( claims.getExpiration().before( new Date() ) );
		String subject = claims.getSubject();
		String[] roles = this.claimToRoles( claims.get( "roles" ) );
		return new JWTTokenInfo( subject, roles, expirado );
	}
	
	public String geraToken( String subject, Map<String, Object> claims ) {
		byte[] secret = Base64.getDecoder().decode( SECRET );
		Key secretKey = Keys.hmacShaKeyFor( secret );
		
		return Jwts.builder()				
				.setSubject( subject )
				.setClaims( claims )
				.setIssuedAt( new Date() )
				.setExpiration( new Date( System.currentTimeMillis() + expirationMS ) )
				.signWith( secretKey, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Claims extraiClaims( String token ) {
		byte[] secret = Base64.getDecoder().decode( SECRET );
		Key secretKey = Keys.hmacShaKeyFor( secret );
		
		return Jwts.parserBuilder()
				.setSigningKey( secretKey )
				.build()
				.parseClaimsJwt( token )
				.getBody();
	}
			
	public String[] claimToRoles( Object claim ) {
		String lista = String.valueOf( claim );
		String[] roles = lista.split( "," );
		return roles;
	}
	
	public String rolesToClaim( List<String> roles ) {
		int size = roles.size();
		
		StringBuilder strB = new StringBuilder();
		for( int i = 0; i < size; i++ ) {
			strB.append( roles.get( i ) );
			if ( i < size-1 )
				strB.append( "," );		
		}
		return strB.toString();
	}
	
	public String rolesToClaim( String[] roles ) {		
		StringBuilder strB = new StringBuilder();
		for( int i = 0; i < roles.length; i++ ) {
			strB.append( roles[ i ] );
			if ( i < roles.length-1 )
				strB.append( "," );		
		}
		return strB.toString();
	}
	
}
