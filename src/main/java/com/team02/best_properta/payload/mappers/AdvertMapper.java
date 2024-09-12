package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.business.Image;
import com.team02.best_properta.payload.response.business.AdvertResponse;
import com.team02.best_properta.service.business.ImageService;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Builder(toBuilder = true)
public class AdvertMapper {



    public AdvertResponse toAdvertResponse(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .price(advert.getPrice())
                .category((advert.getCategory()))
                .advertType(advert.getAdvertType())
                .status(advert.getStatus())
                .createAt(advert.getCreateAt())
                .build();
    }



    public AdvertResponse mapToAdvertResponse(Advert advert) {
        // İlgili Advert için Image listesi alınır
      /*  List<Image> images = imageService.getImagesByAdvertId(advert.getId());*/
        return AdvertResponse.builder().
                id(advert.getId())
                .title(advert.getTitle())
                .district(advert.getDistrict())
                .description(advert.getDescription())
                .category(advert.getCategory())
                .price(advert.getPrice())
                .advertType(advert.getAdvertType())
                .country(advert.getCountry())
                .location(advert.getLocation())
                .city(advert.getCity())
                .createAt(advert.getCreateAt()).
              /*  images(images).*/
                build();
    }



}