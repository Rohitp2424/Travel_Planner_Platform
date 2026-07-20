package com.trvelplannerplatform.user.booking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.booking.dtos.BookingResponse;
import com.trvelplannerplatform.user.booking.dtos.GetAllBookingsResponse;
import com.trvelplannerplatform.user.booking.entity.Booking;
import com.trvelplannerplatform.user.booking.helper.BookingHelper;
import com.trvelplannerplatform.user.booking.interfaces.BookingRepository;
import com.trvelplannerplatform.user.booking.mapper.BookingMapper;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllBookingService {
	
	private final BookingHelper bookingHelper;
	
	private final BookingMapper bookingMapper;
	
	private final BookingRepository bookingRepository;
	
	public GetAllBookingsResponse getAllBookings(Long tripId) {
		
		log.info("Get All Bookings Request for Trip Id : {}", tripId);

        // Fetch Logged-in User
        User loggedInUser = bookingHelper.getLoggedInUser();
        
     // Fetch Trip
        Trip trip = bookingHelper.getTrip(tripId);
        
        // Validate Trip Owner
        bookingHelper.validateTripOwner(loggedInUser, trip);

     // Fetch Bookings
        List<Booking> bookings = bookingRepository.findByTrip(trip);
        
        // Map Response
        List<BookingResponse> bookingResponses =
                bookingMapper.toResponseList(bookings);
        
     // Prepare Response
        GetAllBookingsResponse response = new GetAllBookingsResponse();
        
        response.setTripId(trip.getId());
        response.setTotalBookings(bookings.size());
        response.setBookings(bookingResponses);

        log.info("Fetched {} bookings for Trip Id : {}",
                bookings.size(), tripId);

        return response;
	}

}
