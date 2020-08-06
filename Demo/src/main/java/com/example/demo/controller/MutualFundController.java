package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MutualFund;
import com.example.demo.repository.MutualFundRepository;

@RestController
@RequestMapping("/mutualFunds")
public class MutualFundController {
	
	@Autowired
	private MutualFundRepository repo;

	@GetMapping("/all")
	public List<MutualFund> getAllMutualFunds(){
		return repo.findAll();
		
	}
	
}
