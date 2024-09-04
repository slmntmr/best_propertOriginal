package com.team02.best_properta.payload.request.abstracts;



import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName; // Kullanıcının adı

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName; // Kullanıcının soyadı

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 80, message = "Email must be less than or equal to 80 characters")
    private String email; // Kullanıcının e-posta adresi

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "Phone number must be in the form of (XXX) XXX-XXXX")
    private String phone; // Kullanıcının telefon numarası

    @Column(name = "create_at", nullable = false) // Bu alanın boş olamayacağını belirtir.
    private LocalDateTime createAt; // Kullanıcının oluşturulma tarihi (DateTime), ex: 1990-10-25T10:35:25Z.

}
