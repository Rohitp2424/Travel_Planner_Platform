package com.trvelplannerplatform.user.expense.dtos;

import java.util.List;

import lombok.Data;

@Data
public class GetAllExpensesResponse {

	private Integer totalExpenses;

	private List<ExpenseResponse> expenses;


}
