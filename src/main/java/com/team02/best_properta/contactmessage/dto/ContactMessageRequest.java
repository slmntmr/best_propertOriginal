package com.team02.best_properta.contactmessage.dto;

import lombok.Data;

@Data
public class ContactMessageRequest {


    private String firstName;
    private String lastName;
    private String email;
    private String message;
}
