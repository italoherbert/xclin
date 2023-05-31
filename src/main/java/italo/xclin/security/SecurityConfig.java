package italo.xclin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final String[] PUBLIC = {
		"/*", "/api/login", "/api/localidade/**", "/assets/**", 
		//"/v3/api-docs**/**", "/swagger-ui**/**"
	};
	
	@Autowired
	private AutenticacaoFilter autenticacaoFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(authHttpReqs ->
                authHttpReqs
                    .requestMatchers(PUBLIC).permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(autenticacaoFilter, UsernamePasswordAuthenticationFilter.class);			
        
		return http.build();
	}
	
}
