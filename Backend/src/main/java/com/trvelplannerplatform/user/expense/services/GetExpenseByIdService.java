package com.trvelplannerplatform.user.expense.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.expense.dtos.GetExpenseResponse;
import com.trvelplannerplatform.user.expense.entity.Expense;
import com.trvelplannerplatform.user.expense.helper.ExpenseHelper;
import com.trvelplannerplatform.user.expense.mapper.ExpenseMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetExpenseByIdService {

	private final ExpenseHelper expenseHelper;

	private final ExpenseMapper expenseMapper;

	public GetExpenseResponse getExpense(Long expenseId) {

		log.info("Fetching Expense with Id : {}", expenseId);

		// Logged-in User
		User loggedInUser = expenseHelper.getLoggedInUser();

		// Fetch Expense
		Expense expense = expenseHelper.getExpense(expenseId);

		// Fetch Trip
		Trip trip = expense.getTrip();

		// Validate Trip Owner
		expenseHelper.validateTripOwner(loggedInUser, trip);

		log.info("Expense found with Id : {}", expense.getId());

		// Map Entity -> DTO
		GetExpenseResponse response =
				expenseMapper.toGetResponse(expense);

		log.info("Returning GetExpenseResponse : {}", response);

		return response;

	}


}
