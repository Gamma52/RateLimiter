package ru.RateLimiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
	
	
	@GetMapping("/home")
	public String home() {
		return "Home page!";
	}
	
	
	@GetMapping("/user")
	public String user() {
		return "User page!";
	}
	
	
	@GetMapping("/orders")
	public String orders() {
		return "Orders page!";
	}
}
