package TicketPortal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import TicketPortal.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class Configurations extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private AuthenticationService userDetailsService;

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/usuario").permitAll()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers("/home").hasAuthority("USER")
				.antMatchers("/admin").hasAuthority("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}

}
