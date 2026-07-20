package com.trvelplannerplatform.user.trip.tripdtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;
import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
