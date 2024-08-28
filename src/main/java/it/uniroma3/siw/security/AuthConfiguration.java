package it.uniroma3.siw.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.uniroma3.siw.model.Credentials;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

	@Autowired DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.authoritiesByUsernameQuery("SELECT username, role from credentials WHERE username=?")
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain configure(final HttpSecurity httpSecurity) 
	        throws Exception {
	    httpSecurity
	        .csrf().disable()
	        .cors().disable()
	        .authorizeHttpRequests()
	        .requestMatchers(HttpMethod.GET, "/", "/index", "/css/**", "/images/**", "/login", "/register", "/rest/**", "rest/product/**", "/registrationSuccessfull", "/login/success", "/login/error", "/product/**", "/about", "/teams", "/team/**").permitAll()
	        .requestMatchers(HttpMethod.POST, "/register", "/login", "/success", "/formRegister", "/**").permitAll()
	        .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(Credentials.ADMIN_ROLE)
	        .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(Credentials.ADMIN_ROLE)
	        .requestMatchers(HttpMethod.GET, "/president/**").hasAnyAuthority(Credentials.PRESIDENT_ROLE)
	        .requestMatchers(HttpMethod.POST, "/president/**").hasAnyAuthority(Credentials.PRESIDENT_ROLE)
	        .anyRequest().authenticated()
	        .and().formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .defaultSuccessUrl("/success", true)
	        .failureUrl("/login?error=true")
	        .and().logout()
	        .logoutUrl("/logout")
	        .logoutSuccessUrl("/")
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID")
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .clearAuthentication(true)
	        .permitAll();
	    return httpSecurity.build();
	}
	
}



	
