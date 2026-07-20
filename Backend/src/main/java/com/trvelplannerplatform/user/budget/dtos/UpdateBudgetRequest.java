package com.trvelplannerplatform.user.budget.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBudgetRequest {
	
	@NotNull(message = "Total Budget is required")
    @DecimalMin(value = "0.01", message = "Budget must be greater than zero")
    private BigDecimal totalBudget;

    @NotNull(message = "Currency is required")
    @Size(min = 3, max = 10, message = "Currency must be between 3 and 10 characters")
    private String currency;


}
