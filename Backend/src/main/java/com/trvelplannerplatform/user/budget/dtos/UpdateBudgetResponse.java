package com.trvelplannerplatform.user.budget.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UpdateBudgetResponse {

	private Long budgetId;

	private Long tripId;

	private BigDecimal totalBudget;

	private BigDecimal remainingBudget;

	private String currency;

	private String message;

	private LocalDateTime updatedAt;

}
