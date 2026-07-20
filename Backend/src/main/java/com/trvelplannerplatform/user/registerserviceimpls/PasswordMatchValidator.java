package com.trvelplannerplatform.user.registerserviceimpls;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.dto.UserRegisterRequest;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserRegisterService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PasswordMatchValidator implements UserRegisterService {

	@Override
	public void validate(UserRegisterRequest request) {
		log.info("Validating password match for user registration request.");
		// TODO Auto-generated method stub
		if (!request.getPassword().equals(request.getConfirmPassword())) {
			throw new UserValidationException(
					ErrorCodeEnum.PASSWORDS_DO_NOT_MATCH.getCode(),
					ErrorCodeEnum.PASSWORDS_DO_NOT_MATCH.getMessage(),
					HttpStatus.BAD_REQUEST
			);
		}
	}

}
