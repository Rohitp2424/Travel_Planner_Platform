package com.trvelplannerplatform.user.booking.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.booking.dtos.DeleteBookingResponse;
import com.trvelplannerplatform.user.booking.entity.Booking;
import com.trvelplannerplatform.user.booking.helper.BookingHelper;
import com.trvelplannerplatform.user.booking.interfaces.BookingRepository;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteBookingService {
	
	private final BookingHelper bookingHelper;
	private final BookingRepository bookingRepository;
	
	public DeleteBookingResponse deleteBooking(Long bookingId) {
		
		 log.info("Delete Booking Request Received for Booking Id : {}", bookingId);

	        // Fetch Logged-in User
	        User loggedInUser = bookingHelper.getLoggedInUser();
	        

	        // Fetch Booking
	        Booking booking = bookingHelper.getBooking(bookingId);

	        // Fetch Trip
	        Trip trip = booking.getTrip();

	        // Validate Trip Owner
	        bookingHelper.validateTripOwner(loggedInUser, trip);
	        
	        // Delete Booking
	        bookingRepository.delete(booking);

	        log.info("Booking Deleted Successfully with Id : {}", bookingId);
	        
	        DeleteBookingResponse response = new DeleteBookingResponse();
	        response.setBookingId(bookingId);
	        response.setMessage("Booking deleted successfully.");

	        return response;
	}

}
