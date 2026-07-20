package com.trvelplannerplatform.user.search.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;
import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;

import lombok.Data;

@Data
public class SearchTripItemResponse {

	 private Long tripId;

	    private String tripName;

	    private String description;

	    private String sourceCity;

	    private String destinationCity;

	    private LocalDate startDate;

	    private LocalDate endDate;

	    private BigDecimal budget;

	    private TripStatus status;

	    private TripVisibility visibility;
}
