package com.trvelplannerplatform.user.TripMember.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InviteTripMemberRequest {

    @NotNull(message = "Trip Id is required")
    private Long tripId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

}
