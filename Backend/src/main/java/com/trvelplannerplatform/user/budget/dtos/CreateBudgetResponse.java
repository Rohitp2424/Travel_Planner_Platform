package com.trvelplannerplatform.user.budget.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBudgetResponse {
	
	private Long budgetId;

    private Long tripId;

    private BigDecimal totalBudget;

    private BigDecimal remainingBudget;

    private String currency;

    private String message;

    private LocalDateTime createdAt;


}
