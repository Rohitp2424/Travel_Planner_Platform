package com.trvelplannerplatform.user.expense.dtos;

import lombok.Data;

@Data
public class DeleteExpenseResponse {
	
	private Long expenseId;

    private String message;

}
