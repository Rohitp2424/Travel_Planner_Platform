package com.trvelplannerplatform.user.loginserviceimpls;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.dto.LoginRequest;
import com.trvelplannerplatform.user.dto.LoginResponse;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserLoginService;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.security.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
		
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		log.info("fetching user by email from database: {}", loginRequest.getEmail());
		User user = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new 
						UserValidationException(
								ErrorCodeEnum.USER_NOT_FOUND.getCode(), 
								ErrorCodeEnum.USER_NOT_FOUND.getMessage(), 
								HttpStatus.NOT_FOUND));
		
		if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new UserValidationException(
                    ErrorCodeEnum.INVALID_CREDENTIALS.getCode(),
                    ErrorCodeEnum.INVALID_CREDENTIALS.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
	
		String token = jwtService.generateToken(user);
		
		LoginResponse loginResponse = new LoginResponse(
				token,
			    "Bearer",
			    3600L
			    );
		
		return loginResponse;
   
	}
	

}
