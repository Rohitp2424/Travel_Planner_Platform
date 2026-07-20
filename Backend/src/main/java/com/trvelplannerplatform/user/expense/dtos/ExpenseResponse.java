package com.trvelplannerplatform.user.expense.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.expense.enums.ExpenseCategoryEnum;

import lombok.Data;

@Data
public class ExpenseResponse {
	
	private Long expenseId;

    private String title;

    private String description;

    private BigDecimal amount;

    private ExpenseCategoryEnum category;

    private LocalDate expenseDate;

    private String paidBy;

    private String receiptUrl;

}
