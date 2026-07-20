package com.trvelplannerplatform.user.iternary.dtos;

import java.util.List;

import lombok.Data;

@Data
public class GetAllItineraryResponse {

    private Integer totalItems;

    private List<ItineraryResponse> itineraries;

}