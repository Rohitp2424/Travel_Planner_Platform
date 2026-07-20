package com.trvelplannerplatform.user.TripMember.dtos;

import java.util.List;

import lombok.Data;

@Data
public class GetAllTripMembersResponse {

    private Integer totalMembers;

    private List<TripMemberResponse> members;

}
