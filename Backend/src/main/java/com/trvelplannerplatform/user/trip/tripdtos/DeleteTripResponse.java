package com.trvelplannerplatform.user.trip.tripdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTripResponse {

    private Long tripId;

    private String message;

}
