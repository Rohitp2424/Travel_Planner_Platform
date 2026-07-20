package com.trvelplannerplatform.user.iternary.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteItineraryResponse {

    private Long itineraryId;

    private Long tripId;

    private String message;

	

}
