package com.team02.best_properta.service.business;


import com.team02.best_properta.repository.business.FavoritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;

    public void deleteByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        favoritesRepository.deleteByUserId(userId);
    }


    //*****************************************************************




}
