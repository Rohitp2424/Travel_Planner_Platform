package com.trvelplannerplatform.user.TripMember.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RejectInvitationResponse {

    private Long memberId;

    private String message;

}
