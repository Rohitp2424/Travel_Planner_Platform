package com.trvelplannerplatform.user.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	
	private final CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("===== JWT FILTER START =====");

		// TODO Auto-generated method stub
		log.info("authenticating the request with Jwt authentication");
		
		
		
		String authHeader =
		        request.getHeader("Authorization");
		log.info("Authorization Header : {}", authHeader);
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
		
		String jwt = authHeader.substring(7);
		log.info("JWT : {}", jwt);

        // Extract username (email) from JWT
        String username = jwtService.extractUsername(jwt);
        log.info("Username : {}", username);
        
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(username);
        log.info("user details from token: {}", userDetails);
        
        boolean valid = jwtService.isTokenValid(jwt, userDetails);
        log.info("Token Valid : {}", valid);
        
        if (valid) {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            log.info("Authentication Stored");
        }
		
        log.info("===== JWT FILTER END =====");

        filterChain.doFilter(request, response);
	}

	

}
