package com.team02.best_properta.service.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.AdvertMapper;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.payload.response.business.AdvertResponse;
import com.team02.best_properta.repository.business.AdvertsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertsService {


    private final AdvertMapper advertMapper;
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


    //************************************************************






    public List<Advert> findAdvertsByCriteria(LocalDate date1, LocalDate date2, String category, String advertType, String status) {
        return advertsRepository.findAdvertsByCriteria(date1.atStartOfDay(), date2.atStartOfDay(), category, advertType, status);
    }

    public int countActivatedAdverts() {
        return advertsRepository.countActivatedAdverts();
    }

    public List<AdvertResponse> getAdverts(LocalDateTime date1, LocalDateTime date2, String category, String type, String status) {
        List<Advert> adverts = advertsRepository.findAdvertsByCriteria(date1, date2, category, type, status);
        return adverts.stream()
                .map(advertMapper::toAdvertResponse) // Lambda ifadesi kullanarak dönüşümü yapın
                .collect(Collectors.toList());
    }
    public List<Advert> getMostPopularProperties(int amount) {

        return advertsRepository.findMostPopularProperties(amount);
    }

}
