package com.trvelplannerplatform.user.expense.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.expense.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	List<Expense> findByTrip(Trip trip);

    List<Expense> findByBudget(Budget budget);

    List<Expense> findByTripOrderByExpenseDateDesc(Trip trip);


}
