package com.trvelplannerplatform.user.TripMember.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcceptInvitationResponse {

    private Long memberId;

    private String message;

}
