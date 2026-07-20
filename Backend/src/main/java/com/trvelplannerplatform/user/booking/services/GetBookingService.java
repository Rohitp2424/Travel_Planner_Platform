package com.trvelplannerplatform.user.booking.services;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.booking.dtos.GetBookingResponse;
import com.trvelplannerplatform.user.booking.entity.Booking;
import com.trvelplannerplatform.user.booking.helper.BookingHelper;
import com.trvelplannerplatform.user.booking.mapper.BookingMapper;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetBookingService {

	private final BookingHelper bookingHelper;

	private final BookingMapper bookingMapper;

	public GetBookingResponse getBooking(Long bookingId) {

		log.info("Get Booking Request : {}", bookingId);

		// Fetch logged-in user
		User loggedInUser = bookingHelper.getLoggedInUser();

		// Fetch booking
		Booking booking = bookingHelper.getBooking(bookingId);

		// Fetch trip
		Trip trip = booking.getTrip();

		// Validate trip owner
		bookingHelper.validateTripOwner(loggedInUser, trip);

		// Validate booking belongs to trip
		bookingHelper.validateBookingBelongsToTrip(booking, trip);
		log.info("Booking fetched successfully : {}", bookingId);

		return bookingMapper.toGetResponse(booking);
	}

}
