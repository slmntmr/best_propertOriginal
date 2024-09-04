package com.team02.best_properta.payload.request.business;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank(message = "Reset code is required")
    private String resetCode; // Şifre sıfırlama kodu

    @NotBlank(message = "New password is required")
    private String newPassword; // Yeni şifre
}
