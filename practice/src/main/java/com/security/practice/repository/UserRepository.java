package com.security.practice.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.security.practice.models.Users;



public interface UserRepository extends CrudRepository<Users,String> {

	public List<Users> findAll();

	public Users findByUsername(String username);

}
