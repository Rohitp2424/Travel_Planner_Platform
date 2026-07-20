package com.trvelplannerplatform.user.iternary.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItineraryResponse {

    private Long itineraryId;
    
    private Long tripId;

    private String title;

    private String message;

    private LocalDateTime updatedAt;


}
