package com.trvelplannerplatform.user.expense.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.budget.interfaces.BudgetRepository;
import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.expense.entity.Expense;
import com.trvelplannerplatform.user.expense.interfaces.ExpenseRepository;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExpenseHelper {
	
	private final UserRepository userRepository;

    private final TripRepository tripRepository;

    private final BudgetRepository budgetRepository;

    private final ExpenseRepository expenseRepository;
    
    public User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        log.info("Authenticated user : {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> {

                    log.error("User not found with email {}", email);

                    return new UserValidationException(
                            ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                            ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);
                });
    }
    
    public Trip getTrip(Long tripId) {

        log.info("Fetching Trip : {}", tripId);

        return tripRepository.findById(tripId)
                .orElseThrow(() -> {

                    log.error("Trip not found : {}", tripId);

                    return new UserValidationException(
                            ErrorCodeEnum.TRIP_NOT_FOUND.getCode(),
                            ErrorCodeEnum.TRIP_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);

                });

    }
    
    public Budget getBudget(Long budgetId) {

        log.info("Fetching Budget : {}", budgetId);

        return budgetRepository.findById(budgetId)
                .orElseThrow(() -> {

                    log.error("Budget not found : {}", budgetId);

                    return new UserValidationException(
                            ErrorCodeEnum.BUDGET_NOT_FOUND.getCode(),
                            ErrorCodeEnum.BUDGET_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);

                });

    }
    
    public Expense getExpense(Long expenseId) {

        log.info("Fetching Expense : {}", expenseId);

        return expenseRepository.findById(expenseId)
                .orElseThrow(() -> {

                    log.error("Expense not found : {}", expenseId);

                    return new UserValidationException(
                            ErrorCodeEnum.EXPENSE_NOT_FOUND.getCode(),
                            ErrorCodeEnum.EXPENSE_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);

                });

    }
    
    public void validateTripOwner(User user, Trip trip) {

        if (!trip.getOwner().getId().equals(user.getId())) {

            log.error("Unauthorized access by {}", user.getEmail());

            throw new UserValidationException(
                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getMessage(),
                    HttpStatus.FORBIDDEN);

        }

    }

}
