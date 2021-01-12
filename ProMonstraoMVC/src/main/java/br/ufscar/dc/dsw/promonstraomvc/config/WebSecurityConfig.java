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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests()
				.antMatchers("/error", "/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**", "/teatros/listar", "/teatros/listacidade/**").permitAll()
				.antMatchers("/teatros/**", "/promocoes/cadastrar", "/promocoes/salvar", "/promocoes/editar/**", "/promocoes/excluir/**").hasAuthority("THEATER")
				.antMatchers( "/promocoes/listar").hasAnyAuthority("WEBSITE","THEATER")
				.antMatchers("/teatros/**", "/sites/**").hasAuthority("ADMIN")
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}
}