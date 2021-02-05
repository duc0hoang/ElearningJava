package com.myclass.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;

	private final String secret = "HOANG";

	public AuthFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!request.getServletPath().startsWith("/api/admin")) {
			chain.doFilter(request, response);
			return;
		}

		String tokenHeader = request.getHeader("Authorization");

		if (tokenHeader != null && !tokenHeader.isEmpty() && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.replace("Bearer ", "");

			Date exp = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
			Date now = new Date();

			if (exp.before(now))
				return;

			String email = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();

			UserDetails userDetails = userDetailsService.loadUserByUsername(email);

			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);
		}

		chain.doFilter(request, response);

	}

}
