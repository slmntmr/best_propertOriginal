package com.team02.best_properta.contactmessage.controller;

import com.team02.best_properta.contactmessage.dto.ContactMessageRequest;
import com.team02.best_properta.contactmessage.entity.ContactMessage;
import com.team02.best_properta.contactmessage.service.ContactMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("/createContactMessage") // http://localhost:8080/contactMessages/createContactMessage + POST + JSON
    public ResponseEntity<String> createContactMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {
        return contactMessageService.createContactMessage(contactMessageRequest);
    }

    @GetMapping("/searchByEmail") // http://localhost:8080/contactMessages/searchByEmail?email=aaa@bbb.com + GET
    public ResponseEntity<List<ContactMessage>> getContactMessages(
            @RequestParam(value = "email") String email,
            @RequestParam(defaultValue = "0") Integer status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ) {
        List<ContactMessage> messages = contactMessageService.getContactMessages(email, status, page, size, sort, type);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/getById/{contactMessageId}") // http://localhost:8080/contactMessages/getById/1 + GET
    public ResponseEntity<ContactMessage> getByIdPath(@PathVariable Long contactMessageId) {
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));
    }

    @DeleteMapping("/deleteById/{contactMessageId}") // http://localhost:8080/contactMessages/deleteById/2
    public ResponseEntity<String> deleteById(@PathVariable Long contactMessageId) {
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }
}
