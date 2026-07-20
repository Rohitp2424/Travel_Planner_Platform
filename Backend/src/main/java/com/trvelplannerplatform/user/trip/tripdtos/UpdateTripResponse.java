package com.trvelplannerplatform.user.trip.tripdtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTripResponse {

    private Long tripId;

    private String message;

    private LocalDateTime updatedAt;

}