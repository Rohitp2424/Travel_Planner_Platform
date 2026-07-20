package com.trvelplannerplatform.user.expense.interfaces;

import com.trvelplannerplatform.user.expense.dtos.CreateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.DeleteExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.GetAllExpensesResponse;
import com.trvelplannerplatform.user.expense.dtos.GetExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseResponse;

public interface ExpenseService {
	
	CreateExpenseResponse createExpense(CreateExpenseRequest request);

    GetExpenseResponse getExpense(Long expenseId);

    GetAllExpensesResponse getAllExpenses(Long tripId);

    UpdateExpenseResponse updateExpense(Long expenseId,
                                        UpdateExpenseRequest request);

    DeleteExpenseResponse deleteExpense(Long expenseId);

}
