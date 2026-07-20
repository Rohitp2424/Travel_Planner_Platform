package com.trvelplannerplatform.user.expense.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.trvelplannerplatform.user.expense.enums.ExpenseCategoryEnum;

import lombok.Data;

@Data
public class GetExpenseResponse {
	
	private Long expenseId;

    private Long tripId;

    private Long budgetId;

    private String title;

    private String description;

    private BigDecimal amount;

    private ExpenseCategoryEnum category;

    private LocalDate expenseDate;

    private String paidBy;

    private String receiptUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
