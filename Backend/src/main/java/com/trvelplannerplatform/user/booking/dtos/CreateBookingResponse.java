package com.trvelplannerplatform.user.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.trvelplannerplatform.user.booking.enums.BookingStatus;
import com.trvelplannerplatform.user.booking.enums.BookingType;

import lombok.Data;

@Data
public class CreateBookingResponse {
	
	private Long bookingId;

    private Long tripId;

    private BookingType bookingType;

    private String title;

    private String providerName;

    private String bookingReference;

    private LocalDate bookingDate;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amount;

    private String currency;

    private BookingStatus status;

    private String notes;

    private LocalDateTime createdAt;

    private String message;

}
