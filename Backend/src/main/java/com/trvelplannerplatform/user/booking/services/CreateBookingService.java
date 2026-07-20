package com.trvelplannerplatform.user.booking.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.booking.dtos.CreateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.CreateBookingResponse;
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
public class CreateBookingService {
	
	private final BookingHelper bookingHelper;
	
	private final BookingRepository bookingRepository;
	
	private final BookingMapper bookingMapper;
	
	public CreateBookingResponse createBooking(CreateBookingRequest request){
		
		log.info("Create Booking Request Received : {}", request);

        // Fetch logged-in user
        User loggedInUser = bookingHelper.getLoggedInUser();
        
        // Fetch trip
        Trip trip = bookingHelper.getTrip(request.getTripId());
        
     // Validate owner
        bookingHelper.validateTripOwner(loggedInUser, trip);
        
        // Validate booking reference
        bookingHelper.validateBookingReference(
                trip,
                request.getBookingReference());
        
        // Create Booking Entity
        Booking booking = new Booking();

        booking.setTrip(trip);
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
        
     // Validate booking dates
        bookingHelper.validateBookingDates(booking);
        
     // Save booking
        booking = bookingRepository.save(booking);
        
        log.info("Booking Created Successfully with Id : {}", booking.getId());

        return bookingMapper.toCreateResponse(booking);

	}

}
