package com.udacity.ecom.security;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import com.udacity.ecom.entity.UserInfo;
import com.udacity.ecom.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
    private JwtTokenProvider jwtTokenProvider;

    


	public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
		throws ServletException, IOException {
String auth=request.getHeader("Authorization");
	 
	 if(request.getRequestURI().contains("/api"))
	 {
		 
		 if(auth!=null)
{	 UserInfo token = jwtTokenProvider.resolveToken(request);

			 if (token != null) {
			 		List<GrantedAuthority> authRoles = new ArrayList<>();

			         if (token != null) {
			             // Create an authentication token with the user's details
			             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			            		 token.getUsername(),token.getPassword(), authRoles
			             );

			             // Set up authentication in the SecurityContext
			             SecurityContextHolder.getContext().setAuthentication(authentication);
			         }
			     }
			 else {
				 throw new CustomAccessDeniedException("Invalid token or token is expired,Please login again.");
			 }
} else {
	 throw new CustomAccessDeniedException("Invalid token or token is expired,Please login again.");
}
	 }
	
     

     filterChain.doFilter(request, response);	
}

public JwtFilter() {
	super();
	// TODO Auto-generated constructor stub
}

	
}
