package com.trvelplannerplatform.user.trip.service;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.trip.helper.TripHelper;
import com.trvelplannerplatform.user.trip.mapper.TripMapper;
import com.trvelplannerplatform.user.trip.tripdtos.TripResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetTripByIdService {
	
	private final TripHelper tripHelper;
	
	private final TripMapper tripMapper;
	
	public TripResponse getTripById(Long tripId){

		log.info("Fetching trip with id {}", tripId);
		
		User user = tripHelper.getLoggedInUser();
		
		Trip trip = tripHelper.getTrip(tripId);
		
		tripHelper.validateTripOwner(user, trip);
		
		TripResponse response = tripMapper.toResponse(trip);
		
        return response;
    }

}
