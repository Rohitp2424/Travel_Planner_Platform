package com.trvelplannerplatform.user.iternary.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryResponse;
import com.trvelplannerplatform.user.iternary.helper.ItineraryHelper;
import com.trvelplannerplatform.user.iternary.interfaces.IternaryRepository;
import com.trvelplannerplatform.user.iternary.mapper.IternaryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateItineraryService {

	 private final IternaryRepository itineraryRepository;
	 private final ItineraryHelper itineraryHelper;
	 private final IternaryMapper itineraryMapper;
	 

	    public CreateItineraryResponse create(CreateItineraryRequest request) {

	        log.info("Create itinerary request received for trip {}", request.getTripId());

	        // Logged-in user
	        User user = itineraryHelper.getLoggedInUser();

	        // Fetch trip
	        Trip trip = itineraryHelper.getTrip(request.getTripId());

	        // Validate ownership
	        itineraryHelper.validateTripOwner(user, trip);

	        // Create entity
	        Itinerary itinerary = new Itinerary();

	        // Map request -> entity
	        itineraryMapper.mapCreateRequestToItinerary(request, itinerary);

	        // Set relationship
	        itinerary.setTrip(trip);

	        try {

	            itinerary = itineraryRepository.save(itinerary);

	            log.info("Itinerary {} created successfully for Trip {}",
	                    itinerary.getId(),
	                    trip.getId());

	        } catch (Exception ex) {

	            log.error("Failed to create itinerary for Trip {}",
	                    trip.getId(),
	                    ex);

	            throw new UserValidationException(
	                    ErrorCodeEnum.ITINERARY_CREATION_FAILED.getCode(),
	                    ErrorCodeEnum.ITINERARY_CREATION_FAILED.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        CreateItineraryResponse response = new CreateItineraryResponse(
	                itinerary.getId(),
	                trip.getId(),
	                itinerary.getTitle(),
	                "Itinerary created successfully",
	                itinerary.getCreatedAt()
	        );

	        return response;
	    }

}
