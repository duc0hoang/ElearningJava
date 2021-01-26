package com.myclass.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

public class AuthFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;

	@Value("${secret}")
	private String secretKey;
	
	private final String secret = "HOANG";

	public AuthFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("tokennnnnnnnn"+secretKey);
		String token = request.getHeader("Authorization");

//		System.out.println(token);

		if (token != null && !token.isEmpty()) {

			String email = Jwts.parser().setSigningKey("HOANG").parseClaimsJws(token).getBody().getSubject();

			UserDetails userDetails = userDetailsService.loadUserByUsername(email);

			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		chain.doFilter(request, response);

	}

}
