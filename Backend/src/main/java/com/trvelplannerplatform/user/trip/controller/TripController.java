package com.trvelplannerplatform.user.trip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.trip.interfaces.TripService;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.CreateTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.DeleteTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.GetAllTripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.TripResponse;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripRequest;
import com.trvelplannerplatform.user.trip.tripdtos.UpdateTripResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class TripController {
	
	private final TripService tripService;
	
	@PostMapping("/v1/trip")
	public CreateTripResponse createTrip(@Valid @RequestBody CreateTripRequest request) {
		
		CreateTripResponse response = tripService.createTrip(request);
		
		log.info("creating the trip request: {}", request);

		return response;
		
	}
	
	@GetMapping("v1/trips")
	public GetAllTripResponse getAllTrips() {

		log.info("fetching all the trips");
		GetAllTripResponse response =  tripService.getAllTrips();
		
		log.info("the fetched all the trip: {}", response);
	    return response;

	}
	
	@GetMapping("/v1/trips/{tripId}")
	public TripResponse getTripById(@PathVariable Long tripId) {

	    return tripService.getTripById(tripId);
	}
	
	@PutMapping("/v1/trips/{tripId}")
	public ResponseEntity<UpdateTripResponse> updateTrip(
	        @PathVariable Long tripId,
	        @RequestBody @Valid UpdateTripRequest request){

	    UpdateTripResponse response =
	            tripService.updateTrip(tripId, request);

	    return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/v1/trips/{tripId}")
	public Object deleteTrip(@PathVariable Long tripId) {

	    log.info("Delete Trip Request for id {}", tripId);

	    return tripService.deleteTrip(tripId);
	}
	
}