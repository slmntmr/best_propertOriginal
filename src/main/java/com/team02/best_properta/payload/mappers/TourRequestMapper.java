package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.TourRequest;

import com.team02.best_properta.payload.request.business.TourRequestAdmin;
import com.team02.best_properta.payload.request.business.TourRequestDTO;
import com.team02.best_properta.payload.response.business.TourRequestResponse;
import org.springframework.stereotype.Component;


@Component
public class TourRequestMapper {

    public TourRequestAdmin mapTourRequestToTourRequestAdminDto(TourRequest tourRequest) {
        return TourRequestAdmin.builder()
                .id(tourRequest.getId())
                .tourDate(tourRequest.getTourDate())
                .advert(tourRequest.getAdvert())
                .ownerUser(tourRequest.getOwnerUser())
                .guestUser(tourRequest.getGuestUser())
                .build();
    }

    public TourRequestResponse mapTourRequestToTourRequestResponse(TourRequest tourRequest) {
        return TourRequestResponse.builder()
                .id(tourRequest.getId())
                .tourDate(tourRequest.getTourDate())
                .build();
    }

    public TourRequestDTO mapTourRequestToTourRequestDto(TourRequest tourRequest) {
        return TourRequestDTO.builder()
                .id(tourRequest.getId())
                .tourDate(tourRequest.getTourDate())
                .advert(tourRequest.getAdvert())
                .ownerUser(tourRequest.getOwnerUser())
                .build();
    }

}