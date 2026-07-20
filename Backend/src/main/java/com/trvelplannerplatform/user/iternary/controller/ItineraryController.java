package com.trvelplannerplatform.user.iternary.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.CreateItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.DeleteItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.GetAllItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.ItineraryResponse;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryRequest;
import com.trvelplannerplatform.user.iternary.dtos.UpdateItineraryResponse;
import com.trvelplannerplatform.user.iternary.interfaces.ItineraryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
@Slf4j
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/itinerary")
    public CreateItineraryResponse createItinerary(
            @Valid @RequestBody CreateItineraryRequest request) {

        log.info("Create itinerary request received : {}", request);

        CreateItineraryResponse response = itineraryService.createItinerary(request);

        log.info("Itinerary created successfully : {}", response);

        return response;
    }
   
    @GetMapping("/trip/{tripId}/itineraries")
    public GetAllItineraryResponse getAllItineraries(
            @PathVariable Long tripId) {

        log.info("Fetching all itineraries for Trip {}", tripId);

        GetAllItineraryResponse response =
                itineraryService.getAllItineraries(tripId);

        log.info("Fetched {} itineraries",
                response.getTotalItems());

        return response;
    }
    
    @GetMapping("/itinerary/{itineraryId}")
    public ItineraryResponse getItineraryById(
            @PathVariable Long itineraryId) {

        log.info("Fetching itinerary {}", itineraryId);

        ItineraryResponse response =
                itineraryService.getItineraryById(itineraryId);

        log.info("Fetched itinerary {}", itineraryId);

        return response;
    }

   
    @PutMapping("/itinerary/{itineraryId}")
    public UpdateItineraryResponse updateItinerary(
            @PathVariable Long itineraryId,
            @Valid @RequestBody UpdateItineraryRequest request) {

        log.info("Updating itinerary {}", itineraryId);

        UpdateItineraryResponse response =
                itineraryService.updateItinerary(itineraryId, request);

        log.info("Updated itinerary {}", itineraryId);

        return response;
    }

    @DeleteMapping("/itinerary/{itineraryId}")
    public DeleteItineraryResponse deleteItinerary(
            @PathVariable Long itineraryId) {

        log.info("Delete itinerary request for id {}", itineraryId);

        DeleteItineraryResponse response =
                itineraryService.deleteItinerary(itineraryId);

        log.info("Itinerary deleted successfully : {}", response);

        return response;
    }
  
}