package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Investment;
import com.example.demo.entity.MutualFund;
import com.example.demo.repository.InvestMentRepository;
import com.example.demo.repository.MutualFundRepository;

@RestController
public class InvestMentControoler {
	
	@Autowired
	private InvestMentRepository repo;
	
	@Autowired
	private MutualFundRepository dao;
	
	@PostMapping("/invest")
	public void addInvest(@RequestBody Investment investment) {
//		String userName = principal.getName();
		investment.setPan("AJNPU0000H");
		investment.setTimestamp(new Date());
		repo.save(investment);
		
	}
	
	@GetMapping("/getTransaction/{iId}")
	public Investment findByInvestmentid(@PathVariable int iId){
		Investment investment = repo.findById(iId).orElse(null);
		MutualFund Mf = dao.findById(investment.getMutualFundId()).orElse(null);
		investment.setMutulFund(Mf);
		
		return investment;
	}
	
	@GetMapping("/getTransaction/pan/{pan}/id/{iId}")
	public Investment findByInvestmentid(@PathVariable String pan,@PathVariable int iId){
		Investment investment = repo.findByPanANDIId(pan, iId);
		MutualFund Mf = dao.findById(investment.getMutualFundId()).orElse(null);
		investment.setMutulFund(Mf);
		return investment;
	}
//	@GetMapping("/getTransaction/accnum/{accountNumber}")
//	public int findSUMByACcountNumber(@PathVariable String accountNumber ) {
//		
//		return repo.findSUMByACCOUNTNumber(accountNumber);
//		
//	}
	


}
