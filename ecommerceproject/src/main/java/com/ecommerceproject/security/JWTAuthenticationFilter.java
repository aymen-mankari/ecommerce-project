package com.ecommerceproject.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerceproject.entities.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static com.ecommerceproject.security.SecurityConstants.TOKEN_PREFIX;
import static com.ecommerceproject.security.SecurityConstants.SECRET;
import static com.ecommerceproject.security.SecurityConstants.EXPIRATION_TIME;
import static com.ecommerceproject.security.SecurityConstants.HEADER_STRING;

/**
 * this class is responsible for the authentication process.
 * 
 * @author Aymen
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * where we parse the user's credentials and issue them to the
	 * AuthenticationManager.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ApplicationUser creds = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * which is the method called when a user successfully logs in. We use this
	 * method to generate a JWT for this user.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}
