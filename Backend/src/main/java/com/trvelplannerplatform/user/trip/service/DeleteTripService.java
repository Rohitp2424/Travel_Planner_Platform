package com.trvelplannerplatform.user.trip.service;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.trip.helper.TripHelper;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;
import com.trvelplannerplatform.user.trip.tripdtos.DeleteTripResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteTripService {
	
	private final TripHelper tripHelper;
	
	private final TripRepository tripRepository;
	
	 public DeleteTripResponse delete(Long tripId){
		 
		 User user = tripHelper.getLoggedInUser();
		 
		 Trip trip = tripHelper.getTrip(tripId);
		 
		 tripHelper.validateTripOwner(user, trip);
		 
		 tripRepository.delete(trip);

		    log.info("Trip deleted successfully. Trip Id: {}", tripId);

		    return new DeleteTripResponse(
		            tripId,
		            "Trip deleted successfully"
		    );

     }

}
