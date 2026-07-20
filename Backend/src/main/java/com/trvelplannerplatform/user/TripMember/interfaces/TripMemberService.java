package com.trvelplannerplatform.user.TripMember.interfaces;

import com.trvelplannerplatform.user.TripMember.dtos.AcceptInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.GetAllTripMembersResponse;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberRequest;
import com.trvelplannerplatform.user.TripMember.dtos.InviteTripMemberResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RejectInvitationResponse;
import com.trvelplannerplatform.user.TripMember.dtos.RemoveTripMemberResponse;

public interface TripMemberService {
	
	  public InviteTripMemberResponse inviteMember(InviteTripMemberRequest request);

	  public GetAllTripMembersResponse getAllMembers(Long tripId);

	  public AcceptInvitationResponse acceptInvitation(Long memberId);

	  public RejectInvitationResponse rejectInvitation(Long memberId);

	  public RemoveTripMemberResponse removeMember(Long memberId);

}
