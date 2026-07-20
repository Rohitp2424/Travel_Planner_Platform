package com.trvelplannerplatform.user.trip.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.trip.helper.TripHelper;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;
import com.trvelplannerplatform.user.trip.mapper.TripMapper;
import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;
import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateTripService {
	
	 private final TripRepository tripRepository;
	 private final TripHelper tripHelper;
	 private final TripMapper tripMapper;
	 
	 public CreateTripResponse create(CreateTripRequest request){
		
		 log.info("Create Trip request received.");
			
			Trip trip = new Trip();
			
			tripMapper.mapCreateRequestToTrip(request, trip);
		    
		    User user = tripHelper.getLoggedInUser();
		    
		    trip.setOwner(user);

			log.info("Creating trip '{}' for user '{}'", request.getTripName(), user.getEmail());
			try {

			    trip = tripRepository.save(trip);

			    log.info("Trip created successfully. Trip Id: {}", trip.getId());

			} catch (Exception ex) {

			    log.error("Failed to create trip for user {}", user.getEmail(), ex);

			    throw new UserValidationException(
			    		ErrorCodeEnum.TRIP_CREATION_FAILED.getCode(),
			    		ErrorCodeEnum.TRIP_CREATION_FAILED.getMessage(),
			    		HttpStatus.INTERNAL_SERVER_ERROR);
			}
		    
			CreateTripResponse createResponse = new CreateTripResponse(
			        trip.getId(),
			        trip.getTripName(),
			        trip.getStatus(),
			        "Trip created successfully",
			        trip.getCreatedAt()
			);
			
			log.info("Trip created with : {}", createResponse);
			return createResponse;
		
	 }

	 
	 

}
