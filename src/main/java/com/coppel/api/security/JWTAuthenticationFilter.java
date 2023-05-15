package com.coppel.api.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.coppel.api.model.Data;
import com.coppel.api.model.Meta;
import com.coppel.api.model.ResponseCoppel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final static String APP_JSON = "application/json";
	private static Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// intento de autenticaion en este filtro
		AuthCredentials authCredentials = new AuthCredentials();

		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
			logger.info("INTENTO DE AUTENTICACION DE Username: " + authCredentials.getUsername());

		} catch (IOException e) {
			logger.error("IOException: " + e.getMessage());
		}

		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getUsername(), authCredentials.getPassword(), Collections.emptyList());

		return getAuthenticationManager().authenticate(usernamePAT);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		logger.info("SE AUTENTICO CORRECTAMENTE");

		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());
		// response.setStatus(HttpServletResponse.SC_OK);

		ResponseCoppel body = new ResponseCoppel(new Meta("OK"), new Data(token));
		String bodyJson = new ObjectMapper().writeValueAsString(body);

		// response.addHeader("Authorization", "Bearer " + token);
		response.getWriter().write(bodyJson);
		response.setContentType(APP_JSON);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().flush();// para confirmar el cambio del header
		super.successfulAuthentication(request, response, chain, authResult);

	}
}
