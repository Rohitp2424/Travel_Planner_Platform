package com.trvelplannerplatform.user.budget.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBudgetResponse {
	
	private Long budgetId;

    private String message;

}
