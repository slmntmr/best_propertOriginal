package com.team02.best_properta.payload.request.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.user.Users;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class TourRequestDto {
    private Long id;
    private String tourDate;
    private Advert advert;
    private Users ownerUser;





}