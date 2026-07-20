package com.trvelplannerplatform.user.budget.impls;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.budget.dtos.CreateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.CreateBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.DeleteBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.GetBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetResponse;
import com.trvelplannerplatform.user.budget.interfaces.BudgetService;
import com.trvelplannerplatform.user.budget.services.CreateBudgetService;
import com.trvelplannerplatform.user.budget.services.DeleteBudgetService;
import com.trvelplannerplatform.user.budget.services.GetBudgetService;
import com.trvelplannerplatform.user.budget.services.UpdateBudgetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

	private final CreateBudgetService createBudgetService;
	private final GetBudgetService getBudgetService;
	private final UpdateBudgetService updateBudgetService;
	private final DeleteBudgetService deleteBudgetService;
	
	@Override
	public CreateBudgetResponse createBudget(CreateBudgetRequest request) {
		
		return createBudgetService.createBudget(request);
	}

	@Override
	public GetBudgetResponse getBudget(Long tripId) {
		
		return getBudgetService.getBudget(tripId);
	}

	@Override
	public UpdateBudgetResponse updateBudget(Long budgetId, UpdateBudgetRequest request) {
		
		return updateBudgetService.updateBudget(budgetId, request);
	}

	@Override
	public DeleteBudgetResponse deleteBudget(Long budgetId) {
		
		return deleteBudgetService.deleteBudget(budgetId);
	}

}
