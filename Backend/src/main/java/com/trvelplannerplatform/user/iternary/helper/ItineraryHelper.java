package com.trvelplannerplatform.user.iternary.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.entity.Itinerary;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.iternary.interfaces.IternaryRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItineraryHelper {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final IternaryRepository itineraryRepository;
    
    public User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        log.info("Authenticated user : {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> {

                    log.error("User not found with email {}", email);

                    return new UserValidationException(
                            ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                            ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);
                });
    }
    
    public Trip getTrip(Long tripId) {

        log.info("Fetching trip {}", tripId);

        return tripRepository.findById(tripId)
                .orElseThrow(() -> {

                    log.error("Trip {} not found", tripId);

                    return new UserValidationException(
                            ErrorCodeEnum.TRIP_NOT_FOUND.getCode(),
                            ErrorCodeEnum.TRIP_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);
                });
    }
    
    public void validateTripOwner(User user, Trip trip) {

        if (!trip.getOwner().getId().equals(user.getId())) {

            log.error("User {} is not owner of trip {}",
                    user.getId(),
                    trip.getId());

            throw new UserValidationException(
                    ErrorCodeEnum.UNAUTHORIZED_TRIP_ACCESS.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_TRIP_ACCESS.getMessage(),
                    HttpStatus.FORBIDDEN);
        }
        
    }
    
    public Itinerary getItinerary(Long itineraryId) {

        log.info("Fetching itinerary {}", itineraryId);

        return itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> {

                    log.error("Itinerary {} not found", itineraryId);

                    return new UserValidationException(
                            ErrorCodeEnum.ITINERARY_NOT_FOUND.getCode(),
                            ErrorCodeEnum.ITINERARY_NOT_FOUND.getMessage(),
                            HttpStatus.NOT_FOUND);
                });
    }
    
    public void validateTripItinerary(Trip trip, Itinerary itinerary) {

        if (!itinerary.getTrip().getId().equals(trip.getId())) {

            log.error("Itinerary {} does not belong to trip {}",
                    itinerary.getId(),
                    trip.getId());

            throw new UserValidationException(
                    ErrorCodeEnum.INVALID_ITINERARY.getCode(),
                    ErrorCodeEnum.INVALID_ITINERARY.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}