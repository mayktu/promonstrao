package br.ufscar.dc.dsw.promonstraomvc.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.promonstraomvc.security.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
				.antMatchers("/error", "/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**", "/teatros/listar", "/teatros/listacidade/**").permitAll()
				.antMatchers("/teatros/**").hasAuthority("THEATER")
				.antMatchers("/sites/listar/").hasAuthority("WEBSITE")
				.antMatchers("/teatros/**", "/sites/**").hasAuthority("ADMIN")
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		// Controladores REST
		.antMatchers("/sites", "/teatros", "/promocoes").permitAll()
		.antMatchers("/sites/{\\d+}", "/teatros/{\\d+}").permitAll()
		.antMatchers("/promocoes/{\\d+}").permitAll()
		.antMatchers("/teatros/cidades/{\\w+}").permitAll()
		.antMatchers("/promocoes/sites/{\\d+}").permitAll()
		.antMatchers("/promocoes/teatros/{\\d+}").permitAll()
		// Demais linhas
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().logoutSuccessUrl("/").permitAll();
	}
}