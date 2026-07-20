package com.trvelplannerplatform.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"password", "confirmPassword"})
public class UserRegisterRequest {

    @NotBlank(message = "FIRST_NAME_REQUIRED")
    @Size(min = 2, max = 50, message = "FIRST_NAME_INVALID_LENGTH")
    private String firstName;

    @NotBlank(message = "LAST_NAME_REQUIRED")
    @Size(min = 2, max = 50, message = "LAST_NAME_INVALID_LENGTH")
    private String lastName;

    @NotBlank(message = "EMAIL_REQUIRED")
    @Email(message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "PHONE_REQUIRED")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "PHONE_INVALID"
    )
    private String phone;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 8, max = 20, message = "PASSWORD_INVALID_LENGTH")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "PASSWORD_WEAK"
    )
    private String password;

    @NotBlank(message = "CONFIRM_PASSWORD_REQUIRED")
    private String confirmPassword;

    private Boolean termsAccepted;
    
}
