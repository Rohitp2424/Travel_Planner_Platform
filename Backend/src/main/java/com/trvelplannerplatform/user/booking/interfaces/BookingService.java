package com.trvelplannerplatform.user.booking.interfaces;

import com.trvelplannerplatform.user.booking.dtos.CreateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.CreateBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.DeleteBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.GetAllBookingsResponse;
import com.trvelplannerplatform.user.booking.dtos.GetBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingResponse;

public interface BookingService {
	
	public CreateBookingResponse createBooking(CreateBookingRequest request);

	public GetBookingResponse getBooking(Long bookingId);

	public GetAllBookingsResponse getAllBookings(Long tripId);

	public UpdateBookingResponse updateBooking(Long bookingId,
                                        UpdateBookingRequest request);

	public DeleteBookingResponse deleteBooking(Long bookingId);


}
