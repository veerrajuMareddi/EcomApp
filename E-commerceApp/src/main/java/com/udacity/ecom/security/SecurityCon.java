//package com.udacity.ecom.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//@Configuration
//
//@EnableWebSecurity
//public class SecurityCon extends WebSecurityConfigurerAdapter {
//
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new CustomUserDetailsService();
//	}
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//  
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(this.userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//
//		return authProvider;
//	}
//
//	/*
//	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
//	 * Exception { auth.authenticationProvider(authenticationProvider());
//	 */
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
////				.antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasRole("USER")
////
////				// .antMatchers("//* *")
////				// .permitAll()
////				.and().formLogin().loginPage("/index")
////
////				// .usernameParameter("name")
////				.loginProcessingUrl("/loginurl")
////
////				.defaultSuccessUrl("/home")
////
////				.and().csrf().disable();
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http
//	        .csrf().disable()
//	        
//	        .authorizeRequests()
//	           // .antMatchers("/admin").hasRole("ADMIN")
//	           // .antMatchers("/user").hasRole("USER")
//	            .anyRequest().permitAll() // Allow all other requests
//	        .and()
//	        .formLogin()
//	            .loginPage("/index")
//	            .loginProcessingUrl("/loginurl")
//	            .defaultSuccessUrl("/home")
//	        .and()
//	        .csrf().disable();
//	    
//	    http.headers().frameOptions().disable();
//	}
//
//	
//}
