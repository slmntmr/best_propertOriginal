package com.team02.best_properta.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id; // Kullanıcının benzersiz kimliği (PK).
    private String firstName; // Kullanıcının adı.
    private String lastName; // Kullanıcının soyadı.
    private String email; // Kullanıcının e-posta adresi.
    private String phone; // Kullanıcının telefon numarası.
    private String role; // Kullanıcının rolü (örneğin, "Customer").



    public UserResponse(Long id, String firstName) {
    }
}
