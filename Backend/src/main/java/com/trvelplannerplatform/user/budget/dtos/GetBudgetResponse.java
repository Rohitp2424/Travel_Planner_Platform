package com.trvelplannerplatform.user.budget.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GetBudgetResponse {
	
	 private Long budgetId;

	 private Long tripId;

	 private BigDecimal totalBudget;

	 private BigDecimal remainingBudget;

	 private String currency;

	 private LocalDateTime createdAt;

	 private LocalDateTime updatedAt;


}
