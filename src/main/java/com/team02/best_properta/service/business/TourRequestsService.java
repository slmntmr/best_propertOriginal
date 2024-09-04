package com.team02.best_properta.service.business;


import com.team02.best_properta.repository.business.TourRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourRequestsService {

    private final TourRequestsRepository tourRequestsRepository;

    public boolean userHasTourRequests(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return tourRequestsRepository.existsByOwnerUserIdOrGuestUserId(userId, userId);
    }
}
