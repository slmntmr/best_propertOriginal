package com.team02.best_properta.contactmessage.mapper;

import com.team02.best_properta.contactmessage.dto.ContactMessageRequest;
import com.team02.best_properta.contactmessage.dto.ContactMessageResponse;
import com.team02.best_properta.contactmessage.entity.ContactMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ContactMessageMapper {


    // !!! Request --> POJO
    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest){
        return ContactMessage.builder()
                .firstName(contactMessageRequest.getFirstName())
                .lastName(contactMessageRequest.getLastName())
                .email(contactMessageRequest.getEmail())
                .message(contactMessageRequest.getMessage())
                .status(0) // Default status value
                .createdAt(LocalDateTime.now())
                .build();
    }

    // !!! pojo --> Response
    public ContactMessageResponse contactMessageToResponse(ContactMessage contactMessage){

        return ContactMessageResponse.builder()
                .firstName(contactMessage.getFirstName())
                .lastName(contactMessage.getLastName())
                .email(contactMessage.getEmail())
                .message(contactMessage.getMessage())
                .build();

    }
}
