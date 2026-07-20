package com.trvelplannerplatform.user.TripMember.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trvelplannerplatform.user.TripMember.dtos.AcceptInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.GetAllTripMembersResponse;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberRequest;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RejectInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RemoveTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.services.AcceptInvitationService;
import com.trvelplannerplatform.user.TripMember.services.GetAllTripMembersService;
import com.trvelplannerplatform.user.TripMember.services.InviteTripMemberService;
import com.trvelplannerplatform.user.TripMember.services.RejectInvitationService;
import com.trvelplannerplatform.user.TripMember.services.RemoveTripMemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user/v1/trip-members")
public class TripMemberController{
	
	private final InviteTripMemberService tripMemberService;
	private final GetAllTripMembersService getTripMemberService;
	private final AcceptInvitationService acceptInvitation;
	private final RejectInvitationService rejectedInvitationService;
	private final RemoveTripMemberService removeTripMember;
	
	@PostMapping("/invite")
	public InviteTripMemberResponse inviteMember(
	        @Valid @RequestBody InviteTripMemberRequest request) {

		log.info("Invite Member Controller Hit");
	    return tripMemberService.invite(request);

	}
	
	@GetMapping("/{tripId}")
	public GetAllTripMembersResponse getAllMembers(
	        @PathVariable Long tripId) {

	    log.info("Get all trip members request received for Trip : {}", tripId);

	    GetAllTripMembersResponse response =
	            getTripMemberService.getAllMembers(tripId);

	    log.info("Returning trip members");

	    return response;
	}
	
	@PutMapping("/{memberId}/accept")
	public AcceptInvitationResponse acceptInvitation(
	        @PathVariable Long memberId) {

	    log.info("Accept invitation request received for Member Id : {}", memberId);

	    AcceptInvitationResponse response =
	    		acceptInvitation.accept(memberId);

	    log.info("Invitation accepted successfully.");

	    return response;
	}

	@PutMapping("/{memberId}/reject")
	public RejectInvitationResponse rejectInvitation(
	        @PathVariable Long memberId) {

	    log.info("Reject invitation request received for Member Id : {}", memberId);

	    RejectInvitationResponse response =
	    		rejectedInvitationService.reject(memberId);

	    log.info("Invitation rejected successfully.");

	    return response;
	}
	
	@DeleteMapping("/{memberId}")
	public RemoveTripMemberResponse removeMember(
	        @PathVariable Long memberId) {

	    log.info("Remove Member Controller Hit. Member Id : {}", memberId);

	    RemoveTripMemberResponse response =
	    		removeTripMember.remove(memberId);

	    log.info("Trip Member removed successfully.");

	    return response;
	}
}
