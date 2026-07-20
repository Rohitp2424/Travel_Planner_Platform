package com.trvelplannerplatform.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String path;

    private List<ValidationError> errors;
    
    
    


}
