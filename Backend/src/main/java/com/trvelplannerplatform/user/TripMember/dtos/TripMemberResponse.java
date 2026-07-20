package com.trvelplannerplatform.user.TripMember.dtos;

import java.time.LocalDateTime;

import com.trvelplannerplatform.user.TripMember.enums.InvitationStatus;
import com.trvelplannerplatform.user.TripMember.enums.MemberRole;

import lombok.Data;

@Data
public class TripMemberResponse {

    private Long memberId;

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private MemberRole role;

    private InvitationStatus invitationStatus;

    private LocalDateTime joinedAt;

}
