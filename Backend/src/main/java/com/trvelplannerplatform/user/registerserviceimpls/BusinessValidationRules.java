package com.trvelplannerplatform.user.registerserviceimpls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.dto.UserRegisterRequest;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserRegisterService;
import com.trvelplannerplatform.user.interfaces.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BusinessValidationRules implements UserRegisterService<UserRegisterRequest> {

	 private final UserRepository userRepository;
	 
	 @Value("${registration.enabled}")
	 private boolean registrationEnabled;
	
	@Override
	public void validate(UserRegisterRequest request) {
		
		if (!request.getPassword().equals(request.getConfirmPassword())) {
			log.error("Password and confirm password do not match for user registration request: {}", request);
			throw new UserValidationException(
					ErrorCodeEnum.PASSWORDS_DO_NOT_MATCH.getCode(),
					ErrorCodeEnum.PASSWORDS_DO_NOT_MATCH.getMessage(),
					HttpStatus.BAD_REQUEST
			);
		}
		
		if (userRepository.existsByEmail(request.getEmail())) {
			log.error("Email already exists for user registration request: {}", request);
            throw new UserValidationException(
                    ErrorCodeEnum.EMAIL_ALREADY_EXISTS.getCode(),
                    ErrorCodeEnum.EMAIL_ALREADY_EXISTS.getMessage(),
                    HttpStatus.CONFLICT
            );
        }
		
		if(userRepository.existsByPhone(request.getPhone())) {
			log.error("Phone number already exists for user registration request: {}", request);
			throw new UserValidationException(
					ErrorCodeEnum.PHONE_NUMBER_ALREADY_EXISTED.getCode(),
					ErrorCodeEnum.PHONE_NUMBER_ALREADY_EXISTED.getMessage(),
					HttpStatus.CONFLICT
			);
		}
		
		if (!Boolean.TRUE.equals(request.getTermsAccepted())) {
			log.error("Terms and conditions not accepted for user registration request: {}", request);
		    throw new UserValidationException(
		            ErrorCodeEnum.TERMS_NOT_ACCEPTED.getCode(),
		            ErrorCodeEnum.TERMS_NOT_ACCEPTED.getMessage(),
		            HttpStatus.BAD_REQUEST
		    );
		}
		
		if (!registrationEnabled) {
			log.error("User registration is currently disabled for user registration request: {}", request);
		    throw new UserValidationException(
		            ErrorCodeEnum.REGISTRATION_DISABLED.getCode(),
		            ErrorCodeEnum.REGISTRATION_DISABLED.getMessage(),
		            HttpStatus.FORBIDDEN
		    );
		}
		
	}
	
	

}
