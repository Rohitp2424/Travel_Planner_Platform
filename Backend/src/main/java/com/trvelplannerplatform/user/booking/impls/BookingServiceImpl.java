package com.trvelplannerplatform.user.booking.impls;

import org.springframework.stereotype.Service;


import com.trvelplannerplatform.user.booking.dtos.CreateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.CreateBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.DeleteBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.GetAllBookingsResponse;
import com.trvelplannerplatform.user.booking.dtos.GetBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingResponse;
import com.trvelplannerplatform.user.booking.interfaces.BookingService;
import com.trvelplannerplatform.user.booking.services.CreateBookingService;
import com.trvelplannerplatform.user.booking.services.DeleteBookingService;
import com.trvelplannerplatform.user.booking.services.GetAllBookingService;
import com.trvelplannerplatform.user.booking.services.GetBookingService;
import com.trvelplannerplatform.user.booking.services.UpdateBookingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
	
	private final CreateBookingService createBookingService;
	private final GetBookingService getBookingService;
	private final GetAllBookingService getAllBookingService;
	private final UpdateBookingService updateBookingService;
	private final DeleteBookingService deleteBookingService;

	@Override
	public CreateBookingResponse createBooking(CreateBookingRequest request) {
		
		return createBookingService.createBooking(request);
	}

	@Override
	public GetBookingResponse getBooking(Long bookingId) {
		
		return getBookingService.getBooking(bookingId);
	}

	@Override
	public GetAllBookingsResponse getAllBookings(Long tripId) {
		
		return getAllBookingService.getAllBookings(tripId);
	}

	@Override
	public UpdateBookingResponse updateBooking(Long bookingId, UpdateBookingRequest request) {
		
		return updateBookingService.updateBooking(bookingId, request);
	}

	@Override
	public DeleteBookingResponse deleteBooking(Long bookingId) {
		
		return deleteBookingService.deleteBooking(bookingId);
	}

}
