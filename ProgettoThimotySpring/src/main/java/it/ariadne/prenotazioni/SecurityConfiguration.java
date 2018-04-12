package it.ariadne.prenotazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
//	@Bean
//	UserDetailsService customService() {
//		return new UtentiService();
//	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
		auth.inMemoryAuthentication()
		.withUser("albi")
		.password("ariadne")
		.roles("USER");
		//auth.userDetailsService(customService());
	}	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.defaultSuccessUrl("/")
			.loginPage("/")
			.failureUrl("/?error=true")
			.usernameParameter("nome")
			.passwordParameter("password")
			.and()
		    .exceptionHandling().accessDeniedPage("/403")
			.and().logout()
			.logoutUrl("/j_spring_security_logout")
			.logoutSuccessUrl("/");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/newResource.html").access("hasRole('ADMIN')");
		http.authorizeRequests().antMatchers("/login").permitAll();
	}
	
	
	
}
