package com.trvelplannerplatform.user.expense.services;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.interfaces.BudgetRepository;
import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.CreateExpenseResponse;
import com.trvelplannerplatform.user.expense.entity.Expense;
import com.trvelplannerplatform.user.expense.helper.ExpenseHelper;
import com.trvelplannerplatform.user.expense.interfaces.ExpenseRepository;
import com.trvelplannerplatform.user.expense.mapper.ExpenseMapper;
import com.trvelplannerplatform.user.interfaces.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateExpenseService {

	private final ExpenseRepository expenseRepository;

    private final BudgetRepository budgetRepository;

    private final UserRepository userRepository;

    private final ExpenseHelper expenseHelper;

    private final ExpenseMapper expenseMapper;
    
    @Transactional
    public CreateExpenseResponse createExpense(CreateExpenseRequest request) {
    	
    	log.info("Create Expense Request Received : {}", request);
    	
    	// Logged-in User
        User loggedInUser = expenseHelper.getLoggedInUser();
        
     // Fetch Trip
        Trip trip = expenseHelper.getTrip(request.getTripId());
        
        // Validate Trip Owner
        expenseHelper.validateTripOwner(loggedInUser, trip);
        
     // Fetch Budget
        Budget budget = expenseHelper.getBudget(request.getBudgetId());
        
        // Fetch Paid By User
        User paidBy = userRepository.findById(request.getPaidBy())
                .orElseThrow(() -> new UserValidationException(
                        ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                        ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND));
        
     // Validate Remaining Budget
        BigDecimal remainingBudget = budget.getRemainingBudget();
        
        if (remainingBudget.compareTo(request.getAmount()) < 0) {

            log.error("Insufficient remaining budget");

            throw new UserValidationException(
                    ErrorCodeEnum.INSUFFICIENT_BUDGET.getCode(),
                    ErrorCodeEnum.INSUFFICIENT_BUDGET.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        
        Expense expense =
                expenseMapper.toEntity(request, trip, budget, paidBy);

        // Save Expense
        expense = expenseRepository.save(expense);

        log.info("Expense Saved Successfully : {}", expense.getId());
        
        // Update Remaining Budget
        budget.setRemainingBudget(
                remainingBudget.subtract(request.getAmount()));

        budgetRepository.save(budget);

        log.info("Remaining Budget Updated : {}",
                budget.getRemainingBudget());

        // Prepare Response
        CreateExpenseResponse response =
                expenseMapper.toCreateResponse(expense);

        log.info("Returning CreateExpenseResponse : {}", response);

        
        
		return response;
    }

}
