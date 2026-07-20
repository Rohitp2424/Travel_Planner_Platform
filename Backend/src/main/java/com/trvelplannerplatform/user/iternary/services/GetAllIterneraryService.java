package com.trvelplannerplatform.user.iternary.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.iternary.dtos.GetAllItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.ItineraryResponse;
import com.trvelplannerplatform.user.iternary.helper.ItineraryHelper;
import com.trvelplannerplatform.user.iternary.interfaces.IternaryRepository;
import com.trvelplannerplatform.user.iternary.mapper.IternaryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllIterneraryService {

    private final IternaryRepository itineraryRepository;
    private final ItineraryHelper itineraryHelper;
    private final IternaryMapper itineraryMapper;

    public GetAllItineraryResponse getAll(Long tripId) {

        log.info("Fetching all itineraries for Trip {}", tripId);

        // Logged-in user
        User user = itineraryHelper.getLoggedInUser();

        // Fetch Trip
        Trip trip = itineraryHelper.getTrip(tripId);

        // Validate ownership
        itineraryHelper.validateTripOwner(user, trip);

        // Fetch itineraries
        List<Itinerary> itineraries = itineraryRepository.findByTrip(trip);

        log.info("Found {} itineraries", itineraries.size());

        // Entity -> DTO
        List<ItineraryResponse> responses =
                itineraries.stream()
                        .map(itineraryMapper::toResponse)
                        .collect(Collectors.toList());

        // Final Response
        GetAllItineraryResponse response = new GetAllItineraryResponse();

        response.setTotalItems(responses.size());
        response.setItineraries(responses);

        log.info("Returning {} itineraries", responses.size());

        return response;
    }
}