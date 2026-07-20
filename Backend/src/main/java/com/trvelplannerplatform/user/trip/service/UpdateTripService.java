package com.trvelplannerplatform.user.trip.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.trip.helper.TripHelper;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;
import com.trvelplannerplatform.user.trip.mapper.TripMapper;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateTripService {
	
	private final TripHelper tripHelper;
	
	private final TripRepository tripRepository;
	
	private final TripMapper tripMapper;
	
    public UpdateTripResponse update(Long tripId, UpdateTripRequest request){
    	
    	User user = tripHelper.getLoggedInUser();
		
		Trip trip = tripHelper.getTrip(tripId);
		
		
		tripHelper.validateTripOwner(user, trip);
		
		  tripMapper.mapUpdateRequestToTrip(request, trip);
		    trip = tripRepository.save(trip);

		    log.info("Trip updated successfully. Trip Id: {}", trip.getId());
		    
		    return new UpdateTripResponse(
		            trip.getId(),
		            "Trip updated successfully",
		            trip.getUpdatedAt()
		    );
    	

    }


}
