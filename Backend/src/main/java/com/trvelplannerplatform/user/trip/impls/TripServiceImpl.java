package com.trvelplannerplatform.user.trip.impls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripService;
import com.trvelplannerplatform.user.trip.service.CreateTripService;
import com.trvelplannerplatform.user.trip.service.DeleteTripService;
import com.trvelplannerplatform.user.trip.service.GetAllTrips;
import com.trvelplannerplatform.user.trip.service.GetTripByIdService;
import com.trvelplannerplatform.user.trip.service.UpdateTripService;
import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;
import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.DeleteTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.GetAllTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.TripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
	
	private final TripRepository tripRepository;
	private final UserRepository userRepository;
	private final CreateTripService createTripService;
	private final GetAllTrips getAllTrips;
	private final GetTripByIdService getTripById;
	private final UpdateTripService updateTripService;
	private final DeleteTripService deleteTripService;

	@Override
	public CreateTripResponse createTrip(CreateTripRequest request) {
		
		CreateTripResponse createResponse = createTripService.create(request);
		
		return createResponse;
	}

	//fetch all the trips
	@Override
	public GetAllTripResponse getAllTrips() {
		
		GetAllTripResponse allTrips = getAllTrips.getAllTrips();
		
		return allTrips;
	}

	@Override
	public TripResponse getTripById(Long tripId) {
		
		TripResponse response = getTripById.getTripById(tripId);
		
		return response;
		
		
	}

	@Override
	public UpdateTripResponse updateTrip(Long tripId, UpdateTripRequest request) {

		UpdateTripResponse updatedTrip = updateTripService.update(tripId, request);
		
	    return updatedTrip;
	}

	@Override
	public DeleteTripResponse deleteTrip(Long tripId) {
		
		DeleteTripResponse response = deleteTripService.delete(tripId);
		return response;
		
		
	}


}
