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
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseRequest;
import com.trvelplannerplatform.user.expense.dtos.UpdateExpenseResponse;
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
public class UpdateExpenseService {
	
	private final ExpenseRepository expenseRepository;

    private final BudgetRepository budgetRepository;

    private final UserRepository userRepository;

    private final ExpenseHelper expenseHelper;

    private final ExpenseMapper expenseMapper;
    
    @Transactional
    public UpdateExpenseResponse updateExpense(
            Long expenseId,
            UpdateExpenseRequest request) {
    	
    	log.info("Update Expense Request Received : {}", request);

        // Logged-in User
        User loggedInUser = expenseHelper.getLoggedInUser();

        // Fetch Expense
        Expense expense = expenseHelper.getExpense(expenseId);

        // Fetch Trip
        Trip trip = expense.getTrip();

        // Validate Owner
        expenseHelper.validateTripOwner(loggedInUser, trip);

        // Fetch Budget
        Budget budget = expense.getBudget();
        
     // Fetch Paid By User
        User paidBy = userRepository.findById(request.getPaidBy())
                .orElseThrow(() -> new UserValidationException(
                        ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                        ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND));
    	
        BigDecimal oldAmount = expense.getAmount();

        BigDecimal newAmount = request.getAmount();
        
     // Restore previous expense amount
        BigDecimal remainingBudget =
                budget.getRemainingBudget().add(oldAmount);
        
     // Validate Budget
        if (remainingBudget.compareTo(newAmount) < 0) {

            log.error("Insufficient Remaining Budget");

            throw new UserValidationException(
                    ErrorCodeEnum.INSUFFICIENT_BUDGET.getCode(),
                    ErrorCodeEnum.INSUFFICIENT_BUDGET.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        
     // Deduct new expense amount
        remainingBudget = remainingBudget.subtract(newAmount);

        budget.setRemainingBudget(remainingBudget);
        
        budgetRepository.save(budget);
        
        log.info("Budget Updated Successfully");
        
     // Update Expense Entity
        expenseMapper.updateEntity(expense, request, paidBy);

        expense = expenseRepository.save(expense);

        log.info("Expense Updated Successfully : {}", expense.getId());

        UpdateExpenseResponse response =
                expenseMapper.toUpdateResponse(expense);

        log.info("Returning UpdateExpenseResponse : {}", response);

        return response;
    	
    }


}
