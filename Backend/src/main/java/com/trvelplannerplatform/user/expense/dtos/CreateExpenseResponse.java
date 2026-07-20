package com.trvelplannerplatform.user.expense.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateExpenseResponse {

	private Long expenseId;

    private Long tripId;

    private Long budgetId;

    private String title;

    private String message;

    private LocalDateTime createdAt;

}
