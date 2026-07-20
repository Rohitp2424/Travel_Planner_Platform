package com.trvelplannerplatform.user.expense.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.DeleteExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.ExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.GetExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseResponse;
import com.trvelplannerplatform.user.expense.entity.Expense;

@Component
public class ExpenseMapper {

	public Expense toEntity(CreateExpenseRequest request,
			Trip trip,
			Budget budget,
			User paidBy) {

		Expense expense = new Expense();

		expense.setTrip(trip);
		expense.setBudget(budget);
		expense.setTitle(request.getTitle());
		expense.setDescription(request.getDescription());
		expense.setAmount(request.getAmount());
		expense.setCategory(request.getCategory());
		expense.setExpenseDate(request.getExpenseDate());
		expense.setPaidBy(paidBy);
		expense.setReceiptUrl(request.getReceiptUrl());

		expense.setCreatedAt(LocalDateTime.now());
		expense.setUpdatedAt(LocalDateTime.now());

		return expense;
	}

	public CreateExpenseResponse toCreateResponse(Expense expense) {

		CreateExpenseResponse response = new CreateExpenseResponse();

		response.setExpenseId(expense.getId());
		response.setTripId(expense.getTrip().getId());
		response.setBudgetId(expense.getBudget().getId());
		response.setTitle(expense.getTitle());
		response.setMessage("Expense created successfully");
		response.setCreatedAt(expense.getCreatedAt());

		return response;
	}

	public GetExpenseResponse toGetResponse(Expense expense) {

		GetExpenseResponse response = new GetExpenseResponse();

		response.setExpenseId(expense.getId());
		response.setTripId(expense.getTrip().getId());
		response.setBudgetId(expense.getBudget().getId());
		response.setTitle(expense.getTitle());
		response.setDescription(expense.getDescription());
		response.setAmount(expense.getAmount());
		response.setCategory(expense.getCategory());
		response.setExpenseDate(expense.getExpenseDate());
		response.setPaidBy(expense.getPaidBy().getEmail());
		response.setReceiptUrl(expense.getReceiptUrl());
		response.setCreatedAt(expense.getCreatedAt());
		response.setUpdatedAt(expense.getUpdatedAt());

		return response;
	}

	public ExpenseResponse toExpenseResponse(Expense expense) {

		ExpenseResponse response = new ExpenseResponse();

		response.setExpenseId(expense.getId());
		response.setTitle(expense.getTitle());
		response.setDescription(expense.getDescription());
		response.setAmount(expense.getAmount());
		response.setCategory(expense.getCategory());
		response.setExpenseDate(expense.getExpenseDate());
		response.setPaidBy(expense.getPaidBy().getEmail());
		response.setReceiptUrl(expense.getReceiptUrl());

		return response;
	}

	public List<ExpenseResponse> toExpenseResponseList(List<Expense> expenses) {

		return expenses.stream()
				.map(this::toExpenseResponse)
				.toList();
	}

	public void updateEntity(Expense expense,
			UpdateExpenseRequest request,
			User paidBy) {

		expense.setTitle(request.getTitle());
		expense.setDescription(request.getDescription());
		expense.setAmount(request.getAmount());
		expense.setCategory(request.getCategory());
		expense.setExpenseDate(request.getExpenseDate());
		expense.setPaidBy(paidBy);
		expense.setReceiptUrl(request.getReceiptUrl());

		expense.setUpdatedAt(LocalDateTime.now());
	}
	
	public UpdateExpenseResponse toUpdateResponse(Expense expense) {

        UpdateExpenseResponse response = new UpdateExpenseResponse();

        response.setExpenseId(expense.getId());
        response.setTripId(expense.getTrip().getId());
        response.setTitle(expense.getTitle());
        response.setMessage("Expense updated successfully");
        response.setUpdatedAt(expense.getUpdatedAt());

        return response;
    }

	public DeleteExpenseResponse toDeleteResponse(Expense expense) {

        DeleteExpenseResponse response = new DeleteExpenseResponse();

        response.setExpenseId(expense.getId());
        response.setMessage("Expense deleted successfully");

        return response;
    }

}
