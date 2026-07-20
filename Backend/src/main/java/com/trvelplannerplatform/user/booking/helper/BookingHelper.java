package com.trvelplannerplatform.user.booking.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.booking.entity.Booking;
import com.trvelplannerplatform.user.booking.interfaces.BookingRepository;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;
import com.trvelplannerplatform.user.interfaces.UserRepository;
import com.trvelplannerplatform.user.trip.interfaces.TripRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingHelper {
	
	private final UserRepository userRepository;
	private final TripRepository tripRepository;
	private final BookingRepository bookingRepository;
	
	public User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserValidationException(
                        ErrorCodeEnum.USER_NOT_FOUND.getCode(),
                        ErrorCodeEnum.USER_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND));
    }
	
	/**
     * Fetch Trip
     */
    public Trip getTrip(Long tripId) {

        log.info("Fetching Trip : {}", tripId);

        return tripRepository.findById(tripId)
                .orElseThrow(() -> new UserValidationException(
                        ErrorCodeEnum.TRIP_NOT_FOUND.getCode(),
                        ErrorCodeEnum.TRIP_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND));
    }
    
    /**
     * Fetch Booking
     */
    public Booking getBooking(Long bookingId) {

        log.info("Fetching Booking : {}", bookingId);

        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new UserValidationException(
                        ErrorCodeEnum.BOOKING_NOT_FOUND.getCode(),
                        ErrorCodeEnum.BOOKING_NOT_FOUND.getMessage(),
                        HttpStatus.NOT_FOUND));
    }

    /**
     * Validate Trip Owner
     */
    public void validateTripOwner(User user, Trip trip) {

        if (!trip.getOwner().getId().equals(user.getId())) {

            log.error("Unauthorized access by {}", user.getEmail());

            throw new UserValidationException(
                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getCode(),
                    ErrorCodeEnum.UNAUTHORIZED_ACCESS.getMessage(),
                    HttpStatus.FORBIDDEN);
        }

    }
    
    /**
     * Validate Booking belongs to Trip
     */
    public void validateBookingBelongsToTrip(Booking booking, Trip trip) {

        if (!booking.getTrip().getId().equals(trip.getId())) {

            throw new UserValidationException(
                    ErrorCodeEnum.BOOKING_NOT_FOUND.getCode(),
                    ErrorCodeEnum.BOOKING_NOT_FOUND.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

    }
    
    /**
     * Validate Booking Reference
     */
    public void validateBookingReference(Trip trip,
                                         String bookingReference) {

        if (bookingReference != null
                && !bookingReference.isBlank()
                && bookingRepository.existsByTripAndBookingReference(
                        trip,
                        bookingReference)) {

            throw new UserValidationException(
                    ErrorCodeEnum.BOOKING_REFERENCE_ALREADY_EXISTS.getCode(),
                    ErrorCodeEnum.BOOKING_REFERENCE_ALREADY_EXISTS.getMessage(),
                    HttpStatus.BAD_REQUEST);

        }

    }
    
    /**
     * Validate Dates
     */
    public void validateBookingDates(Booking booking) {

        if (booking.getEndDate() != null &&
                booking.getEndDate().isBefore(booking.getStartDate())) {

            throw new UserValidationException(
                    ErrorCodeEnum.INVALID_BOOKING_DATES.getCode(),
                    ErrorCodeEnum.INVALID_BOOKING_DATES.getMessage(),
                    HttpStatus.BAD_REQUEST);

        }

    }
    
    

}
