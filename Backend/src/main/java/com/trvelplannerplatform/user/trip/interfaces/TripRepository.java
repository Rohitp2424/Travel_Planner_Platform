package com.trvelplannerplatform.user.trip.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

public interface TripRepository extends JpaRepository<Trip, Long>  {

	List<Trip> findByOwner(User user);
	
	@Query("""
			SELECT t
			FROM Trip t
			WHERE t.owner = :owner
			AND (
			    LOWER(t.tripName) LIKE LOWER(CONCAT('%', :keyword, '%'))
			    OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
			    OR LOWER(t.source) LIKE LOWER(CONCAT('%', :keyword, '%'))
			    OR LOWER(t.destination) LIKE LOWER(CONCAT('%', :keyword, '%'))
			)
			""")
			List<Trip> searchTrips(User owner, String keyword);
}
