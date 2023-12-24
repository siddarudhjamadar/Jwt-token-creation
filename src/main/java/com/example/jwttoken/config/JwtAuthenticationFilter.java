package com.example.jwttoken.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwttoken.service.JwtService;
import com.example.jwttoken.service.UserService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private  JwtService service ;
	@Autowired
	private  UserService userService ;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3. StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt=authHeader.substring(7);
		userEmail=service.extractUserName(jwt);
	
		if(StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
		if(service.isTokenValid(jwt, userDetails)) {
		SecurityContext context	= SecurityContextHolder.createEmptyContext();
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		context.setAuthentication(token);
		SecurityContextHolder.setContext(context);
		}
		
		}
		filterChain.doFilter(request, response);
	}

}
