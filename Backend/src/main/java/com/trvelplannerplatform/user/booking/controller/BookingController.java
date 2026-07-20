package com.trvelplannerplatform.user.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.booking.dtos.CreateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.CreateBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.DeleteBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.GetAllBookingsResponse;
import com.trvelplannerplatform.user.booking.dtos.GetBookingResponse;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingRequest;
import com.trvelplannerplatform.user.booking.dtos.UpdateBookingResponse;
import com.trvelplannerplatform.user.booking.interfaces.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class BookingController {
	
	private final BookingService bookingService;
	
	@PostMapping("/bookings")
	public ResponseEntity<CreateBookingResponse> createBooking(
	        @Valid @RequestBody CreateBookingRequest request) {
		System.out.println("CONTROLLER HIT");

	    log.info("Create Booking Controller Hit");

	    CreateBookingResponse response = bookingService.createBooking(request);

	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body(response);
	}
	
	@GetMapping("/bookings/{bookingId}")
	public ResponseEntity<GetBookingResponse> getBooking(
	        @PathVariable Long bookingId) {

	    log.info("Get Booking Controller Hit");

	    GetBookingResponse response =
	            bookingService.getBooking(bookingId);

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/trips/{tripId}/bookings")
	public ResponseEntity<GetAllBookingsResponse> getAllBookings(
	        @PathVariable Long tripId) {

	    log.info("Get All Bookings Controller Hit");

	    GetAllBookingsResponse response =
	            bookingService.getAllBookings(tripId);

	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/bookings/{bookingId}")
	public ResponseEntity<UpdateBookingResponse> updateBooking(
	        @PathVariable Long bookingId,
	        @Valid @RequestBody UpdateBookingRequest request) {

	    log.info("Update Booking Controller Hit");

	    UpdateBookingResponse response =
	            bookingService.updateBooking(bookingId, request);

	    return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/bookings/{bookingId}")
	public ResponseEntity<DeleteBookingResponse> deleteBooking(
	        @PathVariable Long bookingId) {

	    log.info("Delete Booking Controller Hit");

	    DeleteBookingResponse response =
	            bookingService.deleteBooking(bookingId);

	    return ResponseEntity.ok(response);
	}

}
