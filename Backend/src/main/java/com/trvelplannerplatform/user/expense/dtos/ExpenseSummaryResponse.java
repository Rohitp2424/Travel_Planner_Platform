package com.trvelplannerplatform.user.expense.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseSummaryResponse {
	
	private BigDecimal totalExpenses;

    private BigDecimal remainingBudget;

    private Integer totalExpenseCount;


}
