package com.security.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.practice.models.Users;
import com.security.practice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;
	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> list=userRepository.findAll();
		return list;
	}
	@Override
	public String saveUsers(Users users) {
		// TODO Auto-generated method stub
		if(users==null) {
			return "AddDeatils fail to add user";
		}
		Users user=userRepository.findByUsername(users.getUsername());
		
		if(user==null) {
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			userRepository.save(users);
			return "Suceess";
		}
		return "User already pressent";
	}
	@Override
	public String validateUser(Users users) {
		// TODO Auto-generated method stub
		if(users==null) {
			return "AddDeatils fail to add user";
		}
		Users user=userRepository.findByUsername(users.getUsername());
		
		if(	passwordEncoder.matches(users.getPassword(), user.getPassword())) {
			return "valid User";
		}else {
			return "Invalid User password id ";
		}
		
	}

	
}
