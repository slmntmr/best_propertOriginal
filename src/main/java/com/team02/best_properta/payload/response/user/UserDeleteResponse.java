package com.team02.best_properta.payload.response.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDeleteResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
