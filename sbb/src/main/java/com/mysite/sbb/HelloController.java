package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HelloController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		String A= "Hello World_A!!!!";
		
		String B= "Hello Spring Boot Board";
		
		/*	
  		for(int i=0; i<2; i++) {
			if(i==0) {
			return A;
			}else {
			return B;	
			}
		}
		
		*/
		return B;
		
	}
	
	@GetMapping("/jump")
	@ResponseBody
	public String jump() {
		
		String ex_1= "점프 투 스프링 부트";
		
		
		return ex_1;
		
	}
	
	
}
