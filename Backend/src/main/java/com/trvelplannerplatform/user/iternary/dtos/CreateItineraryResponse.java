package com.trvelplannerplatform.user.iternary.dtos;

import lombok.Data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateItineraryResponse {

	private Long itineraryId;
    private Long tripId;
    private String title;
    private String message;
    private LocalDateTime createdAt;

}