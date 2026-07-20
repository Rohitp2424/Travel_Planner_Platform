package com.trvelplannerplatform.user.expense.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.expense.dtos.ExpenseResponse;
import com.trvelplannerplatform.user.expense.dtos.GetAllExpensesResponse;
import com.trvelplannerplatform.user.expense.entity.Expense;
import com.trvelplannerplatform.user.expense.helper.ExpenseHelper;
import com.trvelplannerplatform.user.expense.interfaces.ExpenseRepository;
import com.trvelplannerplatform.user.expense.mapper.ExpenseMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllExpenses {
	
	private final ExpenseRepository expenseRepository;

    private final ExpenseHelper expenseHelper;

    private final ExpenseMapper expenseMapper;
    
    public GetAllExpensesResponse getAllExpenses(Long tripId) {
    	
    	 log.info("Fetching all expenses for Trip : {}", tripId);

         // Logged-in User
         User loggedInUser = expenseHelper.getLoggedInUser();
         
      // Fetch Trip
         Trip trip = expenseHelper.getTrip(tripId);

         // Validate Owner
         expenseHelper.validateTripOwner(loggedInUser, trip);
         
         List<Expense> expenses =
                 expenseRepository.findByTripOrderByExpenseDateDesc(trip);
         
         log.info("Total Expenses Found : {}", expenses.size());

         // Map Entity -> DTO
         List<ExpenseResponse> responses =
                 expenseMapper.toExpenseResponseList(expenses);
         
      // Build Response
         GetAllExpensesResponse response =
                 new GetAllExpensesResponse();

         response.setTotalExpenses(responses.size());
         response.setExpenses(responses);

         log.info("Returning {} expenses", responses.size());

         return response;
    	
    }

}
