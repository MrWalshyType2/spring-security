package com.springboot.security.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.springboot.security.api.security.permission.ApplicationUserPermission;
import com.springboot.security.api.security.role.ApplicationUserRole;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // spring protects the api by default
			.authorizeRequests() // we want authorized requests
			.antMatchers("/", "index", "/css/*", "/js/*") // whitelisted patterns
			.permitAll() // permit the whitelisted urls
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name()) // role based authentication
			
			.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(
					ApplicationUserPermission.COURSE_WRITE.getPermission())
			
			.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(
					ApplicationUserPermission.COURSE_WRITE.getPermission())
			
			// PUT on /management/api/** requires COURSE_WRITE permission as an authority
			.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(
					ApplicationUserPermission.COURSE_WRITE.getPermission())
			
			// ADMIN and ADMINTRAINEEs can access any GET on /management/api/**
			.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(
					ApplicationUserRole.ADMIN.name(),
					ApplicationUserRole.ADMINTRAINEE.name())
			.anyRequest() // any request
			.authenticated() // must be authenticated
			.and()
			.httpBasic(); // mechanism is Basic Authentication (httpBasic)
	}

	@Override
	@Bean // instantiated for us
	protected UserDetailsService userDetailsService() {
		// Get user from db
		UserDetails mDawgUser = User.builder()
			.username("mdawg")
			.password(passwordEncoder.encode("password")) // Using the passwordEncoder to encode password
//			.roles(ApplicationUserRole.STUDENT.name())
			.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
			.build();
		
		UserDetails larryUser = User.builder()
				.username("larry")
				.password(passwordEncoder.encode("password123")) // Using the passwordEncoder to encode password
//				.roles(ApplicationUserRole.ADMIN.name())
				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
				.build();
		
		UserDetails tomUser = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("password")) // Using the passwordEncoder to encode password
//				.roles(ApplicationUserRole.ADMINTRAINEE.name())
				.authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
				.build();
		
		return new InMemoryUserDetailsManager(
				mDawgUser, // student
				larryUser, // admin
				tomUser	   // admin trainee
				);
	}
	
	
	
}
