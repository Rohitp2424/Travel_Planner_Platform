package com.trvelplannerplatform.user.budget.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.dtos.CreateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.CreateBudgetResponse;
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
public class CreateBudgetService {

	private final BudgetRepository budgetRepository;

	private final BudgetHelper budgetHelper;

	private final BudgetMapper budgetMapper;

	public CreateBudgetResponse createBudget(CreateBudgetRequest request) {

		log.info("Create Budget Request Received : {}", request);

		// Get Logged-in User
		User loggedInUser = budgetHelper.getLoggedInUser();

		// Fetch Trip
		Trip trip = budgetHelper.getTrip(request.getTripId());

		 // Validate Trip Owner
        budgetHelper.validateTripOwner(loggedInUser, trip);
        
        // Ensure Budget doesn't already exist
        budgetHelper.validateBudgetNotExists(trip);
        
     // Convert Request -> Entity
        Budget budget = budgetMapper.toEntity(request, trip);
        
        log.info("Saving Budget for Trip : {}", trip.getId());
        
        // Save Budget
        budget = budgetRepository.save(budget);
        
        log.info("Budget Saved Successfully. Budget Id : {}", budget.getId());
        
     // Convert Entity -> Response
        CreateBudgetResponse response =
                budgetMapper.toCreateResponse(budget);
        
        log.info("Returning CreateBudgetResponse : {}", response);
        
		return response;
	}


}
