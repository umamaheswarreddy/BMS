package com.cts.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="demo-service")
public interface DemoServiceProxy {

	
	  @RequestMapping("/get")
	  public String get();
	  
	  
}
