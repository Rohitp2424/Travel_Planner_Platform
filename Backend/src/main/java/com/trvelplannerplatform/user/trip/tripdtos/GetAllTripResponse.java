package com.trvelplannerplatform.user.trip.tripdtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTripResponse {

    private int totalTrips;

    private List<TripResponse> trips;

}
