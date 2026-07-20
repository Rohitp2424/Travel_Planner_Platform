package com.trvelplannerplatform.user.budget.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.budget.dtos.CreateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.CreateBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.DeleteBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.GetBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetResponse;
import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;

@Component
public class BudgetMapper {
	
	 /**
     * CreateBudgetRequest -> Budget Entity
     */
    public Budget toEntity(CreateBudgetRequest request, Trip trip) {

        Budget budget = new Budget();

        budget.setTrip(trip);
        budget.setTotalBudget(request.getTotalBudget());
        budget.setRemainingBudget(request.getTotalBudget());
        budget.setCurrency(request.getCurrency());
        budget.setCreatedAt(LocalDateTime.now());
        budget.setUpdatedAt(LocalDateTime.now());

        return budget;
    }
    
    /**
     * Budget Entity -> CreateBudgetResponse
     */
    public CreateBudgetResponse toCreateResponse(Budget budget) {

        CreateBudgetResponse response = new CreateBudgetResponse();

        response.setBudgetId(budget.getId());
        response.setTripId(budget.getTrip().getId());
        response.setTotalBudget(budget.getTotalBudget());
        response.setRemainingBudget(budget.getRemainingBudget());
        response.setCurrency(budget.getCurrency());
        response.setCreatedAt(budget.getCreatedAt());
        response.setMessage("Budget created successfully");

        return response;
    }

    /**
     * Budget Entity -> GetBudgetResponse
     */
    public GetBudgetResponse toGetResponse(Budget budget) {

        GetBudgetResponse response = new GetBudgetResponse();

        response.setBudgetId(budget.getId());
        response.setTripId(budget.getTrip().getId());
        response.setTotalBudget(budget.getTotalBudget());
        response.setRemainingBudget(budget.getRemainingBudget());
        response.setCurrency(budget.getCurrency());
        response.setCreatedAt(budget.getCreatedAt());
        response.setUpdatedAt(budget.getUpdatedAt());

        return response;
    }
    
    /**
     * Update Budget Entity
     */
    public void updateEntity(Budget budget,
                             UpdateBudgetRequest request) {

        budget.setTotalBudget(request.getTotalBudget());
        budget.setCurrency(request.getCurrency());
        budget.setUpdatedAt(LocalDateTime.now());

    }
    
    /**
     * Budget Entity -> UpdateBudgetResponse
     */
    public UpdateBudgetResponse toUpdateResponse(Budget budget) {

        UpdateBudgetResponse response = new UpdateBudgetResponse();

        response.setBudgetId(budget.getId());
        response.setTripId(budget.getTrip().getId());
        response.setTotalBudget(budget.getTotalBudget());
        response.setRemainingBudget(budget.getRemainingBudget());
        response.setCurrency(budget.getCurrency());
        response.setUpdatedAt(budget.getUpdatedAt());
        response.setMessage("Budget updated successfully");

        return response;
    }
    
    /**
     * Budget Entity -> DeleteBudgetResponse
     */
    public DeleteBudgetResponse toDeleteResponse(Budget budget) {

        DeleteBudgetResponse response = new DeleteBudgetResponse();

        response.setBudgetId(budget.getId());
        response.setMessage("Budget deleted successfully");

        return response;
    }
}
