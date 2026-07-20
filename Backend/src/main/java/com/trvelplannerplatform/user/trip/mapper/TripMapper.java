package com.trvelplannerplatform.user.trip.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.search.dtos.SearchTripItemResponse;
import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;
import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.TripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripRequest;

@Component
public class TripMapper {
	
	public void mapCreateRequestToTrip(CreateTripRequest request, Trip trip) {


        trip.setTripName(request.getTripName());
        trip.setDescription(request.getDescription());
        trip.setSource(request.getSource());
        trip.setDestination(request.getDestination());
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());
        trip.setBudget(request.getBudget());

        trip.setStatus(TripStatus.PLANNED);
        trip.setVisibility(TripVisibility.PRIVATE);

        trip.setCreatedAt(LocalDateTime.now());
        trip.setUpdatedAt(LocalDateTime.now());
    }

    public void mapUpdateRequestToTrip(UpdateTripRequest request, Trip trip) {

        trip.setTripName(request.getTripName());
        trip.setDescription(request.getDescription());
        trip.setSource(request.getSource());
        trip.setDestination(request.getDestination());
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());
        trip.setBudget(request.getBudget());
    }

	
	public TripResponse toResponse(Trip trip){

        TripResponse response = new TripResponse();

        response.setTripId(trip.getId());
        response.setTripName(trip.getTripName());
        response.setDescription(trip.getDescription());
        response.setSourceCity(trip.getSource());
        response.setDestinationCity(trip.getDestination());
        response.setStartDate(trip.getStartDate());
        response.setEndDate(trip.getEndDate());
        response.setBudget(trip.getBudget());
        response.setStatus(trip.getStatus());
        response.setVisibility(trip.getVisibility());
        response.setCreatedAt(trip.getCreatedAt());
        response.setUpdatedAt(trip.getUpdatedAt());

        return response;
    }
	
	 public SearchTripItemResponse toSearchResponse(Trip trip) {

	        SearchTripItemResponse response = new SearchTripItemResponse();

	        response.setTripId(trip.getId());
	        response.setTripName(trip.getTripName());
	        response.setDescription(trip.getDescription());
	        response.setSourceCity(trip.getSource());
	        response.setDestinationCity(trip.getDestination());
	        response.setStartDate(trip.getStartDate());
	        response.setEndDate(trip.getEndDate());
	        response.setBudget(trip.getBudget());
	        response.setStatus(trip.getStatus());
	        response.setVisibility(trip.getVisibility());

	        return response;
	    }

}
