package com.trvelplannerplatform.user.search.dtos;

import java.util.List;

import lombok.Data;

@Data
public class SearchTripResponse {
	
	private int totalTrips;

    private List<SearchTripItemResponse> trips;

}
