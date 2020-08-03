package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.User;
import com.cts.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {
	
	@Autowired
	private UserRepository repo;
	
//	@Autowired
//	private NoOpPasswordEncoder passwordEncoder;
 
	
	
	@GetMapping("/all")
	public List<User> getAll(){
		return repo.findAll();
	}
	
	
}
