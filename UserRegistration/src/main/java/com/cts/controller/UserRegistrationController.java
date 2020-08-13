package com.cts.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.entity.User;
import com.cts.exceptions.CustomerNotFoundException;
import com.cts.exceptions.DupicatesException;
import com.cts.model.Investment;
import com.cts.model.MutualFund;
import com.cts.repository.UserRepository;


import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 844226
 * @implNote UserRegistrationController for UserRegistration
 *
 */
@RestController
@RequestMapping("/UserRegistration")
@CrossOrigin(origins = "http://localhost:9000")
public class UserRegistrationController {


	@Autowired
	private UserRepository repo;

	@Autowired
	private DemoServiceProxy proxy;
	
	 Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	
	
	@PostMapping("/feign/invest")
	public void addInvest(@RequestBody Investment investment) {
		proxy.addInvest(investment);
	}
	

	
	@GetMapping("/username")
	public String currentUserName(Principal principal) {
		String username = principal.getName();
		return username;
	}

	@RequestMapping("/feign")
	public String getAsll() {
	return proxy.get();
	}
	
	 @RequestMapping("/feign/mutualFunds/all")
	  public List<MutualFund> getAllMutualFunds(){
		 return proxy.getAllMutualFunds();
	 }
	 @GetMapping("/feign/getTransaction/{iId}")
		public Investment findByInvestmentid(@PathVariable int iId) {
		 
		 return proxy.findByInvestmentid(iId);
	 }

//	@Autowired
//	private NoOpPasswordEncoder passwordEncoder;

	@GetMapping("/get")
	public String get() {
		return "bms service working";
	}

	/**
	 * 
	 * @param user -user details in json format
	 * @return 201 status if user added succesfull.
	 */
	@ApiOperation(value = "Add a User", consumes = "application/json", notes = "Hit this URL to insert a new USER details")
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		
		if (!user.getPassword().equals(user.getConformPassword())) {
			logger.error("password and conform password are not matching");
			throw new DupicatesException("password and conform password are not matching");
		} 
		else if(repo.existsByPan(user.getPan())) {		
			throw new DupicatesException("duplicate pan ");
		}
		else {			
			user.setRole("USER");
			repo.save(user);
			logger.info("new user added successfully");
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	
	}
		
//		if (user.getPassword().equals(user.getConformPassword())) {
//			user.setRole("USER");
//			repo.save(user);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
//		} else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
//
//	}

}
