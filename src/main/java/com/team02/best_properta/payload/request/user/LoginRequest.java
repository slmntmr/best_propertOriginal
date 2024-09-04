package com.team02.best_properta.payload.request.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class LoginRequest {


    private String email;
    private String password;


}


