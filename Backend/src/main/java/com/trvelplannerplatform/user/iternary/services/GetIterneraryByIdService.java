package com.trvelplannerplatform.user.iternary.services;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.iternary.dtos.ItineraryResponse;
import com.trvelplannerplatform.user.iternary.helper.ItineraryHelper;
import com.trvelplannerplatform.user.iternary.interfaces.IternaryRepository;
import com.trvelplannerplatform.user.iternary.mapper.IternaryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetIterneraryByIdService {

    private final IternaryRepository itineraryRepository;
    private final ItineraryHelper itineraryHelper;
    private final IternaryMapper itineraryMapper;

    public ItineraryResponse getById(Long itineraryId) {

        log.info("Fetching itinerary with id {}", itineraryId);

        // Logged-in user
        User user = itineraryHelper.getLoggedInUser();

        // Fetch itinerary
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> {

                    log.error("Itinerary not found with id {}", itineraryId);

                    return new UserValidationException(
                            ErrorCodeEnum.ITINERARY_NOT_FOUND.getCode(),
                            ErrorCodeEnum.ITINERARY_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);
                });

        log.info("Itinerary found with id {}", itineraryId);

        // Get associated trip
        Trip trip = itinerary.getTrip();

        // Validate ownership
        itineraryHelper.validateTripOwner(user, trip);

        // Entity -> DTO
        ItineraryResponse response = itineraryMapper.toResponse(itinerary);

        log.info("Returning itinerary {}", response);

        return response;
    }
}
