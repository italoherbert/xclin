package italo.scm.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import italo.scm.logica.jwt.JWTLogica;
import italo.scm.logica.jwt.JWTTokenInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutenticacaoFilter extends OncePerRequestFilter {

	private final JWTLogica jwtLogica;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String auth = request.getHeader( "Authorization" );
				
		boolean authHeaderValido = true;
		if ( auth == null ) {
			authHeaderValido = false;
		} else {
			authHeaderValido = auth.startsWith( "Bearer " );						
		}
		
		if ( authHeaderValido ) {		
			String token = auth.substring( 7 );
			try {
				JWTTokenInfo tokenInfo = jwtLogica.tokenInfo( token );
				if ( !tokenInfo.isExpirado() ) {
					String username = tokenInfo.getSubject();
					String[] roles = tokenInfo.getRoles();
					
					List<SimpleGrantedAuthority> authorities = new ArrayList<>();
					for( String role : roles )
						authorities.add( new SimpleGrantedAuthority( role ) );					
					
					UsernamePasswordAuthenticationToken userPassToken = 
							new UsernamePasswordAuthenticationToken( username, null, authorities );
					
					SecurityContextHolder.getContext().setAuthentication( userPassToken );
				}
			} catch ( SignatureException e ) {
				
			} catch ( ExpiredJwtException e ) {
				String resp = "{ \"mensagem\" : \"Token expirado, por favor faça login novamente.\" }";
				response.setContentType( "application/json" );
				response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
				
				PrintWriter writer = new PrintWriter( response.getOutputStream() );
				writer.print( resp ); 
				writer.flush();		
				
				return;
			}
		} 
		super.doFilter( request, response, filterChain );
	}

}
