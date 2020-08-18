package com.cts.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.model.Investment;
import com.cts.model.MutualFund;

@FeignClient(name = "demo-service")
public interface DemoServiceProxy {

	@RequestMapping("/get")
	public String get();

	@RequestMapping("/mutualFunds/all")
	public List<MutualFund> getAllMutualFunds();

	@GetMapping("/getTransaction/{iId}")
	public Investment findByInvestmentid(@PathVariable int iId);
	
	@PostMapping("/invest")
	@RequestMapping(value = "/invest",method = RequestMethod.POST)
	public void addInvest(@RequestBody Investment investment);
	

}
