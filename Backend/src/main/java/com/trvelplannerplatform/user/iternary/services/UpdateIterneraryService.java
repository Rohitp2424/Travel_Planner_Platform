package com.trvelplannerplatform.user.iternary.services;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryResponse;
import com.trvelplannerplatform.user.iternary.helper.ItineraryHelper;
import com.trvelplannerplatform.user.iternary.interfaces.IternaryRepository;
import com.trvelplannerplatform.user.iternary.mapper.IternaryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateIterneraryService {

	private final IternaryRepository itineraryRepository;
    private final ItineraryHelper itineraryHelper;
    private final IternaryMapper itineraryMapper;
    
    public UpdateItineraryResponse updateItinerary(
            Long itineraryId,
            UpdateItineraryRequest request) {

        log.info("Updating itinerary with id {}", itineraryId);

        // Logged-in user
        User user = itineraryHelper.getLoggedInUser();

        // Fetch itinerary
        Itinerary itinerary = itineraryHelper.getItinerary(itineraryId);

        // Get associated trip
        Trip trip = itinerary.getTrip();

        // Validate ownership
        itineraryHelper.validateTripOwner(user, trip);

        // Update entity fields
        itineraryMapper.mapUpdateRequestToItinerary(request, itinerary);

        // Update timestamp
        itinerary.setUpdatedAt(LocalDateTime.now());

        try {

            itinerary = itineraryRepository.save(itinerary);

            log.info("Itinerary {} updated successfully", itineraryId);

        } catch (Exception ex) {

            log.error("Failed to update itinerary {}", itineraryId, ex);

            throw new UserValidationException(
                    ErrorCodeEnum.ITINERARY_UPDATE_FAILED.getCode(),
                    ErrorCodeEnum.ITINERARY_UPDATE_FAILED.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UpdateItineraryResponse response = new UpdateItineraryResponse();

        response.setItineraryId(itinerary.getId());
        response.setTripId(trip.getId());
        response.setTitle(itinerary.getTitle());
        response.setMessage("Itinerary updated successfully");
        response.setUpdatedAt(itinerary.getUpdatedAt());

        log.info("Returning UpdateItineraryResponse : {}", response);

        return response;
    }

}
