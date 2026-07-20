package com.trvelplannerplatform.user.iternary.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;

public interface IternaryRepository extends JpaRepository<Itinerary, Long> {

	 List<Itinerary> findByTrip(Trip trip);

}
