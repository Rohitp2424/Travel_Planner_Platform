package com.trvelplannerplatform.user.budget.interfaces;

import com.trvelplannerplatform.user.budget.dtos.CreateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.CreateBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.DeleteBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.GetBudgetResponse;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetRequest;
import com.trvelplannerplatform.user.budget.dtos.UpdateBudgetResponse;

public interface BudgetService {

	public CreateBudgetResponse createBudget(CreateBudgetRequest request);

	public GetBudgetResponse getBudget(Long tripId);

	public UpdateBudgetResponse updateBudget(
			Long budgetId,
			UpdateBudgetRequest request);

	public DeleteBudgetResponse deleteBudget(Long budgetId);

}
