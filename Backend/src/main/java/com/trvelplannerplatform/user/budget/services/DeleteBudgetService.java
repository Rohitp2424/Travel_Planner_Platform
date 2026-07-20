package com.trvelplannerplatform.user.budget.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.dtos.DeleteBudgetResponse;
import com.trvelplannerplatform.user.budget.helper.BudgetHelper;
import com.trvelplannerplatform.user.budget.interfaces.BudgetRepository;
import com.trvelplannerplatform.user.budget.mapper.BudgetMapper;
import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteBudgetService {

	private final BudgetRepository budgetRepository;

	private final BudgetHelper budgetHelper;

	private final BudgetMapper budgetMapper;

	public DeleteBudgetResponse deleteBudget(Long budgetId) {


		log.info("Delete Budget request received for Budget Id : {}", budgetId);

		// Get Logged-in User
		User loggedInUser = budgetHelper.getLoggedInUser();

		// Fetch Budget
		Budget budget = budgetHelper.getUpdatedBudget(budgetId);

		// Fetch Trip
		Trip trip = budget.getTrip();

		// Validate Trip Owner
		budgetHelper.validateTripOwner(loggedInUser, trip);

		log.info("Deleting Budget with Id : {}", budget.getId());

		// Delete Budget
		budgetRepository.delete(budget);

		log.info("Budget deleted successfully : {}", budget.getId());

		// Prepare Response
		DeleteBudgetResponse response =
				budgetMapper.toDeleteResponse(budget);

		log.info("Returning DeleteBudgetResponse : {}", response);


		return response;
	}

}
