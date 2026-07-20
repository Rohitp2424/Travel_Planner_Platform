package com.trvelplannerplatform.user.expense.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.expense.enums.ExpenseCategoryEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateExpenseRequest {

	@NotBlank(message = "Title is required")
	@Size(max = 150)
	private String title;

	@Size(max = 500)
	private String description;

	@NotNull(message = "Amount is required")
	@DecimalMin(value = "0.01", message = "Amount must be greater than zero")
	private BigDecimal amount;

	@NotNull(message = "Category is required")
	private ExpenseCategoryEnum category;

	@NotNull(message = "Expense date is required")
	private LocalDate expenseDate;

	@NotNull(message = "Paid By is required")
	private Long paidBy;

	@Size(max = 255)
	private String receiptUrl;

}
