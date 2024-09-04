package com.team02.best_properta.payload.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserDeleteRequest {

    @NotBlank(message = "Password is required.")
    private String password;
}
