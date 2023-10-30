package com.udacity.ecom.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.udacity.ecom.entity.UserInfo;
import com.udacity.ecom.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {
	
	@Autowired
	private  UserService repo ;
    
    private static String jwtSecret="jhzgduyw763t26jhfsc";

    private int jwtExpirationInMs=10000000;

    public String generateToken(String long1) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", long1);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(long1)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Handle token validation exceptions here
        }
        return false;
    }

    public UserInfo resolveToken(HttpServletRequest request) {
        // Get the token from the Authorization header
        String bearerToken = request.getHeader("Authorization");
        Long id ;
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Extract the token (remove "Bearer " prefix)
            String token = bearerToken.substring(7);

            // Check if the token is valid and not expired
            if (JwtTokenProvider.validateToken(token)) {
                // Assuming you have a method to check the user's role from the token
                // Replace 'getUserRoleFromToken' with your implementation
                String userRole=null;
				try {
					userRole = JwtTokenProvider.getUsernameFromToken(token);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	 UserInfo dt= repo.findByUsername(userRole);
		    	 String requestURL = request.getRequestURL().toString();

		    	    // Split the URL by '/'
		    	    String[] urlParts = requestURL.split("/");

		    	    // Extract the last part of the URL (in this case, "1")
		    	    String idString = urlParts[urlParts.length - 1];

		    	    try {
		    	        // Parse the extracted ID to a Long (or int) if it's a number
		    	         id = Long.parseLong(idString);
		    	    } catch (NumberFormatException e) {
		    	        // Handle the case where the ID is not a valid number
		    	        return null;
		    	    }
		    	 if(id==dt.getId())
                    return dt;
                
            }
        }
        
        return null; // No valid token found or token does not have the required role
    }

}
