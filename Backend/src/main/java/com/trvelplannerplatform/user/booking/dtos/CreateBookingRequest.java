package com.trvelplannerplatform.user.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.booking.enums.BookingStatus;
import com.trvelplannerplatform.user.booking.enums.BookingType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookingRequest {

	@NotNull(message = "Trip Id is required")
	private Long tripId;

	@NotNull(message = "Booking type is required")
	private BookingType bookingType;

	@NotBlank(message = "Title is required")
	private String title;

	private String providerName;

	private String bookingReference;

	@NotNull(message = "Booking date is required")
	private LocalDate bookingDate;

	@NotNull(message = "Start date is required")
	private LocalDate startDate;

	private LocalDate endDate;

	@NotNull(message = "Amount is required")
	@DecimalMin(value = "0.0")
	private BigDecimal amount;

	@NotBlank(message = "Currency is required")
	private String currency;

	@NotNull(message = "Booking status is required")
	private BookingStatus status;

	private String notes;

}
