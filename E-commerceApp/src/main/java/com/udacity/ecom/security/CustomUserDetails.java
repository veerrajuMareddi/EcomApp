package com.udacity.ecom.security;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.udacity.ecom.entity.UserInfo;


public class CustomUserDetails implements UserDetails {
	/**
	 * 
	*/
	private static final long serialVersionUID = 1L;
	@Autowired
	private com.udacity.ecom.entity.UserInfo user;

	public CustomUserDetails(UserInfo user) {
		
		this.user = user;
	}
  
	public CustomUserDetails() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-genestub
		SimpleGrantedAuthority simpl= new SimpleGrantedAuthority("USER");
		return List.of(simpl); 
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	 }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stu
		return true;    
	}

	@Override
	public boolean isAccountNonLocked() { 
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}


