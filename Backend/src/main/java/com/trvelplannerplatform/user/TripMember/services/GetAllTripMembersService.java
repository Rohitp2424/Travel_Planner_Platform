package com.trvelplannerplatform.user.TripMember.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.TripMember.dtos.GetAllTripMembersResponse;
import com.trvelplannerplatform.user.TripMember.dtos.TripMemberResponse;
import com.trvelplannerplatform.user.TripMember.entities.TripMember;
import com.trvelplannerplatform.user.TripMember.helper.TripMemberHelper;
import com.trvelplannerplatform.user.TripMember.interfaces.TripMemberRepository;
import com.trvelplannerplatform.user.TripMember.mapper.TripMemberMapper;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllTripMembersService {
	
	   private final TripMemberRepository tripMemberRepository;

	   private final TripMemberHelper tripMemberHelper;

	   private final TripMemberMapper tripMemberMapper;

	public GetAllTripMembersResponse getAllMembers(Long tripId) {
		
		 log.info("Fetching all members for Trip : {}", tripId);

	        // Logged-in user
	        User loggedInUser = tripMemberHelper.getLoggedInUser();

	        // Fetch trip
	        Trip trip = tripMemberHelper.getTrip(tripId);
	        
	        log.info("Logged-in User Id   : {}", loggedInUser.getId());
	        log.info("Logged-in User Email: {}", loggedInUser.getEmail());

	        log.info("Trip Owner Id       : {}", trip.getOwner().getId());
	        log.info("Trip Owner Email    : {}", trip.getOwner().getEmail());

	        // Validate owner
	        tripMemberHelper.validateTripOwner(loggedInUser, trip);

	        // Fetch members
	        List<TripMember> members = tripMemberRepository.findByTrip(trip);

	        log.info("Total members found : {}", members.size());

	        // Map entity -> DTO
	        List<TripMemberResponse> responses =
	                tripMemberMapper.toResponseList(members);
	        
	     // Build response
	        GetAllTripMembersResponse response =
	                new GetAllTripMembersResponse();

	        response.setTotalMembers(responses.size());
	        response.setMembers(responses);

	        log.info("Returning {} members", responses.size());

	        return response;
	
	}

}
