package com.trvelplannerplatform.user.search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.search.dtos.SearchTripResponse;
import com.trvelplannerplatform.user.search.interfaces.SearchTripService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class SearchController {
	
	private final SearchTripService searchTripService;
	
	@GetMapping("/trips/search")
	 public SearchTripResponse searchTrips(
	            @RequestParam String keyword) {

	        return searchTripService.searchTrips(keyword);

	    }
}
