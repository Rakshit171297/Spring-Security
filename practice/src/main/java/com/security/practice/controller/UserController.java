package com.security.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.models.Users;
import com.security.practice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public List<Users> getAllUsers(){
		List<Users> list= userService.getAllUsers();
		return list;
	}
	
	@PostMapping("/save")
	public String saveUser(@RequestBody Users users) {
		String message=userService.saveUsers(users);
		return message;
	}
	
	@PostMapping("/validate")
	public String validateUser(@RequestBody Users users) {
		String message=userService.validateUser(users);
		return message;
	}
}
