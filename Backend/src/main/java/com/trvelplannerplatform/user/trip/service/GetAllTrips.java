package com.trvelplannerplatform.user.trip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.trip.helper.TripHelper;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;
import com.trvelplannerplatform.user.trip.mapper.TripMapper;
import com.trvelplannerplatform.user.trip.tripdtos.GetAllTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.TripResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllTrips {
	
	private final TripHelper tripHelper;
	
	private final TripRepository tripRepository;
	
	private final TripMapper tripMapper;
	
	public GetAllTripResponse getAllTrips(){
		
		log.info("Fetching all trips.");
		
		User user = tripHelper.getLoggedInUser();
		
		 List<Trip> trips = tripRepository.findByOwner(user);
		    try {

		        trips = tripRepository.findByOwner(user);

		    } catch (Exception ex) {

		        log.error("Unable to fetch trips for user {}", user.getEmail(), ex);

		        throw new UserValidationException(
		        		ErrorCodeEnum.TRIP_FETCH_FAILED.getCode(),
		        		ErrorCodeEnum.TRIP_FETCH_FAILED.getCode(),
		        		HttpStatus.INTERNAL_SERVER_ERROR );
		    }
		    
		    // Convert Entity -> DTO
		    List<TripResponse> tripResponses = new ArrayList<>();

		    for (Trip trip : trips) {

		    	tripResponses.add(tripMapper.toResponse(trip));
		    }

		    // Prepare final response
		    GetAllTripResponse response = new GetAllTripResponse();
		    response.setTotalTrips(tripResponses.size());
		    response.setTrips(tripResponses);

		    log.info("Returning {} trips.", tripResponses.size());
		    
		    return response;

    }


}
