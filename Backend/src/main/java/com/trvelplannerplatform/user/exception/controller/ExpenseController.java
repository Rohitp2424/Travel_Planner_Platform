package com.trvelplannerplatform.user.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.expense.dtos.CreateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.DeleteExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.GetAllExpensesResponse;
import com.trvelplannerplatform.user.expense.dtos.GetExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseResponse;
import com.trvelplannerplatform.user.expense.interfaces.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user/v1/")
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping("/expenses")
	@ResponseStatus(HttpStatus.CREATED)
	public CreateExpenseResponse createExpense(
	        @Valid @RequestBody CreateExpenseRequest request) {

	    log.info("Create Expense Controller Hit");

	    CreateExpenseResponse response =
	    		expenseService.createExpense(request);

	    log.info("Returning CreateExpenseResponse : {}", response);

	    return response;
	}
	
	@GetMapping("/expenses/{expenseId}")
	public GetExpenseResponse getExpense(
	        @PathVariable Long expenseId) {

	    log.info("Get Expense Controller Hit : {}", expenseId);

	    GetExpenseResponse response =
	            expenseService.getExpense(expenseId);

	    log.info("Returning GetExpenseResponse : {}", response);

	    return response;
	}
	
	@GetMapping("/expenses/trip/{tripId}")
	public GetAllExpensesResponse getAllExpenses(
	        @PathVariable Long tripId) {

	    log.info("Get All Expenses Controller Hit");

	    GetAllExpensesResponse response =
	            expenseService.getAllExpenses(tripId);

	    log.info("Returning GetAllExpensesResponse : {}", response);

	    return response;
	}
	
	@PutMapping("/expenses/{expenseId}")
	public UpdateExpenseResponse updateExpense(
	        @PathVariable Long expenseId,
	        @Valid @RequestBody UpdateExpenseRequest request) {

	    log.info("Update Expense Controller Hit");

	    return expenseService.updateExpense(expenseId, request);
	}

	@DeleteMapping("/expenses/{expenseId}")
	public DeleteExpenseResponse deleteExpense(
	        @PathVariable Long expenseId) {

	    log.info("Delete Expense Controller Hit");

	    DeleteExpenseResponse response =
	            expenseService.deleteExpense(expenseId);

	    log.info("Returning DeleteExpenseResponse : {}", response);

	    return response;
	}
}
