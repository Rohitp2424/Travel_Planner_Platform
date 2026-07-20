package com.trvelplannerplatform.user.budget.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trvelplannerplatform.user.entity.Budget;
import com.trvelplannerplatform.user.entity.Trip;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByTrip(Trip trip);

    boolean existsByTrip(Trip trip);

}