package com.trvelplannerplatform.user.iternary.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.iternary.dtos.DeleteItineraryResponse;
import com.trvelplannerplatform.user.iternary.helper.ItineraryHelper;
import com.trvelplannerplatform.user.iternary.interfaces.IternaryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteIterneraryService {
	
	 private final IternaryRepository itineraryRepository;
	 private final ItineraryHelper itineraryHelper;
	 
	 public DeleteItineraryResponse delete(Long itineraryId) {

	        log.info("Delete itinerary request received for itinerary id {}", itineraryId);

	        // Get logged-in user
	        User user = itineraryHelper.getLoggedInUser();

	        // Fetch itinerary
	        Itinerary itinerary = itineraryHelper.getItinerary(itineraryId);

	        // Get associated trip
	        Trip trip = itinerary.getTrip();

	        // Validate ownership
	        itineraryHelper.validateTripOwner(user, trip);

	        try {

	            itineraryRepository.delete(itinerary);

	            log.info("Itinerary {} deleted successfully.", itineraryId);

	        } catch (Exception ex) {

	            log.error("Failed to delete itinerary {}", itineraryId, ex);

	            throw new UserValidationException(
	                    ErrorCodeEnum.ITINERARY_DELETE_FAILED.getCode(),
	                    ErrorCodeEnum.ITINERARY_DELETE_FAILED.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        
	        DeleteItineraryResponse response = new DeleteItineraryResponse();

	        response.setItineraryId(itineraryId);
	        response.setTripId(trip.getId());
	        response.setMessage("Itinerary deleted successfully");

	        log.info("Returning response : {}", response);

	        return response;
	    }


}
