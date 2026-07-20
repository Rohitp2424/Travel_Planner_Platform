package com.trvelplannerplatform.user.search.interfaces;

import com.trvelplannerplatform.user.search.dtos.SearchTripResponse;

public interface SearchTripService {
	
	 public SearchTripResponse searchTrips(String keyword);

}
