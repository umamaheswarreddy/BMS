package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.entity.User;
import com.cts.repository.UserRepository;

@RestController
@RequestMapping("/demo")
public class demo {
	
	@Autowired
	private UserRepository repo;
	
//	@Autowired
//	private NoOpPasswordEncoder passwordEncoder;
	
	@GetMapping("/get")
	public String get() {
		return "demo working";
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		if(user.getPassword().equals(user.getConformPassword()))
		{
			repo.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
					
		  
	}

}
