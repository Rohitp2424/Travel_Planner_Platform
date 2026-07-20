package com.trvelplannerplatform.user.TripMember.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.TripMember.dtos.AcceptInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RejectInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RemoveTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.dtos.TripMemberResponse;
import com.trvelplannerplatform.user.TripMember.entities.TripMember;
import com.trvelplannerplatform.user.TripMember.enums.InvitationStatus;
import com.trvelplannerplatform.user.TripMember.enums.MemberRole;
import com.trvelplannerplatform.user.entity.Trip;
import com.trvelplannerplatform.user.entity.User;

@Component
public class TripMemberMapper {

    public TripMemberResponse toResponse(TripMember member) {

        TripMemberResponse response = new TripMemberResponse();

        response.setMemberId(member.getId());
        response.setUserId(member.getMember().getId());
        response.setFirstName(member.getMember().getFirstName());
        response.setLastName(member.getMember().getLastName());
        response.setEmail(member.getMember().getEmail());
        response.setRole(member.getRole());
        response.setInvitationStatus(member.getInvitationStatus());
        response.setJoinedAt(member.getJoinedAt());

        return response;
    }
    
    public List<TripMemberResponse> toResponseList(List<TripMember> members) {

        return members.stream()
                .map(this::toResponse)
                .toList();
    }
    
    public TripMember mapToTripMember(Trip trip,
            User member,
            User invitedBy) {

		TripMember tripMember = new TripMember();
		
		tripMember.setTrip(trip);
		tripMember.setMember(member);
		tripMember.setInvitedBy(invitedBy);
		
		tripMember.setRole(MemberRole.MEMBER);
		
		tripMember.setInvitationStatus(InvitationStatus.PENDING);
		
		tripMember.setCreatedAt(LocalDateTime.now());
		
		tripMember.setUpdatedAt(LocalDateTime.now());
		
		return tripMember;
    }
    
    public InviteTripMemberResponse toInviteResponse(
            TripMember tripMember) {

        return new InviteTripMemberResponse(
                tripMember.getId(),
                tripMember.getMember().getEmail(),
                "Invitation sent successfully",
                tripMember.getCreatedAt());

    }
    
    public AcceptInvitationResponse toAcceptResponse(
            TripMember tripMember) {

        return new AcceptInvitationResponse(
                tripMember.getId(),
                "Invitation accepted successfully");

    }
    
    public RejectInvitationResponse toRejectResponse(
            TripMember tripMember) {

        return new RejectInvitationResponse(
                tripMember.getId(),
                "Invitation rejected successfully");

    }

    public RemoveTripMemberResponse toRemoveResponse(
            TripMember tripMember) {

        return new RemoveTripMemberResponse(
                tripMember.getId(),
                "Trip member removed successfully");

    }
    

}