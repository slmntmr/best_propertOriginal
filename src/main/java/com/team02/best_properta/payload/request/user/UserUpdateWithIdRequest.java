package com.team02.best_properta.payload.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserUpdateWithIdRequest {

    @NotNull
    private Long id;

    private String firstName;

    private String lastName;

}
