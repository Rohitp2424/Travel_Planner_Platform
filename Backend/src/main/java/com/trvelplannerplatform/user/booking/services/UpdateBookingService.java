package com.trvelplannerplatform.user.booking.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.booking.dtos.UpdateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingResponse;
import com.trvelplannerplatform.user.booking.entity.Booking;
import com.trvelplannerplatform.user.booking.helper.BookingHelper;
import com.trvelplannerplatform.user.booking.interfaces.BookingRepository;
import com.trvelplannerplatform.user.booking.mapper.BookingMapper;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateBookingService {
	
	private final BookingHelper bookingHelper;
	
	private final BookingRepository bookingRepository;
	
	private final BookingMapper bookingMapper;
	
	 public UpdateBookingResponse updateBooking(Long bookingId,
             UpdateBookingRequest request) {
		 
		 log.info("Update Booking Request Received for Booking Id : {}", bookingId);

	        // Fetch Logged-in User
	        User loggedInUser = bookingHelper.getLoggedInUser();
	        
	     // Fetch Booking
	        Booking booking = bookingHelper.getBooking(bookingId);
	        
	        // Fetch Trip
	        Trip trip = booking.getTrip();
	        
	     // Validate Trip Owner
	        bookingHelper.validateTripOwner(loggedInUser, trip);
	        
	        // Validate Booking Reference (only if changed)
	        if (request.getBookingReference() != null
	                && !request.getBookingReference().equals(booking.getBookingReference())) {

	            bookingHelper.validateBookingReference(
	                    trip,
	                    request.getBookingReference());
	        }
	        
	        // Update Fields
	        booking.setBookingType(request.getBookingType());
	        booking.setTitle(request.getTitle());
	        booking.setProviderName(request.getProviderName());
	        booking.setBookingReference(request.getBookingReference());
	        booking.setBookingDate(request.getBookingDate());
	        booking.setStartDate(request.getStartDate());
	        booking.setEndDate(request.getEndDate());
	        booking.setAmount(request.getAmount());
	        booking.setCurrency(request.getCurrency());
	        booking.setStatus(request.getStatus());
	        booking.setNotes(request.getNotes());
	        
	     // Validate Dates
	        bookingHelper.validateBookingDates(booking);
	        
	     // Save Updated Booking
	        booking = bookingRepository.save(booking);
	        
	        log.info("Booking Updated Successfully with Id : {}", bookingId);

	        return bookingMapper.toUpdateResponse(booking);
		 
	 }

}
