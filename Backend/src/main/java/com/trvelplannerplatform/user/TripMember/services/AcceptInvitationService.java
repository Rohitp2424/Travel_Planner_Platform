package com.trvelplannerplatform.user.TripMember.services;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.TripMember.dtos.AcceptInvitationResponse;
import com.trvelplannerplatform.user.TripMember.entities.TripMember;
import com.trvelplannerplatform.user.TripMember.enums.InvitationStatus;
import com.trvelplannerplatform.user.TripMember.helper.TripMemberHelper;
import com.trvelplannerplatform.user.TripMember.interfaces.TripMemberRepository;
import com.trvelplannerplatform.user.TripMember.mapper.TripMemberMapper;
import com.trvelplannerplatform.user.entity.User;
import com.trvelplannerplatform.user.enums.ErrorCodeEnum;
import com.trvelplannerplatform.user.exception.UserValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcceptInvitationService {
	
	 private final TripMemberRepository tripMemberRepository;

	 private final TripMemberHelper tripMemberHelper;

	 private final TripMemberMapper tripMemberMapper;


	public AcceptInvitationResponse accept(Long memberId) {
		  log.info("Accept invitation request received for Member Id : {}", memberId);

	        // Get logged-in user
	        User loggedInUser = tripMemberHelper.getLoggedInUser();
	        
	        // Fetch invitation
	        TripMember tripMember = tripMemberHelper.getTripMember(memberId);

	        // Validate invitation belongs to logged-in user
	        tripMemberHelper.validateInvitationOwner(loggedInUser, tripMember);

	        // Validate invitation is still pending
	        tripMemberHelper.validatePendingInvitation(tripMember);
	        
	        try {

	            tripMember.setInvitationStatus(InvitationStatus.ACCEPTED);
	            tripMember.setJoinedAt(LocalDateTime.now());
	            tripMember.setUpdatedAt(LocalDateTime.now());

	            tripMember = tripMemberRepository.save(tripMember);

	            log.info("Invitation accepted successfully for Member Id : {}",
	                    tripMember.getId());

	        } catch (Exception ex) {

	            log.error("Failed to accept invitation for Member Id : {}",
	                    memberId, ex);

	            throw new UserValidationException(
	                    ErrorCodeEnum.TRIP_MEMBER_UPDATE_FAILED.getCode(),
	                    ErrorCodeEnum.TRIP_MEMBER_UPDATE_FAILED.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR);

	        }
	        AcceptInvitationResponse response =
	                tripMemberMapper.toAcceptResponse(tripMember);

	        log.info("Returning AcceptInvitationResponse : {}", response);

	        return response;
	}

}
