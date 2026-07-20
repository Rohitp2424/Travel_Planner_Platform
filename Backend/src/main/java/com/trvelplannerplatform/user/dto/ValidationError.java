package com.trvelplannerplatform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {
	
	  private String field;
	  
	  private String errorCode;

	  private String errorMessage;

}
