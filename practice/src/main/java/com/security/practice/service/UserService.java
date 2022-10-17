package com.security.practice.service;

import java.util.List;

import com.security.practice.models.Users;

public interface UserService {

	public List<Users> getAllUsers();

	public String saveUsers(Users users);

	public String validateUser(Users users);

}
