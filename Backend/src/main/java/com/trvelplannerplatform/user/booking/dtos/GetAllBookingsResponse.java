package com.trvelplannerplatform.user.booking.dtos;

import java.util.List;

import lombok.Data;

@Data
public class GetAllBookingsResponse {
	
	private Integer totalBookings;

    private List<BookingResponse> bookings;
    
    private Long tripId;

}
