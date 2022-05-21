package com.juegoDados.juegoDados;

import com.juegoDados.juegoDados.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class JuegoDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuegoDadosApplication.class, args);
	}

	@EnableWebSecurity //cuales endpoints estan protegidos
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		//este metodo va decidir cuales estan permitidos o no
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/loginJugador").permitAll()
					.antMatchers(HttpMethod.POST, "/juegoMongo").permitAll()
					.antMatchers(HttpMethod.POST, "/players").permitAll()
					.anyRequest().authenticated();

		}
	}
}
