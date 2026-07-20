package com.trvelplannerplatform.user.trip.interfaces;

import java.util.List;

import com.trvelplannerplatform.user.trip.tripdtos.CreateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.GetAllTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.TripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripResponse;

import jakarta.validation.Valid;

public interface TripService {
	
	public CreateTripResponse createTrip(CreateTripRequest request);

	public GetAllTripResponse getAllTrips();

	public TripResponse getTripById(Long tripId);

	public UpdateTripResponse updateTrip(Long tripId, UpdateTripRequest request);

	public Object deleteTrip(Long tripId);

}
