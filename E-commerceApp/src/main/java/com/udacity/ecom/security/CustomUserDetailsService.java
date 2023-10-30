package com.udacity.ecom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.UserInfo;
import com.udacity.ecom.repo.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repo;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// TODO Auto-generated method stub4
		 UserInfo user =checkUser(username);
	if (user==null)
	  {
	   throw new UsernameNotFoundException("could not found user !!");
	  }
	   CustomUserDetails customUserDetails = new CustomUserDetails(user);
	   return customUserDetails;
	}
	
	public UserInfo checkUser(String username) {
	   

		return  repo.findByUsername(username);
		
	}
	}


