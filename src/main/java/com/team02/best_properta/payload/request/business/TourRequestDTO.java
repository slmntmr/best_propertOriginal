package com.team02.best_properta.payload.request.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.entity.concretes.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TourRequestDTO {

    private Long id;
    private LocalDateTime tourDate;
    private LocalDateTime tourTime;
    private Integer status;
    private Advert advert;
    private Users ownerUser;


    public TourRequestDTO(Long id, LocalDateTime tourDate, LocalDateTime tourTime, Integer status) {

    }
}