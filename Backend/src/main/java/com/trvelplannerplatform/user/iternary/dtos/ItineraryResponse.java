package com.trvelplannerplatform.user.iternary.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ItineraryResponse {

    private Long itineraryId;
    
    private Long tripId;

    private Integer dayNumber;

    private String title;

    private String description;

    private String location;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal estimatedCost;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

	
}