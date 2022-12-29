package br.mrc.helpdesk.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.mrc.helpdesk.infrastructure.services.UserDetailsServiceImpl;
import br.mrc.helpdesk.security.JWTAuthenticationFilter;
import br.mrc.helpdesk.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = false, securedEnabled = true)
public class SecurityConfig {// extends WebSecurityConfigureAdapter{

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**"};

	@Autowired
	private Environment env; // Pega o ambiente que o sistema esta executando (Test/Dev)

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@Autowired
//	@Qualifier("authenticationManager")
//	private AuthenticationManager authenticationManager;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());	

		return authProvider;
	}

	// Ele estaria subscrevendo um metodo
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		//private AuthenticationManager authenticationManager;
		
		http.anonymous();
		// Se for o perfil de test
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		// Só que o metodo nao existe entao nao tem como funcionar
		// Teria que nesse caso escrever o metodo do 0

		// Aki ele vai aplicar ao http a configuracao que ja esta no sistema
		// Que é a que fizemos no Bean de baixo corsConfigurationSource()
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable(); // Tambem desabilitamos o
		
		// Criamos uma politica para que a sessao de usuario nao seja criada
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Liberar o acesso a requisisoes
		http.authorizeHttpRequests().requestMatchers(PUBLIC_MATCHERS).permitAll()
			             .anyRequest().authenticated().and().httpBasic();
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers(PUBLIC_MATCHERS).permitAll()
//                .anyRequest().authenticated());
        
        http.authenticationProvider(authenticationProvider());
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),jwtUtil));
		//http.addFilter(new JWTAuthenticationFilter(authenticationManager(new AuthenticationConfiguration()), jwtUtil));
		http.formLogin();

		

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

//** VER PORQ NAO ESTA FUNFANDO //**
	// Vai fazer uma configracao default para permitir alguns valores
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues(); // Ja seta alguns valores

		// Configuracao com os valores padroes nao é o suficiente
		// Então é preciso setar outros metodos "POST"..."GET"....
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));
			
		// Registrando essa configuracao
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Caminho das requisicoes, configuracao
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
