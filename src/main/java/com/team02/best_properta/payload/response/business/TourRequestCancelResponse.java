package com.team02.best_properta.payload.response.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.user.Users;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TourRequestCancelResponse {
    private Long id;
    private LocalDateTime tourDate;
    private LocalDateTime tourTime;
    private Integer status;
    private Advert advert;
    private Users ownerUser;
    private Users guestUser;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}