package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.AdvertType;
import com.team02.best_properta.payload.response.business.AdvertTypeResponse;

public class AdvertTypeMapper {

    public static AdvertTypeResponse toResponse(AdvertType advertType) {
        return new AdvertTypeResponse(advertType.getId(), advertType.getTitle());
    }
}