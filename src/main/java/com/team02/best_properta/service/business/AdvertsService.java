package com.team02.best_properta.service.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.repository.business.AdvertsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertsService {

    private final AdvertsRepository advertsRepository;


    public boolean userHasAdverts(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return advertsRepository.existsByUserId(userId);
    }


    //************************************************************





    public Advert getAdvertById(Long advertId) {
        return advertsRepository.findById(advertId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ADVERT_NOT_FOUND_MESSAGE));
    }




}
