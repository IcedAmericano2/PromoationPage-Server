package com.example.promotionpage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class TestController {

	@GetMapping("/greeting")
	public String greetting(){
		return "Hello World!";
	}
}
