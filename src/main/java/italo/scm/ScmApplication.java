package italo.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude=UserDetailsServiceAutoConfiguration.class)
@EnableFeignClients
public class ScmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScmApplication.class, args);
	}

}
