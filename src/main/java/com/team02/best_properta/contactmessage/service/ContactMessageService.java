package com.team02.best_properta.contactmessage.service;

import com.team02.best_properta.contactmessage.dto.ContactMessageRequest;
import com.team02.best_properta.contactmessage.entity.ContactMessage;
import com.team02.best_properta.contactmessage.mapper.ContactMessageMapper;
import com.team02.best_properta.contactmessage.messages.Messages;
import com.team02.best_properta.contactmessage.repository.ContactMessageRepository;
import com.team02.best_properta.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageMapper contactMessageMapper;

    public ResponseEntity<String> createContactMessage(ContactMessageRequest contactMessageRequest) {
        ContactMessage contactMessage = contactMessageMapper.requestToContactMessage(contactMessageRequest);
        contactMessageRepository.save(contactMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message created");
    }

    public List<ContactMessage> getContactMessages(String email, Integer status, Integer page, Integer size, String sort, String type) {
        Sort.Direction direction = type.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        Page<ContactMessage> contactMessagesPage = contactMessageRepository.findByEmailAndStatus(email, status, pageable);
        return contactMessagesPage.getContent(); // Return list of ContactMessage
    }

    public ContactMessage getContactMessageById(Long id) {
        return contactMessageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE));
    }

    public String deleteById(Long id) {
        getContactMessageById(id); // Ensure the message exists
        contactMessageRepository.deleteById(id);
        return Messages.CONTACT_MESSAGE_DELETED_SUCCESSFULLY;
    }
}
