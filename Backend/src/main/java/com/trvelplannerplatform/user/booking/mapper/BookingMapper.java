package com.trvelplannerplatform.user.booking.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.booking.dtos.BookingResponse;
import com.trvelplannerplatform.user.booking.dtos.CreateBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.GetBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingResponse;
import com.trvelplannerplatform.user.booking.entity.Booking;

@Component
public class BookingMapper {
	
	 /**
     * Create Booking Response
     */
    public CreateBookingResponse toCreateResponse(Booking booking) {

        CreateBookingResponse response = new CreateBookingResponse();

        response.setBookingId(booking.getId());
        response.setTripId(booking.getTrip().getId());
        response.setBookingType(booking.getBookingType());
        response.setTitle(booking.getTitle());
        response.setProviderName(booking.getProviderName());
        response.setBookingReference(booking.getBookingReference());
        response.setBookingDate(booking.getBookingDate());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setAmount(booking.getAmount());
        response.setCurrency(booking.getCurrency());
        response.setStatus(booking.getStatus());
        response.setNotes(booking.getNotes());
        response.setCreatedAt(booking.getCreatedAt());
        response.setMessage("Booking created successfully");

        return response;
    }
    
    /**
     * Get Booking Response
     */
    public GetBookingResponse toGetResponse(Booking booking) {

        GetBookingResponse response = new GetBookingResponse();

        response.setBookingId(booking.getId());
        response.setTripId(booking.getTrip().getId());
        response.setBookingType(booking.getBookingType());
        response.setTitle(booking.getTitle());
        response.setProviderName(booking.getProviderName());
        response.setBookingReference(booking.getBookingReference());
        response.setBookingDate(booking.getBookingDate());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setAmount(booking.getAmount());
        response.setCurrency(booking.getCurrency());
        response.setStatus(booking.getStatus());
        response.setNotes(booking.getNotes());
        response.setCreatedAt(booking.getCreatedAt());
        response.setUpdatedAt(booking.getUpdatedAt());

        return response;
    }
    
    /**
     * Booking Response
     */
    public BookingResponse toResponse(Booking booking) {

        BookingResponse response = new BookingResponse();

        response.setBookingId(booking.getId());
        response.setBookingType(booking.getBookingType());
        response.setTitle(booking.getTitle());
        response.setProviderName(booking.getProviderName());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setAmount(booking.getAmount());
        response.setCurrency(booking.getCurrency());
        response.setStatus(booking.getStatus());

        return response;
    }

    /**
     * Booking List Response
     */
    public List<BookingResponse> toResponseList(List<Booking> bookings) {

        return bookings.stream()
                .map(this::toResponse)
                .toList();
    }
    
    /**
     * Update Booking Response
     */
    public UpdateBookingResponse toUpdateResponse(Booking booking) {

        UpdateBookingResponse response = new UpdateBookingResponse();

        response.setBookingId(booking.getId());
        response.setTripId(booking.getTrip().getId());
        response.setBookingType(booking.getBookingType());
        response.setTitle(booking.getTitle());
        response.setProviderName(booking.getProviderName());
        response.setBookingReference(booking.getBookingReference());
        response.setBookingDate(booking.getBookingDate());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setAmount(booking.getAmount());
        response.setCurrency(booking.getCurrency());
        response.setStatus(booking.getStatus());
        response.setNotes(booking.getNotes());
        response.setUpdatedAt(booking.getUpdatedAt());
        response.setMessage("Booking updated successfully");

        return response;
    }

}
