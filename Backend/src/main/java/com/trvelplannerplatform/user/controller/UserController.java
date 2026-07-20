package com.trvelplannerplatform.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.dto.LoginRequest;
import com.trvelplannerplatform.user.dto.LoginResponse;
import com.trvelplannerplatform.user.dto.UserRegisterRequest;
import com.trvelplannerplatform.user.interfaces.UserLoginService;
import com.trvelplannerplatform.user.service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userService;
	private final UserLoginService userLoginService;
	
	@PostMapping("v1/register")
	public String registerUser(@Valid @RequestBody UserRegisterRequest request) {
		log.info("Registering user with request: {}", request.getEmail());
		
		// Implementation for user registration
		
		String response = userService.registerUser(request);
		
		return response;
	}

	@PostMapping("v1/login")
	public String loginUser(@Valid @RequestBody LoginRequest loginRequest) {
		// Implementation for user login
		log.info("Logging in user with email: {}", loginRequest);
		
		LoginResponse response = userLoginService.login(loginRequest);
		
		log.info("User logged in successfully: {}", response);
		return "User logged in successfully\n"+ "request: " +loginRequest+ "\n"+"response: " + response;
	}
	
	@GetMapping("v1/test")
	public String test() {
	    return "JWT Authentication Successful";
	}
}
