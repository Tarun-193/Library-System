package com.springboot.first.Security;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.first.exception.APIException;
import com.springboot.first.exception.ResoureNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomAdminDetailsService customAdminDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get the token from header
		String token=getToken(request);
		
		try {
			//check if the token is valid or not
			if (StringUtils.hasText(token)) {
				if (!jwtTokenProvider.validateToken(token)) {
					throw new ResoureNotFoundException("Invalid token");
				}
				//load the user 
				String userName=jwtTokenProvider.getUserNameFromToken(token);
				UserDetails userDetails=customAdminDetailsService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				//grant authentication
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} catch (APIException e) {
			//Handle the exception here, for example:
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("{\"Invalid token\": \"" + e.getMessage() + "\"}");
			return;
		} 
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String token=request.getHeader("Authorization");
		if(StringUtils.hasText(token)) {
			return token;
		}
		return null;
	}
	
	
	
}
	
	


