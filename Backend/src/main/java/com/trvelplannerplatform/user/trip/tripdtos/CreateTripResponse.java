package com.trvelplannerplatform.user.trip.tripdtos;

import java.time.LocalDateTime;

import com.trvelplannerplatform.user.trip.tripEnums.TripStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTripResponse {

    private Long tripId;

    private String tripName;

    private TripStatus status;

    private String message;

    private LocalDateTime createdAt;
    
    

}
