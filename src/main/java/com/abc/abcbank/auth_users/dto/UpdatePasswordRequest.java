package com.abc.abcbank.auth_users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.parameters.P;

@Data
public class UpdatePasswordRequest  {

    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    private String newPassword;
}
