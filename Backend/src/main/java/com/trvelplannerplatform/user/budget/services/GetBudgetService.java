package com.trvelplannerplatform.user.budget.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.dtos.GetBudgetResponse;
import com.trvelplannerplatform.user.budget.helper.BudgetHelper;
import com.trvelplannerplatform.user.budget.mapper.BudgetMapper;
import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetBudgetService {

	private final BudgetHelper budgetHelper;

    private final BudgetMapper budgetMapper;
    
    public GetBudgetResponse getBudget(Long tripId) {
    	
    	log.info("Fetching budget for Trip : {}", tripId);
    	
    	// Get Logged-in User
        User loggedInUser = budgetHelper.getLoggedInUser();
        
     // Fetch Trip
        Trip trip = budgetHelper.getTrip(tripId);
        
     // Validate Trip Owner
        budgetHelper.validateTripOwner(loggedInUser, trip);
        
     // Fetch Budget
        Budget budget = budgetHelper.getBudget(trip);
        
        log.info("Budget found with Id : {}", budget.getId());
        
        // Map Entity -> Response
        GetBudgetResponse response =
                budgetMapper.toGetResponse(budget);
        
        log.info("Returning GetBudgetResponse : {}", response);
    	
		return response;
    	
    }
}
