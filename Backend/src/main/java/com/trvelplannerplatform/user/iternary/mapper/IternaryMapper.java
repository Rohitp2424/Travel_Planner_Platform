package com.trvelplannerplatform.user.iternary.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.ItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.SearchItineraryItemResponse;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryRequest;

@Component
public class IternaryMapper {
	
	 public void mapCreateRequestToItinerary(CreateItineraryRequest request,
             Itinerary itinerary) {

		itinerary.setDayNumber(request.getDayNumber());
		itinerary.setTitle(request.getTitle());
		itinerary.setDescription(request.getDescription());
		itinerary.setLocation(request.getLocation());
		itinerary.setStartTime(request.getStartTime());
		itinerary.setEndTime(request.getEndTime());
		itinerary.setEstimatedCost(request.getEstimatedCost());
		
		itinerary.setCreatedAt(LocalDateTime.now());
		itinerary.setUpdatedAt(LocalDateTime.now());
	}
	 
	 public void mapUpdateRequestToItinerary(UpdateItineraryRequest request,
             Itinerary itinerary) {

		itinerary.setDayNumber(request.getDayNumber());
		itinerary.setTitle(request.getTitle());
		itinerary.setDescription(request.getDescription());
		itinerary.setLocation(request.getLocation());
		itinerary.setStartTime(request.getStartTime());
		itinerary.setEndTime(request.getEndTime());
		itinerary.setEstimatedCost(request.getEstimatedCost());
		
		itinerary.setUpdatedAt(LocalDateTime.now());
	}

	 public ItineraryResponse toResponse(Itinerary itinerary) {

	    ItineraryResponse response = new ItineraryResponse();

	        response.setItineraryId(itinerary.getId());
	        response.setTripId(itinerary.getTrip().getId());
	        response.setDayNumber(itinerary.getDayNumber());
	        response.setTitle(itinerary.getTitle());
	        response.setDescription(itinerary.getDescription());
	        response.setLocation(itinerary.getLocation());
	        response.setStartTime(itinerary.getStartTime());
	        response.setEndTime(itinerary.getEndTime());
	        response.setEstimatedCost(itinerary.getEstimatedCost());
	        response.setCreatedAt(itinerary.getCreatedAt());
	        response.setUpdatedAt(itinerary.getUpdatedAt());

	        return response;
	    }
	 
	
}
