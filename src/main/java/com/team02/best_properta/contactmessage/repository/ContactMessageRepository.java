package com.team02.best_properta.contactmessage.repository;

import com.team02.best_properta.contactmessage.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    // Find by status with pagination
    Page<ContactMessage> findByStatus(Integer status, Pageable pageable);

    // Find by email and status with pagination
    Page<ContactMessage> findByEmailAndStatus(String email, Integer status, Pageable pageable);
}
