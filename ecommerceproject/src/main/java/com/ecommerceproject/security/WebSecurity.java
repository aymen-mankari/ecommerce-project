package com.ecommerceproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.ecommerceproject.security.SecurityConstants.SIGN_UP_URL;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * a method where we can define which resources are public and which are
	 * secured. In our case, we set the SIGN_UP_URL endpoint as being public and
	 * everything else as being secured. We also configure custom security filter in
	 * the Spring Security filter chain.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();

		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().authorizeRequests()
				.antMatchers("/**").permitAll();

//		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().authorizeRequests()
//				.antMatchers(HttpMethod.POST, SIGN_UP_URL, "/customer/create").permitAll().anyRequest().authenticated()
//				.and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
//				.addFilter(new JWTAuthorizationFilter(authenticationManager()));

//		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
//		.addFilter(new JWTAuthorizationFilter(authenticationManager()));

//		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL, "/customer/create").permitAll().anyRequest()
//				.authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
//				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}

	/**
	 * a method where we defined a custom implementation of UserDetailsService to
	 * load user-specific data in the security framework. We have also used this
	 * method to set the encrypt method used by our application
	 * (BCryptPasswordEncoder).
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	/**
	 * The easiest way to ensure that CORS is handled first is to use the
	 * CorsFilter. You can integrate the CorsFilter with Spring Security by
	 * providing a CorsConfigurationSource Bean method. NB : If you want clients to
	 * be able to access other headers, you have to list them using the
	 * setExposedHeaders() function.
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("*"));
//		configuration.setAllowedHeaders(Arrays.asList("*"));

		configuration.setAllowCredentials(true);
		// configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));

		configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
				"Access-Control-Allow-Headers", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//	    final CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOrigins(Arrays.asList("*localhost:4200/*"));
//	    configuration.setAllowedMethods(Arrays.asList("HEAD",
//	            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//	    configuration.setAllowCredentials(true);
//	    configuration.setAllowedHeaders(Arrays.asList("*"));
//	    configuration.setExposedHeaders(Arrays.asList("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
}
