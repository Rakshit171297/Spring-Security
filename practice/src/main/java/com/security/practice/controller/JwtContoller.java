package com.security.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.helper.JwtUtil;
import com.security.practice.models.JwtRequest;
import com.security.practice.models.JwtResponse;
import com.security.practice.models.Users;
import com.security.practice.service.CustomerDetailService;

@RestController
public class JwtContoller {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerDetailService customerDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping(value = "/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
			
			
		}catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("bad credentials!!");
		}catch (BadCredentialsException e) {
			
			throw new Exception("bad credentials");
			// TODO: handle exception
		}
		
		
		UserDetails userDetails=this.customerDetailService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
