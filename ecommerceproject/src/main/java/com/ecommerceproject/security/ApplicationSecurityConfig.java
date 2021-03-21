//package com.ecommerceproject.security;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//	
////	@Autowired
////	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	public PasswordEncoder passwordEncoder;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		new Jwt
//		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
//				.addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
//				.authorizeRequests().antMatchers("/api/**").hasAuthority(ApplicationUserRole.ROLE_ADMIN.name())
//				.anyRequest().authenticated();
//	}
//
//	/**
//	 * configure(AuthenticationManagerBuilder auth): a method where we defined a
//	 * custom implementation of UserDetailsService to load user-specific data in the
//	 * security framework.
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//	}
//
//	/**
//	 * The easiest way to ensure that CORS is handled first is to use the
//	 * CorsFilter. You can integrate the CorsFilter with Spring Security by
//	 * providing a CorsConfigurationSource Bean method. NB : If you want clients to
//	 * be able to access other headers, you have to list them using the
//	 * setExposedHeaders() function.
//	 */
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("*"));
//		configuration.setAllowedHeaders(Arrays.asList("*"));
//		configuration.setExposedHeaders(
//				Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Authorization"));
//		configuration.setAllowCredentials(true);
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//
//		return source;
//	}
//}
