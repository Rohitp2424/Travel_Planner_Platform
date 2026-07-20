package com.trvelplannerplatform.user.TripMember.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InviteTripMemberResponse {

    private Long memberId;

    private String email;

    private String message;

    private LocalDateTime invitedAt;

}
