package com.trvelplannerplatform.user.TripMember.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.TripMember.dtos.RemoveTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.entities.TripMember;
import com.trvelplannerplatform.user.TripMember.helper.TripMemberHelper;
import com.trvelplannerplatform.user.TripMember.interfaces.TripMemberRepository;
import com.trvelplannerplatform.user.TripMember.mapper.TripMemberMapper;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RemoveTripMemberService {
	   private final TripMemberRepository tripMemberRepository;

	    private final TripMemberHelper tripMemberHelper;

	    private final TripMemberMapper tripMemberMapper;
	    
	public RemoveTripMemberResponse remove(Long memberId) {
		
		 log.info("Remove member request received. Member Id : {}", memberId);

	        // Get logged-in user
	        User loggedInUser = tripMemberHelper.getLoggedInUser();

	        // Fetch Trip Member
	        TripMember tripMember = tripMemberHelper.getTripMember(memberId);

	        // Fetch Trip
	        Trip trip = tripMember.getTrip();

	        // Validate logged-in user is trip owner
	        tripMemberHelper.validateTripOwner(loggedInUser, trip);

	        // Validate owner cannot be removed
	        tripMemberHelper.validateNotOwner(tripMember);
	        
	        try {

	            tripMemberRepository.delete(tripMember);

	            log.info("Trip Member removed successfully. Member Id : {}", memberId);

	        } catch (Exception ex) {

	            log.error("Failed to remove Trip Member : {}", memberId, ex);

	            throw new UserValidationException(
	                    ErrorCodeEnum.TRIP_MEMBER_DELETE_FAILED.getCode(),
	                    ErrorCodeEnum.TRIP_MEMBER_DELETE_FAILED.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR);

	        }

	        RemoveTripMemberResponse response =
	                tripMemberMapper.toRemoveResponse(tripMember);

	        log.info("Returning RemoveTripMemberResponse : {}", response);

	        return response;
	
	}

}
