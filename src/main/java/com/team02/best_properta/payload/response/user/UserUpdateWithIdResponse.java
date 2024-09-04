package com.team02.best_properta.payload.response.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateWithIdResponse {


    @NotNull
    private Long id;

    private String firstName;

    private String lastName;



}
