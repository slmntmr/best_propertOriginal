package com.team02.best_properta.payload.response.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGetPageableResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
