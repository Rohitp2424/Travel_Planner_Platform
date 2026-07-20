package com.trvelplannerplatform.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.dto.UserRegisterRequest;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.mapper.UserMapper;
import com.trvelplannerplatform.user.registerserviceimpls.BusinessValidationRules;
import com.trvelplannerplatform.user.registerserviceimpls.PasswordMatchValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl {
	
	private final PasswordMatchValidator passwordMatchValidator;
	private final BusinessValidationRules businessValidationRules;
	
	private final UserMapper userMapper;
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final BusinessValidationRules emailAlreadyExistsValidator;
	
	public String registerUser(UserRegisterRequest request) {
		
		log.info("Starting user registration process for request: {}", request);
		businessValidationRules.validate(request);
		
		// Maping the request to a User entity
		log.info("Mapping user registration request to User entity.");
		User user = userMapper.map(request);
		
		log.info("setting password for user: {}", request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		log.info("Saving user to the database: {}", request.getEmail());
		userRepository.save(user);
		
		// Additional registration logic here
		log.info("User registered successfully: {}", request.getEmail());
		return "User registered successfully\n" + "email: "+request.getEmail() + "Phone.no: " + request.getPhone();
	}
	
	

}
