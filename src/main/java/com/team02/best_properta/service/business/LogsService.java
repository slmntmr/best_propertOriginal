package com.team02.best_properta.service.business;


import com.team02.best_properta.repository.business.LogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogsService {

    private final LogsRepository logsRepository;

    public void deleteByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        logsRepository.deleteByUserId(userId);
    }
}
