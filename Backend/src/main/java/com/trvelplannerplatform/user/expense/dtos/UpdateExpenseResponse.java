package com.trvelplannerplatform.user.expense.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UpdateExpenseResponse {
	
	private Long expenseId;

    private Long tripId;

    private String title;

    private String message;

    private LocalDateTime updatedAt;


}
