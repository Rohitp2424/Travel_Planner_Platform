package com.trvelplannerplatform.user.TripMember.impls;

import org.springframework.stereotype.Service;

import com.trvelplannerplatform.user.TripMember.dtos.AcceptInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.GetAllTripMembersResponse;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberRequest;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RejectInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RemoveTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.interfaces.TripMemberService;
import com.trvelplannerplatform.user.TripMember.services.AcceptInvitationService;
import com.trvelplannerplatform.user.TripMember.services.GetAllTripMembersService;
import com.trvelplannerplatform.user.TripMember.services.InviteTripMemberService;
import com.trvelplannerplatform.user.TripMember.services.RejectInvitationService;
import com.trvelplannerplatform.user.TripMember.services.RemoveTripMemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TripMemberServiceImpl implements TripMemberService {

	 private final InviteTripMemberService inviteTripMemberService;

	 private final GetAllTripMembersService getAllTripMembersService;

	 private final AcceptInvitationService acceptInvitationService;

	 private final RejectInvitationService rejectInvitationService;

	 private final RemoveTripMemberService removeTripMemberService;
	
	
	@Override
    public InviteTripMemberResponse inviteMember(InviteTripMemberRequest request) {
        return inviteTripMemberService.invite(request);
    }

    @Override
    public GetAllTripMembersResponse getAllMembers(Long tripId) {
        return getAllTripMembersService.getAllMembers(tripId);
    }

    @Override
    public AcceptInvitationResponse acceptInvitation(Long memberId) {
        return acceptInvitationService.accept(memberId);
    }

    @Override
    public RejectInvitationResponse rejectInvitation(Long memberId) {
        return rejectInvitationService.reject(memberId);
    }

    @Override
    public RemoveTripMemberResponse removeMember(Long memberId) {
        return removeTripMemberService.remove(memberId);
    }

}
