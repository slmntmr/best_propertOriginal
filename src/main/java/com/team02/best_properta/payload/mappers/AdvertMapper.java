package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.AdvertType;
import com.team02.best_properta.payload.response.business.AdvertResponse;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder(toBuilder = true)
public class AdvertMapper {

    public AdvertResponse toAdvertResponse(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .price(advert.getPrice())
                .category(String.valueOf(advert.getCategory()))
                .type(advert.getAdvertType())
                .status(advert.getStatus())
                .createdDate(advert.getCreateAt())
                .build();
    }
}