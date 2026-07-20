package com.trvelplannerplatform.user.iternary.impls;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.DeleteItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.GetAllItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.ItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryResponse;
import com.trvelplannerplatform.user.iternary.interfaces.ItineraryService;
import com.trvelplannerplatform.user.iternary.services.CreateItineraryService;
import com.trvelplannerplatform.user.iternary.services.DeleteIterneraryService;
import com.trvelplannerplatform.user.iternary.services.GetAllIterneraryService;
import com.trvelplannerplatform.user.iternary.services.GetIterneraryByIdService;
import com.trvelplannerplatform.user.iternary.services.UpdateIterneraryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IternaryServiceImpl implements ItineraryService {

	private final CreateItineraryService createIternaryService;
	private final GetAllIterneraryService getAllIternaryService;
	private final GetIterneraryByIdService getItineraryByIdService;
	private final UpdateIterneraryService updateIternerayService;
	private final DeleteIterneraryService deleteIterneraryService;
	
	@Override
	public CreateItineraryResponse createItinerary(CreateItineraryRequest request) {
		
		return createIternaryService.create(request);
	}

	@Override
	public GetAllItineraryResponse getAllItineraries(Long tripId) {
		
		return getAllIternaryService.getAll(tripId);
	}

	@Override
	public ItineraryResponse getItineraryById(Long itineraryId) {
		
		return getItineraryByIdService.getById(itineraryId);
	}

	@Override
	public UpdateItineraryResponse updateItinerary(Long itineraryId, UpdateItineraryRequest request) {
		
		return updateIternerayService.updateItinerary(itineraryId, request);
	}

	@Override
	public DeleteItineraryResponse deleteItinerary(Long itineraryId) {
	    return deleteIterneraryService.delete(itineraryId);
	}

}
