package com.cts.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.BankAccount;
import com.cts.entity.User;
import com.cts.repository.BankAccountRepository;
import com.cts.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private BankAccountRepository dao;
	
//	@Autowired
//	private NoOpPasswordEncoder passwordEncoder;
 
	
	
	@GetMapping("/all")
	public List<User> getAll(){
		return repo.findAll();
	}
	@GetMapping("/bank/{pan}")
	public List<BankAccount> getBank(@PathVariable String pan){
		return dao.findAllByPan(pan);
	}
	
	@PostMapping("/addBank")
	public void addbank(@RequestBody BankAccount account,Principal principal) {
		String username=principal.getName();
		 int count = dao.countByPan(username);
		 if(count<=4) {
			 account.setPan(username);	
		     dao.save(account);
		 }
			
	}
	
	 
	 @GetMapping("/username")
	  public String currentUserName(Principal principal) {
		 
	     String username = principal.getName();
	     int count = dao.countByPan(username);
	     System.out.println(count +"------------------------------------------------");
		 return username+count;
	  }
	
	
	
}
