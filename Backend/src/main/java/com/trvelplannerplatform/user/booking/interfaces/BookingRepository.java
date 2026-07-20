package com.trvelplannerplatform.user.booking.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trvelplannerplatform.user.booking.entity.Booking;
import com.trvelplannerplatform.user.entity.Trip;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	List<Booking> findByTrip(Trip trip);

    Optional<Booking> findById(Long id);

    boolean existsByTripAndBookingReference(Trip trip, String bookingReference);

}
