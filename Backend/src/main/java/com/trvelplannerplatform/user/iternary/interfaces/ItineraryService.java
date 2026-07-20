package com.trvelplannerplatform.user.iternary.interfaces;

import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.DeleteItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.GetAllItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.ItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryResponse;

public interface ItineraryService {
	
	public CreateItineraryResponse createItinerary(CreateItineraryRequest request);
	
	public GetAllItineraryResponse getAllItineraries(Long tripId);
	
	public ItineraryResponse getItineraryById(Long itineraryId);
	
	public UpdateItineraryResponse updateItinerary(Long itineraryId,
            UpdateItineraryRequest request);
	
	public DeleteItineraryResponse deleteItinerary(Long itineraryId);

}
