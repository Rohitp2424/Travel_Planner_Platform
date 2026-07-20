package com.trvelplannerplatform.user.exception;

import org.springframework.http.HttpStatus;

import com.trvelplannerplatform.user.enums.ErrorCodeEnum;

public class UserValidationException extends RuntimeException {
	
	private final String errorCode;
    private final HttpStatus httpStatus;

    public UserValidationException(String errorCode,
                                   String errorMessage,
                                   HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
