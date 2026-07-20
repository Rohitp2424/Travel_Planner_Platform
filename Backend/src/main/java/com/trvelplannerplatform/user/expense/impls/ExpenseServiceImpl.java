package com.trvelplannerplatform.user.expense.impls;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.expense.dtos.CreateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.DeleteExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.GetAllExpensesResponse;
import com.trvelplannerplatform.user.expense.dtos.GetExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseResponse;
import com.trvelplannerplatform.user.expense.interfaces.ExpenseService;
import com.trvelplannerplatform.user.expense.services.CreateExpenseService;
import com.trvelplannerplatform.user.expense.services.DeleteExpenseService;
import com.trvelplannerplatform.user.expense.services.GetAllExpenses;
import com.trvelplannerplatform.user.expense.services.GetExpenseByIdService;
import com.trvelplannerplatform.user.expense.services.UpdateExpenseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
	
	private final CreateExpenseService createExpenseService;
	private final GetExpenseByIdService getExpenseById;
	private final GetAllExpenses getAllExpenses;
	private final UpdateExpenseService updateExpense;
	private final DeleteExpenseService deleteExpense;

	@Override
	public CreateExpenseResponse createExpense(CreateExpenseRequest request) {
		
		return createExpenseService.createExpense(request);
	}

	@Override
	public GetExpenseResponse getExpense(Long expenseId) {
		
		return getExpenseById.getExpense(expenseId);
	}

	@Override
	public GetAllExpensesResponse getAllExpenses(Long tripId) {
		
		return getAllExpenses.getAllExpenses(tripId);
	}

	@Override
	public UpdateExpenseResponse updateExpense(Long expenseId, UpdateExpenseRequest request) {
		
		return updateExpense.updateExpense(expenseId, request);
	}

	@Override
	public DeleteExpenseResponse deleteExpense(Long expenseId) {
		
		return deleteExpense.deleteExpense(expenseId);
	}

}
