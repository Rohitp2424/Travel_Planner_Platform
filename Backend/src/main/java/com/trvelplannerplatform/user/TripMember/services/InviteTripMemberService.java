package com.trvelplannerplatform.user.TripMember.services;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberRequest;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberResponse;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class InviteTripMemberService {
	
	  private final TripMemberRepository tripMemberRepository;

	    private final TripMemberHelper tripMemberHelper;

	    private final TripMemberMapper tripMemberMapper;


	public InviteTripMemberResponse invite(InviteTripMemberRequest request) {
			
		log.info("Invite member request received : {}", request);

	    User loggedInUser = tripMemberHelper.getLoggedInUser();

	    Trip trip = tripMemberHelper.getTrip(request.getTripId());

	    tripMemberHelper.validateTripOwner(loggedInUser, trip);

	    User member = tripMemberHelper.getUserByEmail(request.getEmail());
	    
	    /*
         * Owner cannot invite himself.
         */
        if (loggedInUser.getId().equals(member.getId())) {

            log.error("Owner cannot invite himself.");

            throw new UserValidationException(
                    ErrorCodeEnum.CANNOT_INVITE_YOURSELF.getCode(),
                    ErrorCodeEnum.CANNOT_INVITE_YOURSELF.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        /*
         * Check existing member.
         */
        tripMemberHelper.validateAlreadyMember(trip, member);

        /*
         * Map entity.
         */
        TripMember tripMember =
                tripMemberMapper.mapToTripMember(
                        trip,
                        member,
                        loggedInUser);
        try {

            tripMember = tripMemberRepository.save(tripMember);

            log.info("Invitation sent successfully. Member Id : {}",
                    tripMember.getId());

        } catch (Exception ex) {

            log.error("Failed to invite member {}", member.getEmail(), ex);

            throw new UserValidationException(
                    ErrorCodeEnum.TRIP_MEMBER_CREATION_FAILED.getCode(),
                    ErrorCodeEnum.TRIP_MEMBER_CREATION_FAILED.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }

        InviteTripMemberResponse response =
                tripMemberMapper.toInviteResponse(tripMember);

        log.info("Returning response : {}", response);

        return response;

	}

}
