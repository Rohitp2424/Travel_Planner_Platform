package com.trvelplannerplatform.user.booking.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.booking.enums.BookingStatus;
import com.trvelplannerplatform.user.booking.enums.BookingType;

import lombok.Data;

@Data
public class BookingResponse {
	
	private Long bookingId;

    private BookingType bookingType;

    private String title;

    private String providerName;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal amount;

    private String currency;

    private BookingStatus status;

}
