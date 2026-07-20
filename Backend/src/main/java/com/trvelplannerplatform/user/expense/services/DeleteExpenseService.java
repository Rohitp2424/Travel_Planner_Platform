package com.trvelplannerplatform.user.expense.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.interfaces.BudgetRepository;
import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.expense.dtos.DeleteExpenseResponse;
import com.trvelplannerplatform.user.expense.entity.Expense;
import com.trvelplannerplatform.user.expense.helper.ExpenseHelper;
import com.trvelplannerplatform.user.expense.interfaces.ExpenseRepository;
import com.trvelplannerplatform.user.expense.mapper.ExpenseMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteExpenseService {
	
	private final ExpenseRepository expenseRepository;

    private final BudgetRepository budgetRepository;

    private final ExpenseHelper expenseHelper;

    private final ExpenseMapper expenseMapper;
    
    @Transactional
    public DeleteExpenseResponse deleteExpense(Long expenseId) {
    	
    	log.info("Delete Expense Request Received for Expense Id : {}", expenseId);

        // Logged-in User
        User loggedInUser = expenseHelper.getLoggedInUser();

        // Fetch Expense
        Expense expense = expenseHelper.getExpense(expenseId);

        // Fetch Trip
        Trip trip = expense.getTrip();

        // Validate Trip Owner
        expenseHelper.validateTripOwner(loggedInUser, trip);

        // Fetch Budget
        Budget budget = expense.getBudget();
        
     // Restore Remaining Budget
        BigDecimal updatedRemainingBudget =
                budget.getRemainingBudget().add(expense.getAmount());
        
        budget.setRemainingBudget(updatedRemainingBudget);

        budgetRepository.save(budget);
        
        log.info("Remaining Budget Updated : {}", updatedRemainingBudget);

        // Delete Expense
        expenseRepository.delete(expense);
        
        log.info("Expense Deleted Successfully : {}", expenseId);

        // Prepare Response
        DeleteExpenseResponse response =
                expenseMapper.toDeleteResponse(expense);

        log.info("Returning DeleteExpenseResponse : {}", response);

		return response;
    	
    }


}
