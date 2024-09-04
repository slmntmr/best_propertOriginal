package com.team02.best_properta.payload.request.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserPasswordUpdateRequest {
    private String password;
}
