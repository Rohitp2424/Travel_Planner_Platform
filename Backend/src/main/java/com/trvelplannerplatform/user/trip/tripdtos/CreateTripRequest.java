package com.trvelplannerplatform.user.trip.tripdtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trvelplannerplatform.user.trip.tripEnums.TripVisibility;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTripRequest {

	@NotBlank(message = "TRIP_NAME_REQUIRED")
    @Size(min = 3, max = 100, message = "TRIP_NAME_INVALID_LENGTH")
    private String tripName;

    @Size(max = 500, message = "TRIP_DESCRIPTION_INVALID_LENGTH")
    private String description;

    @NotBlank(message = "SOURCE_REQUIRED")
    @Size(max = 100, message = "SOURCE_INVALID_LENGTH")
    private String source;

    @NotBlank(message = "DESTINATION_REQUIRED")
    @Size(max = 100, message = "DESTINATION_INVALID_LENGTH")
    private String destination;

    @NotNull(message = "START_DATE_REQUIRED")
    @FutureOrPresent(message = "START_DATE_INVALID")
    private LocalDate startDate;

    @NotNull(message = "END_DATE_REQUIRED")
    private LocalDate endDate;

    @NotNull(message = "BUDGET_REQUIRED")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "BUDGET_INVALID"
    )
    private BigDecimal budget;

    @NotNull(message = "VISIBILITY_REQUIRED")
    private TripVisibility visibility;
}
