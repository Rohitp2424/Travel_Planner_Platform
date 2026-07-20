package com.trvelplannerplatform.user.budget.helper;

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
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BudgetHelper {

	private final UserRepository userRepository;

	private final TripRepository tripRepository;

	private final BudgetRepository budgetRepository;
	
	public User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        log.info("Authenticated User : {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> {

                    log.error("User not found : {}", email);

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
	
	 public void validateTripOwner(User user, Trip trip) {

	        if (!trip.getOwner().getId().equals(user.getId())) {

	            log.error("Unauthorized access by {}", user.getEmail());

	            throw new UserValidationException(
	                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getCode(),
	                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getMessage(),
	                    HttpStatus.FORBIDDEN);
	        }

	    }
	 
	 public Budget getBudget(Trip trip) {

	        log.info("Fetching Budget for Trip : {}", trip.getId());

	        return budgetRepository.findByTrip(trip)
	                .orElseThrow(() -> {

	                    log.error("Budget not found for Trip : {}", trip.getId());

	                    return new UserValidationException(
	                            ErrorCodeEnum.BUDGET_NOT_FOUND.getCode(),
	                            ErrorCodeEnum.BUDGET_NOT_FOUND.getMessage(),
	                            HttpStatus.NOT_FOUND);
	                });

	    }
	 
	 public Budget getUpdatedBudget(Long budgetId) {

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
	 
	 public void validateBudgetNotExists(Trip trip) {

	        if (budgetRepository.existsByTrip(trip)) {

	            log.error("Budget already exists for Trip : {}", trip.getId());

	            throw new UserValidationException(
	                    ErrorCodeEnum.BUDGET_ALREADY_EXISTS.getCode(),
	                    ErrorCodeEnum.BUDGET_ALREADY_EXISTS.getMessage(),
	                    HttpStatus.BAD_REQUEST);

	        }

	    }
	 
	 public void validateBudgetExists(Trip trip) {

	        if (!budgetRepository.existsByTrip(trip)) {

	            log.error("Budget does not exist for Trip : {}", trip.getId());

	            throw new UserValidationException(
	                    ErrorCodeEnum.BUDGET_NOT_FOUND.getCode(),
	                    ErrorCodeEnum.BUDGET_NOT_FOUND.getMessage(),
	                    HttpStatus.NOT_FOUND);

	        }

	    }

}
