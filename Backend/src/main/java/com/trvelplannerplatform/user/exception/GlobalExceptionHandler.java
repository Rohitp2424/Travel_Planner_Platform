package com.trvelplannerplatform.user.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trvelplannerplatform.user.dto.ErrorResponse;
import com.trvelplannerplatform.user.dto.ValidationError;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<ValidationError> validationErrors =
                ex.getBindingResult()
                  .getFieldErrors()
                  .stream()
                  .map(this::mapFieldError)
                  .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
        		LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                request.getRequestURI(),
                validationErrors
		);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    private ValidationError mapFieldError(FieldError fieldError) {

        ErrorCodeEnum errorCode;
        ErrorCodeEnum errorMessage;
        

        try {
            errorCode = ErrorCodeEnum.fromName(fieldError.getDefaultMessage());
        } catch (IllegalArgumentException ex) {
            errorCode = ErrorCodeEnum.VALIDATION_ERROR;
        }

        return new ValidationError(
                fieldError.getField(),
                errorCode.getCode(), 
       		 errorCode.getMessage()
        );
    }
    
    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ErrorResponse> handleUserValidationException(
            UserValidationException ex,
            HttpServletRequest request) {

        ValidationError validationError = new ValidationError(
                null,
                ex.getErrorCode(),
                ex.getMessage()
        );

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getHttpStatus().value(),
                ex.getHttpStatus().getReasonPhrase(),
                request.getRequestURI(),
                List.of(validationError)
        );

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(response);
    }
}
	