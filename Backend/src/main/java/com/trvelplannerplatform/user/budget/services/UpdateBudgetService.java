package com.trvelplannerplatform.user.budget.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetResponse;
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
public class UpdateBudgetService {

	private final BudgetRepository budgetRepository;

	private final BudgetHelper budgetHelper;

	private final BudgetMapper budgetMapper;

	public UpdateBudgetResponse updateBudget(Long budgetId,
			UpdateBudgetRequest request) {

		log.info("Update Budget Request received for Budget Id : {}", budgetId);

		// Get Logged-in User
		User loggedInUser = budgetHelper.getLoggedInUser();

		// Fetch Budget
		Budget budget = budgetHelper.getUpdatedBudget(budgetId);

		// Fetch Trip
		Trip trip = budget.getTrip();

		// Validate Owner
		budgetHelper.validateTripOwner(loggedInUser, trip);

		// Update Entity
		budgetMapper.updateEntity(budget, request);

		log.info("Saving Updated Budget : {}", budget.getId());

		// Save
		budget = budgetRepository.save(budget);

		log.info("Budget Updated Successfully : {}", budget.getId());

		// Prepare Response
		UpdateBudgetResponse response =
				budgetMapper.toUpdateResponse(budget);

		log.info("Returning UpdateBudgetResponse : {}", response);

		return response;

	}

}
