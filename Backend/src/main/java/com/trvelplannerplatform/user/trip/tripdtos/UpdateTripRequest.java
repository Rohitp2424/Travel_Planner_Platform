package com.trvelplannerplatform.user.trip.tripdtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTripRequest {

    private String tripName;

    private String description;

    private String source;

    private String destination;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal budget;

    private TripVisibility visibility;

}
