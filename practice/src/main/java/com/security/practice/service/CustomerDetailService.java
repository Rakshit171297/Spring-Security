package com.security.practice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.practice.models.Users;
import com.security.practice.repository.UserRepository;
@Service
public class CustomerDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users=userRepository.findByUsername(username);
		if(users!=null) {
			return new User(users.getUsername(),users.getPassword(),new ArrayList<>());
		}else {
		throw new UsernameNotFoundException("user not found");
		}
	} 
	
	

}
