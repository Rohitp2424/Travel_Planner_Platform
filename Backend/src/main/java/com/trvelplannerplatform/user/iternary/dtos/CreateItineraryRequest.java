package com.trvelplannerplatform.user.iternary.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateItineraryRequest {

    @NotNull(message = "Trip Id is required")
    private Long tripId;

    @NotNull(message = "Day number is required")
    private Integer dayNumber;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private String location;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal estimatedCost;

}
