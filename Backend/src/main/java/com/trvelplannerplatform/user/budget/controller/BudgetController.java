package com.trvelplannerplatform.user.budget.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.budget.dtos.CreateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.CreateBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.DeleteBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.GetBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetResponse;
import com.trvelplannerplatform.user.budget.interfaces.BudgetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user/v1")
@Slf4j
@RequiredArgsConstructor
public class BudgetController {
	
	private final BudgetService budgetService;
	
	@PostMapping("/budgets")
    public CreateBudgetResponse createBudget(
            @Valid @RequestBody CreateBudgetRequest request) {

        log.info("Create Budget request received : {}", request);

        CreateBudgetResponse response =
                budgetService.createBudget(request);

        log.info("Budget created successfully : {}", response);

        return response;
    }
	
	@GetMapping("/budgets/{tripId}")
	public GetBudgetResponse getBudget(
	        @PathVariable Long tripId) {

	    log.info("Get Budget request received for Trip : {}", tripId);

	    GetBudgetResponse response =
	            budgetService.getBudget(tripId);

	    log.info("Returning Budget : {}", response);

	    return response;
	}
	
	@PutMapping("/budgets/{budgetId}")
	public UpdateBudgetResponse updateBudget(
	        @PathVariable Long budgetId,
	        @Valid @RequestBody UpdateBudgetRequest request) {

	    log.info("Update Budget request received : {}", budgetId);

	    UpdateBudgetResponse response =
	            budgetService.updateBudget(budgetId, request);

	    log.info("Budget updated successfully : {}", response);

	    return response;
	}

	@DeleteMapping("/budgets/{budgetId}")
	public DeleteBudgetResponse deleteBudget(
	        @PathVariable Long budgetId) {

	    log.info("Delete Budget request received for Budget Id : {}", budgetId);

	    DeleteBudgetResponse response =
	            budgetService.deleteBudget(budgetId);

	    log.info("Budget deleted successfully : {}", response);

	    return response;
	}

}
